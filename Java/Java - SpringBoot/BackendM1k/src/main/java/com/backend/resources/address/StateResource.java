package com.backend.resources.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.address.State;
import com.backend.services.StateService;

@RestController
@RequestMapping("state")
public class StateResource {
	
	@Autowired
	private StateService StateService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<State>> getCities(){			
		return ResponseEntity.ok().body(StateService.getCities());
				
	}
}
