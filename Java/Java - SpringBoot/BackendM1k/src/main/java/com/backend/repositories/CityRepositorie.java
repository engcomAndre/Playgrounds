package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entity.address.City;

@Repository
public interface CityRepositorie extends JpaRepository<City, Integer>{

}
