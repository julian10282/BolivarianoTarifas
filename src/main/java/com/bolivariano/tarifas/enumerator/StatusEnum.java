package com.bolivariano.tarifas.enumerator;

public enum StatusEnum {

	SUCCESSS(1),
    FAIL(2),
    PENDING(3);
	
	private int type;
	
    private StatusEnum(int type) {
		this.type = type;
	}

    public int getStatusType() {
        return type;
    }
}
