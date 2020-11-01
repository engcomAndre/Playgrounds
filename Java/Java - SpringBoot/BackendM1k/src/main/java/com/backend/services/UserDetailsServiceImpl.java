package com.backend.services;

import com.backend.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.entity.users.ApplicationUser;
import com.backend.repositories.ApplicationUserRepositorie;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ApplicationUserRepositorie applicationUserRepositorie;

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {

		ApplicationUser applicationUser = applicationUserRepositorie.findByCpf(cpf);

		if(applicationUser == null) {
			throw new UsernameNotFoundException(cpf);
		}
		return new UserSS(applicationUser.getId(),applicationUser.getCpf(),applicationUser.getPassword(),applicationUser.getAuthorities());
	}

}