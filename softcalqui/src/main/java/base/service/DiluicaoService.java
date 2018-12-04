package base.service;

import java.io.Serializable;

import javax.inject.Inject;

import base.modelo.Diluicao;
import dao.GenericDAO;
import util.Transacional;

public class DiluicaoService implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Inject
	private GenericDAO<Diluicao> daoDiluicao;
	
	@Transacional
	public void inserirAlterar(Diluicao diluicao){
		if(diluicao.getId()==null){
			daoDiluicao.inserir(diluicao);
		}else{
			daoDiluicao.alterar(diluicao);
		}
	}
	
	@Transacional
	public void update(String valor){
		daoDiluicao.update(valor);
	}
}
