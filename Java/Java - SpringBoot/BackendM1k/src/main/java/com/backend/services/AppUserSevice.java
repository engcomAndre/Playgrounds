package com.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.users.ApplicationUser;
import com.backend.repositories.ApplicationUserRepositorie;

@Service
public class AppUserSevice {
	
	@Autowired
	private ApplicationUserRepositorie applicationUserRepositorie;
	
	public ApplicationUser getAppUserById(Integer id) {
		ApplicationUser applicationUser = applicationUserRepositorie.findById(id).orElse(null);		
		return applicationUser;
	}

	public List<ApplicationUser> getAppUsers() {
		List<ApplicationUser> applicationUsers = applicationUserRepositorie.findAll();		
		return applicationUsers;
	}
	
	
	

	

}
