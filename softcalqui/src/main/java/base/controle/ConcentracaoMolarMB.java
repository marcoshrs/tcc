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
import dao.GenericDAO;
import util.ChamarRelatorio;
import util.EnviarEmail;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("concentracaoMolarMB")
public class ConcentracaoMolarMB {
	
	private static final long serialVersionUID = 1L;
	
	private ConcentracaoMolar concentracaoMolar;
	private List<ConcentracaoMolar> concentracaoMolarBusca;
	private List<ConcentracaoMolar> listaConcentracaoMolar;
	
	private ConcentracaoMolar puxarResultado;
	
	private EnviarEmail enviarEmail;
	private Usuario usuario;
	
	
	@Inject
	private GenericDAO<ConcentracaoMolar> daoConcentracaoMolar;
	
	@Inject
	private ConcentracaoMolarService concentracaoMolarService;
	
	@Inject
	private EntityManager manager;
	
	
	
	@PostConstruct
	public void inicializar() {
		
		concentracaoMolar = new ConcentracaoMolar();
		
		listaConcentracaoMolar = new ArrayList<>();
		listaConcentracaoMolar = daoConcentracaoMolar.listaComStatus(ConcentracaoMolar.class);
		concentracaoMolarBusca = new ArrayList<>();
		
	}
	
	
	
	public void preencherListaConcentracaoMolar(ConcentracaoMolar t) {
		this.concentracaoMolar = t;
	}
	
	public void inativarConcentracaoMolar(ConcentracaoMolar t) {
		concentracaoMolarService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}
	
	public void salvar() {
		
		
		calcular();
		
		try {
				
				if	(concentracaoMolar.getId() == null) {
					 concentracaoMolar.setStatus(true);
					 concentracaoMolarService.inserirAlterar(concentracaoMolar);
					 
				
				} else {
					concentracaoMolar.setStatus(true);
					concentracaoMolarService.inserirAlterar(concentracaoMolar);
					

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
	
	public void buscandoResultado(ConcentracaoMolar r) {
		puxarResultado = r;
	}
		
	
	public void enviarEmail() {
		
		//sempre que for chamado um metodo, utiliza-se o nome do objeto da classe.nome do metodo
		enviarEmail.enviarEmail(usuario.getEmail(), "Dados do cálculo de Concentração Molar", 
				"Descrição: " + puxarResultado.getDescricao() +
				" - Resultado: " + puxarResultado.getResultado());	
		
	}
	
	public void calcular() {
		Double r;
		
		r = concentracaoMolar.getConcentracaoPretendida() * concentracaoMolar.getVolume() 
				* concentracaoMolar.getMassaMolecularSoluto();
		
		concentracaoMolar.setResultado((r * 100) / concentracaoMolar.getPurezaSoluto());
	}
	
	
	
	public void criarNovoObjeto() {
		concentracaoMolar = new ConcentracaoMolar();
	}
	
	public void carregarLista() {
		listaConcentracaoMolar = daoConcentracaoMolar.listaComStatus(ConcentracaoMolar.class);
	}
	
	public void imprimirRelatorioConcentracaoMolar() { 
		try {
			List<ConcentracaoMolar> relatorio = daoConcentracaoMolar.listar(ConcentracaoMolar.class, "status = true");
			if (relatorio.size() > 0) {

				HashMap parametro = new HashMap<>();
				
				ChamarRelatorio ch = new ChamarRelatorio("rel_conMolar.jasper", parametro, "relatorio_concentracao_molar");
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
	
	public List<ConcentracaoMolar> getConcentracaoMolarBusca() {
		return concentracaoMolarBusca;
	}

	public void setConcentracaoMolarBusca(List<ConcentracaoMolar> concentracaoMolarBusca) {
		this.concentracaoMolarBusca = concentracaoMolarBusca;
	}

	public List<ConcentracaoMolar> getListaConcentracaoMolar() {
		return listaConcentracaoMolar;
	}

	public void setListaConcentracaoMolar(List<ConcentracaoMolar> listaConcentracaoMolar) {
		this.listaConcentracaoMolar = listaConcentracaoMolar;
	}

	public GenericDAO<ConcentracaoMolar> getDaoConcentracaoMolar() {
		return daoConcentracaoMolar;
	}

	public void setDaoConcentracaoMolar(GenericDAO<ConcentracaoMolar> daoConcentracaoMolar) {
		this.daoConcentracaoMolar = daoConcentracaoMolar;
	}

	public ConcentracaoMolarService getConcentracaoMolarService() {
		return concentracaoMolarService;
	}

	public void setConcentracaoMolarService(ConcentracaoMolarService concentracaoMolarService) {
		this.concentracaoMolarService = concentracaoMolarService;
	}

	public ConcentracaoMolar getConcentracaoMolar() {
		return concentracaoMolar;
	}

	public void setConcentracaoMolar(ConcentracaoMolar concentracaoMolar) {
		this.concentracaoMolar = concentracaoMolar;
	}




	public EntityManager getManager() {
		return manager;
	}



	public void setManager(EntityManager manager) {
		this.manager = manager;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public ConcentracaoMolar getPuxarResultado() {
		return puxarResultado;
	}



	public void setPuxarResultado(ConcentracaoMolar puxarResultado) {
		this.puxarResultado = puxarResultado;
	}



	public EnviarEmail getEnviarEmail() {
		return enviarEmail;
	}



	public void setEnviarEmail(EnviarEmail enviarEmail) {
		this.enviarEmail = enviarEmail;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}
