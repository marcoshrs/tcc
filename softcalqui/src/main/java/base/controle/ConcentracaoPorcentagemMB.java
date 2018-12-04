package base.controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import base.modelo.ConcentracaoMolar;
import base.modelo.ConcentracaoPorcentagem;
import base.modelo.Usuario;
import base.service.ConcentracaoMolarService;
import base.service.ConcentracaoPorcentagemService;
import dao.GenericDAO;
import util.ChamarRelatorio;
import util.EnviarEmail;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("concentracaoPorcentagemMB")
public class ConcentracaoPorcentagemMB {
	
	private static final long serialVersionUID = 1L;
	
	private ConcentracaoPorcentagem concentracaoPorcentagem;
	private List<ConcentracaoPorcentagem> concentracaoPorcentagemBusca;
	private List<ConcentracaoPorcentagem> listaConcentracaoPorcentagem;
	
	private ConcentracaoPorcentagem puxarResultado;
	
	private EnviarEmail enviarEmail;
	private Usuario usuario;
	
	
	
	@Inject
	private GenericDAO<ConcentracaoPorcentagem> daoConcentracaoPorcentagem;
	
	@Inject
	private ConcentracaoPorcentagemService concentracaoPorcentagemService;
	
	@Inject
	private EntityManager manager;
	
	
	@PostConstruct
	public void inicializar() {
		
		concentracaoPorcentagem = new ConcentracaoPorcentagem();
		
		listaConcentracaoPorcentagem = new ArrayList<>();
		listaConcentracaoPorcentagem = daoConcentracaoPorcentagem.listaComStatus(ConcentracaoPorcentagem.class);
		concentracaoPorcentagemBusca = new ArrayList<>();
		
	}
	
	
	public void preencherListaConcentracaoPorcentagem(ConcentracaoPorcentagem t) {
		this.concentracaoPorcentagem = t;
	}
	
	public void inativarConcentracaoPorcentagem(ConcentracaoPorcentagem t) {
		concentracaoPorcentagemService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}
	
	public void salvar() {
		
	calcular();
		
		try {
				
				if	(concentracaoPorcentagem.getId() == null) {
					 concentracaoPorcentagem.setStatus(true);
					 concentracaoPorcentagemService.inserirAlterar(concentracaoPorcentagem);
					 
				
				} else {
					concentracaoPorcentagem.setStatus(true);
					concentracaoPorcentagemService.inserirAlterar(concentracaoPorcentagem);
					calcular();
				}
				
				
				
				criarNovoObjeto();
				
				ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
				FecharDialog.fecharDialogCadastro();
				carregarLista();
				
		} catch (Exception e) {
			ExibirMensagem.exibirMensagem(Mensagem.ERRO);
			e.printStackTrace();
		}
	}
	
	public void buscandoResultado(ConcentracaoPorcentagem r) {
		puxarResultado = r;
	}
		
	
public void enviarEmail() {
		
		//sempre que for chamado um metodo, utiliza-se o nome do objeto da classe.nome do metodo
		enviarEmail.enviarEmail(usuario.getEmail(), "Dados do cálculo de Concentração em Porcentagem", 
				"Descrição: " + puxarResultado.getDescricao() +
				" - Concentração Percentual: " + puxarResultado.getConcentracaoPercentual()+
				" - Volume da Solução: " + puxarResultado.getVolumeSolucao()+
				" - Pureza do Soluto: " + puxarResultado.getPurezaSoluto()+
				" - Resultado: " + puxarResultado.getResultado());	
		
	}
	
	public void calcular() {
		
		Double r;
		
		r = concentracaoPorcentagem.getVolumeSolucao() * (concentracaoPorcentagem.getConcentracaoPercentual() / 100);
		
		concentracaoPorcentagem.setResultado((r * 100) / concentracaoPorcentagem.getPurezaSoluto());
	}
	
	public void criarNovoObjeto() {
		concentracaoPorcentagem = new ConcentracaoPorcentagem();
	}
	
	public void carregarLista() {
		listaConcentracaoPorcentagem = daoConcentracaoPorcentagem.listaComStatus(ConcentracaoPorcentagem.class);
	}
	
	
	
	public void imprimirRelatorioConcentracaoPorcentagem() { 
		try {
			List<ConcentracaoPorcentagem> relatorio = daoConcentracaoPorcentagem.listar(ConcentracaoPorcentagem.class, "status = true");
			if (relatorio.size() > 0) {

				HashMap parametro = new HashMap<>();
				
				ChamarRelatorio ch = new ChamarRelatorio("rel_porcentagem.jasper", parametro, "relatorio_concentracao_porcentagem");
				Session sessions = manager.unwrap(Session.class);
				sessions.doWork(ch);

			} else {
				ExibirMensagem.exibirMensagem(Mensagem.NADA_ENCONTRADO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExibirMensagem.exibirMensagem(Mensagem.ERRO);
		}
	}



	public ConcentracaoPorcentagem getConcentracaoPorcentagem() {
		return concentracaoPorcentagem;
	}



	public void setConcentracaoPorcentagem(ConcentracaoPorcentagem concentracaoPorcentagem) {
		this.concentracaoPorcentagem = concentracaoPorcentagem;
	}



	public List<ConcentracaoPorcentagem> getConcentracaoPorcentagemBusca() {
		return concentracaoPorcentagemBusca;
	}



	public void setConcentracaoPorcentagemBusca(List<ConcentracaoPorcentagem> concentracaoPorcentagemBusca) {
		this.concentracaoPorcentagemBusca = concentracaoPorcentagemBusca;
	}



	public List<ConcentracaoPorcentagem> getListaConcentracaoPorcentagem() {
		return listaConcentracaoPorcentagem;
	}



	public void setListaConcentracaoPorcentagem(List<ConcentracaoPorcentagem> listaConcentracaoPorcentagem) {
		this.listaConcentracaoPorcentagem = listaConcentracaoPorcentagem;
	}



	public GenericDAO<ConcentracaoPorcentagem> getDaoConcentracaoPorcentagem() {
		return daoConcentracaoPorcentagem;
	}



	public void setDaoConcentracaoPorcentagem(GenericDAO<ConcentracaoPorcentagem> daoConcentracaoPorcentagem) {
		this.daoConcentracaoPorcentagem = daoConcentracaoPorcentagem;
	}



	public ConcentracaoPorcentagemService getConcentracaoPorcentagemService() {
		return concentracaoPorcentagemService;
	}



	public void setConcentracaoPorcentagemService(ConcentracaoPorcentagemService concentracaoPorcentagemService) {
		this.concentracaoPorcentagemService = concentracaoPorcentagemService;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EnviarEmail getEnviarEmail() {
		return enviarEmail;
	}


	public void setEnviarEmail(EnviarEmail enviarEmail) {
		this.enviarEmail = enviarEmail;
	}


	public EntityManager getManager() {
		return manager;
	}


	public void setManager(EntityManager manager) {
		this.manager = manager;
	}


	public ConcentracaoPorcentagem getPuxarResultado() {
		return puxarResultado;
	}


	public void setPuxarResultado(ConcentracaoPorcentagem puxarResultado) {
		this.puxarResultado = puxarResultado;
	}

	

	

}
