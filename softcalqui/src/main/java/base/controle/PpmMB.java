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

import base.modelo.Diluicao;
import base.modelo.Ppm;
import base.modelo.Usuario;
import base.service.PpmService;
import dao.GenericDAO;
import util.ChamarRelatorio;
import util.EnviarEmail;
import util.ExibirMensagem;
import util.FecharDialog;
import util.Mensagem;

@ViewScoped
@Named("ppmMB")
public class PpmMB {
	
	private static final long serialVersionUID = 1L;
	
	private Ppm ppm;
	private List<Ppm> ppmBusca;
	private List<Ppm> listaPpm;
	
	private Ppm puxarResultado;
	
	private EnviarEmail enviarEmail;
	private Usuario usuario;
	
	@Inject
	private GenericDAO<Ppm> daoPpm; //faz as buscas
	
	@Inject
	private PpmService ppmService; // inserir no banco
	
	@Inject
	private EntityManager manager;
	
	
	@PostConstruct
	public void inicializar() {
	
		ppm = new Ppm();
	
		listaPpm = daoPpm.listaComStatus(Ppm.class);
		ppmBusca = new ArrayList<>();
		
	}

	public void preencherListaPpm(Ppm t) {
		this.ppm = t;

	}
	
	
	public void inativarPpm(Ppm t) {
		ppmService.update(" Tipo set status = false where id = " + t.getId());
		criarNovoObjeto();
		ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
		carregarLista();
	}

	public void salvar() {
		calcular();
		try {			
				
				if (ppm.getId() == null) {
					ppm.setStatus(true);
					ppmService.inserirAlterar(ppm);
					//calcular();
					

				} else {
					ppm.setStatus(true);
					ppmService.inserirAlterar(ppm);
					//calcular();
				}
				
				//calcular();

				criarNovoObjeto();
				
				ExibirMensagem.exibirMensagem(Mensagem.SUCESSO);
				FecharDialog.fecharDialogCadastro();
				carregarLista();
			
		} catch (Exception e) {
			ExibirMensagem.exibirMensagem(Mensagem.ERRO);
			e.printStackTrace();
		}

	}
	
	public void buscandoResultado(Ppm r) {
		puxarResultado = r;
	}
		
	
	public void enviarEmail() {
		
		//sempre que for chamado um metodo, utiliza-se o nome do objeto da classe.nome do metodo
		enviarEmail.enviarEmail(usuario.getEmail(), "Dados do cálculo de PPM", 
				"Descrição: " + puxarResultado.getDescricao() +
				" - Resultado: " + puxarResultado.getResultado());	
		
	}
	
	public void calcular() {
		Double r;
		
		r = (ppm.getConcentracaoSolucaoPpm() * ppm.getVolumeSolucao()) / 1000000;
		
		ppm.setResultado((r * 100) / ppm.getPurezaSoluto()); 
	}
	
	public void criarNovoObjeto() {
		ppm = new Ppm();
	}
	
	public void carregarLista() {
		listaPpm = daoPpm.listaComStatus(Ppm.class);
	}
	
	
	public void imprimirRelatorioPpm() { 
		try {
			List<Ppm> relatorio = daoPpm.listar(Ppm.class, "status = true");
			if (relatorio.size() > 0) {

				HashMap parametro = new HashMap<>();
				
				ChamarRelatorio ch = new ChamarRelatorio("rel_ppm.jasper", parametro, "relatorio_ppm");
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

	public Ppm getPpm() {
		return ppm;
	}

	public void setPpm(Ppm ppm) {
		this.ppm = ppm;
	}

	public List<Ppm> getPpmBusca() {
		return ppmBusca;
	}

	public void setPpmBusca(List<Ppm> ppmBusca) {
		this.ppmBusca = ppmBusca;
	}

	public List<Ppm> getListaPpm() {
		return listaPpm;
	}

	public void setListaPpm(List<Ppm> listaPpm) {
		this.listaPpm = listaPpm;
	}

	public GenericDAO<Ppm> getDaoPpm() {
		return daoPpm;
	}

	public void setDaoPpm(GenericDAO<Ppm> daoPpm) {
		this.daoPpm = daoPpm;
	}

	public PpmService getPpmService() {
		return ppmService;
	}

	public void setPpmService(PpmService ppmService) {
		this.ppmService = ppmService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Ppm getPuxarResultado() {
		return puxarResultado;
	}

	public void setPuxarResultado(Ppm puxarResultado) {
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
