package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entity.address.Country;

@Repository
public interface CountryRepositorie extends JpaRepository<Country, Integer>{

}
