package com.enset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.enset.entities.AddressEntity;
import com.enset.entities.RoleEntity;
import com.enset.entities.UserEntity;
import com.enset.repositories.AddressRepository;
import com.enset.repositories.LivreurRepository;
import com.enset.repositories.RoleRepository;

@SpringBootApplication
public class ProjetInnovationEcommerceApplication implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetInnovationEcommerceApplication.class, args);
	}
	
	
	@Bean
	public BCryptPasswordEncoder bCryptpassPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	

	@Override
	public void run(String... args) throws Exception {
		//roleRepository.save(new RoleEntity(null,"ADMIN",null));
		//roleRepository.save(new RoleEntity(null,"USER",null));
		
	}
	
}
