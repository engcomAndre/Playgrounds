package com.backend.repositories;

import com.backend.entity.contacts.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entity.users.ApplicationUser;

@Repository
public interface ApplicationUserRepositorie extends JpaRepository<ApplicationUser, Integer>{

    ApplicationUser findByCpf(String email);
}
