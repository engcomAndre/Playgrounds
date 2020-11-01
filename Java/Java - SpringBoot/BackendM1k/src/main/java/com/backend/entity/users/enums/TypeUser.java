package com.backend.entity.users.enums;

public enum TypeUser {

	UNDEFINED(0,"ROLE_UNDEFINED"),ADMIN(1, "ROLE_ADMIN"), INSTRUCTOR(2, "ROLE_INSTRUCTOR"),HAPVIDA_USER(3,"ROLE_HAPVIDA_USER");

	private int cod;
	private String desc;

	private TypeUser(int cod, String desc) {
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
