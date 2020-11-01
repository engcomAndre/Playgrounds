package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.contacts.Email;

public interface EmailRepositorie extends JpaRepository<Email,Integer>{
	

}
