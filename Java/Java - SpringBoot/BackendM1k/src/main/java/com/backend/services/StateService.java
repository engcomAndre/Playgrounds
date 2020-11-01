package com.backend.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.address.State;
import com.backend.repositories.StateRepositorie;

@Service
public class StateService {
	
	@Autowired
	private StateRepositorie stateRepositorie;
	
	public List<State> getCities(Integer id){
		List<State> states = Arrays.asList(stateRepositorie.findById(id).orElse(null));		
		return states;		
	}
	
	public List<State> getCities(){
		List<State> states= stateRepositorie.findAll();		
		return states;		
	}

}
