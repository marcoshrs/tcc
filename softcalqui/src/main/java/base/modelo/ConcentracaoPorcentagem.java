package base.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import base.controle.ConcentracaoPorcentagemMB;

@Entity
@Table(name = "concentracaoPorcentagem")
public class ConcentracaoPorcentagem {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String descricao;
	private Double concentracaoPercentual;
	private Double volumeSolucao;
	private Double purezaSoluto;
	private Double resultado;
	
	private boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getConcentracaoPercentual() {
		return concentracaoPercentual;
	}

	public void setConcentracaoPercentual(Double concentracaoPercentual) {
		this.concentracaoPercentual = concentracaoPercentual;
	}

	public Double getVolumeSolucao() {
		return volumeSolucao;
	}

	public void setVolumeSolucao(Double volumeSolucao) {
		this.volumeSolucao = volumeSolucao;
	}

	public Double getPurezaSoluto() {
		return purezaSoluto;
	}

	public void setPurezaSoluto(Double purezaSoluto) {
		this.purezaSoluto = purezaSoluto;
	}

	public Double getResultado() {
		return resultado;
	}

	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concentracaoPercentual == null) ? 0 : concentracaoPercentual.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((purezaSoluto == null) ? 0 : purezaSoluto.hashCode());
		result = prime * result + ((resultado == null) ? 0 : resultado.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((volumeSolucao == null) ? 0 : volumeSolucao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConcentracaoPorcentagem other = (ConcentracaoPorcentagem) obj;
		if (concentracaoPercentual == null) {
			if (other.concentracaoPercentual != null)
				return false;
		} else if (!concentracaoPercentual.equals(other.concentracaoPercentual))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (purezaSoluto == null) {
			if (other.purezaSoluto != null)
				return false;
		} else if (!purezaSoluto.equals(other.purezaSoluto))
			return false;
		if (resultado == null) {
			if (other.resultado != null)
				return false;
		} else if (!resultado.equals(other.resultado))
			return false;
		if (status != other.status)
			return false;
		if (volumeSolucao == null) {
			if (other.volumeSolucao != null)
				return false;
		} else if (!volumeSolucao.equals(other.volumeSolucao))
			return false;
		return true;
	}

	

}
