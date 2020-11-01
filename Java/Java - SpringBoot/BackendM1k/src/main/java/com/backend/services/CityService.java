package com.backend.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.address.City;
import com.backend.repositories.CityRepositorie;

@Service
public class CityService {
	
	@Autowired
	private CityRepositorie cityRepositorie;
	
	public List<City> getCities(Integer id){
		List<City> cities = Arrays.asList(cityRepositorie.findById(id).orElse(null));		
		return cities;		
	}
	
	public List<City> getCities(){
		List<City> cities = cityRepositorie.findAll();		
		return cities;		
	}

}
