package com.backend.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.address.Address;
import com.backend.repositories.AddressRepositorie;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepositorie addressRepositorie;
	
	public List<Address> getAddress(Integer id){
		List<Address> addresses = Arrays.asList(addressRepositorie.findById(id).orElse(null));
		return addresses;
	}
	
	public List<Address> getAddress(){
		List<Address> addresses = addressRepositorie.findAll();
		return addresses;
	}

}
