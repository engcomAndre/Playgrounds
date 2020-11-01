package com.backend.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.address.Country;
import com.backend.entity.dto.CountryDTO;
import com.backend.repositories.CountryRepositorie;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepositorie countryRepositorie;
	
	public List<Country> getCountries(Integer id){
		List<Country> countries= Arrays.asList(countryRepositorie.findById(id).orElse(null));		
		return countries;		
	}
	
	public List<Country> getCountries(){
		List<Country> countries = countryRepositorie.findAll();
		return countries;
	}

	public Country insert(CountryDTO countryDTO) {
		Country country = new Country(countryDTO.getCountryName());
		country = countryRepositorie.save(country);
		return country;
	}

    public Country updtCountry(Integer id, CountryDTO countryDTO) {
		Country country = countryRepositorie.findById(id).orElse(null);
		country = countryRepositorie.save(country);
		return country;
    }
}
