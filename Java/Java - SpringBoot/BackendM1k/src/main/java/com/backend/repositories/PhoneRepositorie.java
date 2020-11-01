package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entity.contacts.Phone;

@Repository
public interface PhoneRepositorie extends JpaRepository<Phone, Integer>{

}
