package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entity.contacts.Contact;

@Repository
public interface ContactRepositorie extends JpaRepository<Contact, Integer>{
	
}
