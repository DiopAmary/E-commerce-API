package com.enset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjetInnovationEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetInnovationEcommerceApplication.class, args);
	}
	
	
	@Bean
	public BCryptPasswordEncoder bCryptpassPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
