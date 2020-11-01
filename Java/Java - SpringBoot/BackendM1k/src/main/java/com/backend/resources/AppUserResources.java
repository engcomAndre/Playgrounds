package com.backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.users.ApplicationUser;
import com.backend.services.AppUserSevice;

@RestController
@RequestMapping("appuser")
public class AppUserResources {	
	
	@Autowired
	private AppUserSevice appUserSevice;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ApplicationUser>>  getAppUsers(){
		List<ApplicationUser> applicationUser = appUserSevice.getAppUsers();
		return ResponseEntity.ok().body(applicationUser);
	}
}
