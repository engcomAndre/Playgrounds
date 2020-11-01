package com.backend.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.backend.services.DBService;


@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String stratetgy;
	
	@Bean
	public boolean instateateDatabase() throws ParseException{
		if(!"create".equals(stratetgy)) {
			return false;			
		}
		dbService.instantiateTestDatabase();
		return true;
	}

}
