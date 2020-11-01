package com.backend.entity.contact.enums;

public enum TypePhone {

	UNDEFINED(0,"UNDEFINED"),CELULAR(1, "CELULAR"), HOME(2, "HOME");

	private int cod;
	private String desc;

	private TypePhone(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}

	public static TypePhone toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TypePhone t : TypePhone.values()) {
			if (cod.equals(t.getCod())) {
				return t;
			}
		}
		throw new IllegalArgumentException("Id Inválido" + cod);

	}

}
