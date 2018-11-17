package com.bolivariano.tarifas.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarfias")
public class TarifaEntity {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "itinerarioId")
	private long itinerarioId;
	
	@Column(name = "precio")
	private double precio;

	@Column(name = "tipoViajero")
	private long tipoViajero;

	@Column(name = "fechaCreacion")
	private Date fechaCreacion;

	@Column(name = "fechaModificacion")
	private Date fechaModificacion;
	
	@Column(name = "estado")
	private int estado;

	public TarifaEntity() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getItinerarioId() {
		return itinerarioId;
	}

	public void setItinerarioId(long itinerarioId) {
		this.itinerarioId = itinerarioId;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public long getTipoViajero() {
		return tipoViajero;
	}

	public void setTipoViajero(long tipoViajero) {
		this.tipoViajero = tipoViajero;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "TarifaEntity [id=" + id + ", itinerarioId=" + itinerarioId + ", precio=" + precio + ", tipoViajero="
				+ tipoViajero + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion
				+ ", estado=" + estado + "]";
	}
	
}
