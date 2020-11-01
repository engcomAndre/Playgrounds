package com.backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.address.Address;
import com.backend.services.AddressService;

@RestController
@RequestMapping("address")
public class AddressResouces {
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Address>> getContacts() {
		return ResponseEntity.ok().body(this.addressService.getAddress());				
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<List<Address>> getContacts(@PathVariable Integer id) {
		return ResponseEntity.ok().body(this.addressService.getAddress(id));				
	}

}
