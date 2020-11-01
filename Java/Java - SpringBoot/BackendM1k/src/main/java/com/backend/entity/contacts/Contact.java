package com.backend.entity.contacts;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@OneToMany(mappedBy = "contact")
	private Set<Phone> phones = new HashSet<Phone>();

	@OneToMany(mappedBy = "contact")
	private Set<Email> emails = new HashSet<Email>();	
	
	public Contact() {
	}

	
	public Contact(Integer id, HashSet<Phone> phones, HashSet<Email> emails) {
		super();
		this.id = id;
		this.phones = phones;
		this.emails = emails;
	}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(HashSet<Phone> phones) {
		this.phones = phones;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(HashSet<Email> emails) {
		this.emails = emails;
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
		Contact other = (Contact) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
