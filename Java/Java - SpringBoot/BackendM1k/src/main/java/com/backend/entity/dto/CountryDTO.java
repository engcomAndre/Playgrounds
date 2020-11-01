package com.backend.entity.dto;

import java.io.Serializable;

public class CountryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String countryName;

	public CountryDTO() {
	}

	public CountryDTO(String countryName) {
		super();
		this.countryName = countryName;
	}
	
//	public Integer getId() {
//		return id;
//	}
//	
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
}
