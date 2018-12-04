package base.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diluicao")
public class Diluicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String descricao;
	private Double concentracaoSolucaoPretendida;
	private Double volumeSolucaoPretendida;
	private Double concentracaoSolucaoInicial;
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

	public Double getConcentracaoSolucaoPretendida() {
		return concentracaoSolucaoPretendida;
	}

	public void setConcentracaoSolucaoPretendida(Double concentracaoSolucaoPretendida) {
		this.concentracaoSolucaoPretendida = concentracaoSolucaoPretendida;
	}

	public Double getVolumeSolucaoPretendida() {
		return volumeSolucaoPretendida;
	}

	public void setVolumeSolucaoPretendida(Double volumeSolucaoPretendida) {
		this.volumeSolucaoPretendida = volumeSolucaoPretendida;
	}

	public Double getConcentracaoSolucaoInicial() {
		return concentracaoSolucaoInicial;
	}

	public void setConcentracaoSolucaoInicial(Double concentracaoSolucaoInicial) {
		this.concentracaoSolucaoInicial = concentracaoSolucaoInicial;
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
		result = prime * result + ((concentracaoSolucaoInicial == null) ? 0 : concentracaoSolucaoInicial.hashCode());
		result = prime * result
				+ ((concentracaoSolucaoPretendida == null) ? 0 : concentracaoSolucaoPretendida.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((resultado == null) ? 0 : resultado.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((volumeSolucaoPretendida == null) ? 0 : volumeSolucaoPretendida.hashCode());
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
		Diluicao other = (Diluicao) obj;
		if (concentracaoSolucaoInicial == null) {
			if (other.concentracaoSolucaoInicial != null)
				return false;
		} else if (!concentracaoSolucaoInicial.equals(other.concentracaoSolucaoInicial))
			return false;
		if (concentracaoSolucaoPretendida == null) {
			if (other.concentracaoSolucaoPretendida != null)
				return false;
		} else if (!concentracaoSolucaoPretendida.equals(other.concentracaoSolucaoPretendida))
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
		if (resultado == null) {
			if (other.resultado != null)
				return false;
		} else if (!resultado.equals(other.resultado))
			return false;
		if (status != other.status)
			return false;
		if (volumeSolucaoPretendida == null) {
			if (other.volumeSolucaoPretendida != null)
				return false;
		} else if (!volumeSolucaoPretendida.equals(other.volumeSolucaoPretendida))
			return false;
		return true;
	}

	


}
