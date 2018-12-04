package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.ConcentracaoPorcentagem;
import dao.GenericDAO;
import util.Transacional;

public class ConcentracaoPorcentagemService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<ConcentracaoPorcentagem> daoConcentracaoPorcentagem;
	
	@Transacional
	public void inserirAlterar(ConcentracaoPorcentagem concentracaoPorcentagem) {
		if(concentracaoPorcentagem.getId()==null) {
			daoConcentracaoPorcentagem.inserir(concentracaoPorcentagem);
		}else {
			daoConcentracaoPorcentagem.alterar(concentracaoPorcentagem);
		}
	}
	
	@Transacional
	public void update(String valor) {
		daoConcentracaoPorcentagem.update(valor);
	}

}
