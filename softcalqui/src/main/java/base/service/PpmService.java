package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.Ppm;
import dao.GenericDAO;
import util.Transacional;

public class PpmService implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Ppm> daoPpm;
	
	@Transacional
	public void inserirAlterar(Ppm ppm){
		if(ppm.getId()==null){
			daoPpm.inserir(ppm);
		}else{
			daoPpm.alterar(ppm);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoPpm.update(valor);
	}

}
