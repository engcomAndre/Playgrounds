package com.backend.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.entity.users.enums.TypeUser;

public class UserSS implements UserDetails {	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String senha;	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {}
	
	public UserSS(Integer id, String email, String senha, Set<TypeUser>typeUsers) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = typeUsers.stream()
								 .map(x -> new SimpleGrantedAuthority(x.getDesc()))
								 .collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasRole(TypeUser typeUser) {
		return getAuthorities().contains(new SimpleGrantedAuthority(typeUser.getDesc()));
	}

}