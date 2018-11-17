package com.bolivariano.tarifas.modelos;

public class Tarifa {

	private long itinerarioId;
	
	private double precio;

	private long tipoViajero;
	
	private int estado;

	public Tarifa() {
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Tarifas [itinerarioId=" + itinerarioId + ", precio=" + precio + ", tipoViajero=" + tipoViajero
				+ ", estado=" + estado + "]";
	}
	
}
