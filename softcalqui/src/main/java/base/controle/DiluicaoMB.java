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
import base.modelo.Diluicao;
import base.modelo.Usuario;
import base.service.DiluicaoService;
import dao.GenericDAO;
import util.ChamarRelatorio;
import util.EnviarEmail;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("diluicaoMB")
public class DiluicaoMB {
	
	private static final long serialVersionUID = 1L;

	private Diluicao diluicao;
	private List<Diluicao> diluicaoBusca;
	private List<Diluicao> listaDiluicao;
	
	private Diluicao puxarResultado;
	
	private EnviarEmail enviarEmail;
	private Usuario usuario;
	
	@Inject
	private GenericDAO<Diluicao> daoDiluicao; //faz as buscas
	
	@Inject
	private DiluicaoService diluicaoService; // inserir no banco
	
	@Inject
	private EntityManager manager;

	
	@PostConstruct
	public void inicializar() {
	
		diluicao = new Diluicao();
	
		listaDiluicao = new ArrayList<>();
		listaDiluicao = daoDiluicao.listaComStatus(Diluicao.class);
		diluicaoBusca = new ArrayList<>();
		
	}
	
	public void preencherListaDiluicao(Diluicao t) {
		this.diluicao = t;

	}
	
	public void inativarDiluicao(Diluicao t) {
		diluicaoService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}
	
	
	public void salvar() {
		
		calcular();
		
		try {			
				
				if (diluicao.getId() == null) {
					diluicao.setStatus(true);
					diluicaoService.inserirAlterar(diluicao);
					

				} else {
					diluicao.setStatus(true);
					diluicaoService.inserirAlterar(diluicao);
					
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
	
	public void buscandoResultado(Diluicao r) {
		puxarResultado = r;
	}
		
	
	public void enviarEmail() {
		
		//sempre que for chamado um metodo, utiliza-se o nome do objeto da classe.nome do metodo
		enviarEmail.enviarEmail(usuario.getEmail(), "Dados do cálculo de Diluição", 
				"Descrição: " + puxarResultado.getDescricao() +
				" - Resultado: " + puxarResultado.getResultado());	
		
	}
	
	public void calcular() {
		diluicao.setResultado((diluicao.getConcentracaoSolucaoPretendida() * diluicao.getVolumeSolucaoPretendida()) 
				/ diluicao.getConcentracaoSolucaoInicial());
	}
	
	
	public void criarNovoObjeto() {
		diluicao = new Diluicao();
	}

	public void carregarLista() {
		listaDiluicao = daoDiluicao.listaComStatus(Diluicao.class);
	}
	
	
	
	public void imprimirRelatorioDiluicao() { 
		try {
			List<Diluicao> relatorio = daoDiluicao.listar(Diluicao.class, "status = true");
			if (relatorio.size() > 0) {

				HashMap parametro = new HashMap<>();
				
				ChamarRelatorio ch = new ChamarRelatorio("rel_diluicao.jasper", parametro, "relatorio_diluicao");
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
	

	public Diluicao getDiluicao() {
		return diluicao;
	}

	public void setDiluicao(Diluicao diluicao) {
		this.diluicao = diluicao;
	}

	public List<Diluicao> getDiluicaoBusca() {
		return diluicaoBusca;
	}

	public void setDiluicaoBusca(List<Diluicao> diluicaoBusca) {
		this.diluicaoBusca = diluicaoBusca;
	}

	public List<Diluicao> getListaDiluicao() {
		return listaDiluicao;
	}

	public void setListaDiluicao(List<Diluicao> listaDiluicao) {
		this.listaDiluicao = listaDiluicao;
	}

	public GenericDAO<Diluicao> getDaoDiluicao() {
		return daoDiluicao;
	}

	public void setDaoDiluicao(GenericDAO<Diluicao> daoDiluicao) {
		this.daoDiluicao = daoDiluicao;
	}

	public DiluicaoService getDiluicaoService() {
		return diluicaoService;
	}

	public void setDiluicaoService(DiluicaoService diluicaoService) {
		this.diluicaoService = diluicaoService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Diluicao getPuxarResultado() {
		return puxarResultado;
	}

	public void setPuxarResultado(Diluicao puxarResultado) {
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

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	
	
	
	
}