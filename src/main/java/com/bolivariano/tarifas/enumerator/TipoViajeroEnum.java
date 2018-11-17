package com.bolivariano.tarifas.enumerator;

public enum TipoViajeroEnum {

	SILVER(1),
    GOLD(2),
    PLATINUM(3);
	
	private int tipo;
	
    private TipoViajeroEnum(int tipo) {
		this.tipo = tipo;
	}

    public int getTipo() {
        return tipo;
    }
}
