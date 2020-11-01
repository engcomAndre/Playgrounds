package com.backend.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.entity.users.ApplicationUser;
import com.backend.repositories.ApplicationUserRepositorie;

@Service
public class AuthService {

	@Autowired
	private ApplicationUserRepositorie  applicationUserRepositorie;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
			
//	@Autowired
//	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewpassword(String email) {
		ApplicationUser applicationUser = null;
//		ApplicationUser applicationUser =  applicationUserRepositorie.findByEmail(email);
		
//		if(applicationUser == null) {
//			throw new ObjectNotFoundException("Email não encontrado.");
//		}
		
		String newPass = newPassword();
//		applicationUser.setSenha(bCryptPasswordEncoder.encode(newPass));
		
		applicationUserRepositorie.save(applicationUser);
		
//		emailService.sendNewPasswordEmail(cliente,newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);

		switch (opt) {
			case 0:return (char)(rand.nextInt(10) + 48);	
			case 1:return (char)(rand.nextInt(26) + 65);	
		   default:return (char)(rand.nextInt(26) + 97);	
		}
	}

}