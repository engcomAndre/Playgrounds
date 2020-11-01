package com.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.contacts.Contact;
import com.backend.repositories.ContactRepositorie;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepositorie contactRepositorie;
	
	public List<Contact> getContacts()	{
		return contactRepositorie.findAll();
		
	}
	
	public List<Contact> getContacts(Integer id)	{
		List<Contact> contacts = new ArrayList<>();
		Optional<Contact> optContact = this.contactRepositorie.findById(id);
		contacts.add(optContact.orElse(null));
		return contacts;
		
	}

}
