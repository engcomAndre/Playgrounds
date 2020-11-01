package com.backend.resources.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.address.City;
import com.backend.services.CityService;

@RestController
@RequestMapping("city")
public class CityResource {
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<City>> getCities(){			
		return ResponseEntity.ok().body(cityService.getCities());
				
	}
}
