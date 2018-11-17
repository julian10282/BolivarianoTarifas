package com.bolivariano.tarifas.enumerator;

public enum PaymentTypeEnum {

	DOMESTIC_PAY(1),
    INTERNATIONAL_PAY(2),
    INTERNAL_PAY(3),
    MANUAL_PAY(4);
	
	private int type;
	
    private PaymentTypeEnum(int type) {
		this.type = type;
	}

    public int getPaymentType() {
        return type;
    }
}
