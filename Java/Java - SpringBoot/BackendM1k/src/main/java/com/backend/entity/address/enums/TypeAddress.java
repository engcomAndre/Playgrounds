package com.backend.entity.address.enums;

import com.backend.entity.users.enums.TypeUser;

public enum TypeAddress {

	UNDEFINED(0,"UNDEFINED"),RESIDENTIAL(1, "RESIDENTIAL"), BUSINNESS(2, "BUSINNESS");

	private int cod;
	private String desc;

	private TypeAddress(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}

	public static TypeUser toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TypeUser t : TypeUser.values()) {
			if (cod.equals(t.getCod())) {
				return t;
			}
		}
		throw new IllegalArgumentException("Id Inválido" + cod);

	}

}
