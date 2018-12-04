package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.ConcentracaoMolar;
import dao.GenericDAO;
import util.Transacional;

public class ConcentracaoMolarService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<ConcentracaoMolar> daoConcentracaoMolar;
	
	@Transacional
	public void inserirAlterar(ConcentracaoMolar concentracaoMolar) {
		if(concentracaoMolar.getId()==null) {
			daoConcentracaoMolar.inserir(concentracaoMolar);
		}else {
			daoConcentracaoMolar.alterar(concentracaoMolar);
		}
	}
	
	@Transacional
	public void update(String valor) {
		daoConcentracaoMolar.update(valor);
	}

}
