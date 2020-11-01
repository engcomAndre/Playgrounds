package com.backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.contacts.Contact;
import com.backend.services.ContactService;


@RestController
@RequestMapping("contact")
public class ContactResources {
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> getContacts() {
		return ResponseEntity.ok().body(this.contactService.getContacts());				
	}
}
