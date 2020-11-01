package com.backend.entity.contacts;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.backend.entity.contact.enums.TypePhone;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	private String countryCode;
	private String ddd;
	private TypePhone typePhone;
	private String phoneNumber;
	private Date updateDate;

	@ManyToOne
	@JoinColumn(name = "contact_id")
	@JsonIgnore
	private Contact contact;

	public Phone() {
		super();
	}
	
	public Phone(TypePhone typePhone,String countryCode,String ddd, String phoneNumber, Date updateDate,
			Contact contact) {
		super();
		
		this.typePhone = typePhone;
		this.countryCode = countryCode;
		this.ddd = ddd;
		this.phoneNumber = phoneNumber;
		this.updateDate = updateDate;
		this.contact = contact;		
	}
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public TypePhone getTypePhone() {
		return typePhone;
	}

	public void setTypePhone(TypePhone typePhone) {
		this.typePhone = typePhone;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
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
		Phone other = (Phone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
