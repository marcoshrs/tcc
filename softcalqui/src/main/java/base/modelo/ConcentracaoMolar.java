package base.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "concentracaoMolar")
public class ConcentracaoMolar {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String descricao;
	private Double concentracaoPretendida;
	private Double volume;
	private Double massaMolecularSoluto;
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

	public Double getConcentracaoPretendida() {
		return concentracaoPretendida;
	}

	public void setConcentracaoPretendida(Double concentracaoPretendida) {
		this.concentracaoPretendida = concentracaoPretendida;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getMassaMolecularSoluto() {
		return massaMolecularSoluto;
	}

	public void setMassaMolecularSoluto(Double massaMolecularSoluto) {
		this.massaMolecularSoluto = massaMolecularSoluto;
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
		result = prime * result + ((concentracaoPretendida == null) ? 0 : concentracaoPretendida.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((massaMolecularSoluto == null) ? 0 : massaMolecularSoluto.hashCode());
		result = prime * result + ((purezaSoluto == null) ? 0 : purezaSoluto.hashCode());
		result = prime * result + ((resultado == null) ? 0 : resultado.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
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
		ConcentracaoMolar other = (ConcentracaoMolar) obj;
		if (concentracaoPretendida == null) {
			if (other.concentracaoPretendida != null)
				return false;
		} else if (!concentracaoPretendida.equals(other.concentracaoPretendida))
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
		if (massaMolecularSoluto == null) {
			if (other.massaMolecularSoluto != null)
				return false;
		} else if (!massaMolecularSoluto.equals(other.massaMolecularSoluto))
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
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}
	
	

	

}
