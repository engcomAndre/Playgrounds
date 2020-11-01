package com.backend.entity.address;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.backend.entity.address.enums.TypeAddress;

@Entity
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String streetName;
	private String number;
	private String complement;
	private String districtName;
	private String postalCode;
	private String references;
	private TypeAddress typeAddress;

	@OneToOne
    @JoinColumn(name = "state_id")
	private State state;

	@OneToOne
	@JoinColumn(name = "city_id")
	private City city;

	@OneToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	public Address() {}

	public Address(String streetName, String number, String complement, String districtName, String postalCode,
			String references, TypeAddress typeAddress) {
		super();
		this.streetName = streetName;
		this.number = number;
		this.complement = complement;
		this.districtName = districtName;
		this.postalCode = postalCode;
		this.references = references;
		this.typeAddress = typeAddress;
	}

	public Address(String streetName, String number, String complement, String districtName, String postalCode,
			String references, TypeAddress typeAddress, City city, State state, Country country) {
		super();
		this.streetName = streetName;
		this.number = number;
		this.complement = complement;
		this.districtName = districtName;
		this.postalCode = postalCode;
		this.references = references;
		this.typeAddress = typeAddress;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getReferences() {
		return references;
	}

	public void setReferences(String references) {
		this.references = references;
	}

	public TypeAddress getTypeAddress() {
		return typeAddress;
	}

	public void setTypeAddress(TypeAddress typeAddress) {
		this.typeAddress = typeAddress;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
