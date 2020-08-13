package cl.prueba.cnt.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name= "sismos")
public class Sismo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id 
	@Column(name="sismo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int sismoId;
	
	@Column(name="fecha")
	private String fecha; 
	
	@Column(name="magnitud")
    private String magnitud;
	
	@Column(name="lugar")
    private String lugar;
	
	@Column(name="tiempo")
    private String tiempo;
	
	@Column(name="urlInfo")
    private String urlInfo;
	
	@Column(name="estado")
    private String estado;
	
	@Column(name="coordenadas")
    private String coordenadas;
	
	

	public int getSismoId() {
		return sismoId;
	}

	public void setSismoId(int sismoId) {
		this.sismoId = sismoId;
	}

	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMagnitud() {
		return magnitud;
	}

	public void setMagnitud(String magnitud) {
		this.magnitud = magnitud;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getUrlInfo() {
		return urlInfo;
	}

	public void setUrlInfo(String urlInfo) {
		this.urlInfo = urlInfo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	@Override
	public String toString() {
		return "Sismo [sismoId=" + sismoId + ", fecha=" + fecha + ", magnitud=" + magnitud + ", lugar=" + lugar
				+ ", tiempo=" + tiempo + ", urlInfo=" + urlInfo + ", estado=" + estado + ", coordenadas=" + coordenadas
				+ "]";
	}

	
	
	

}
