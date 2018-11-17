package com.bolivariano.tarifas.enumerator;

public enum EstadoEnum {

	ACTIVO(1),
    INACTIVO(2);
	
	private int estado;
	
    private EstadoEnum(int estado) {
		this.estado = estado;
	}

    public int getEstado() {
        return estado;
    }
}
