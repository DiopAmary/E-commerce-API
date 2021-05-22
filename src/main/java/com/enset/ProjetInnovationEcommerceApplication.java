package com.enset;

import com.enset.entities.CategorieEntity;
import com.enset.entities.ProduitEntity;
import com.enset.entities.RoleEntity;
import com.enset.repositories.CategorieRepository;
import com.enset.repositories.FournisseurRepository;
import com.enset.repositories.ProduitRepository;
import com.enset.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.enset.repositories.RoleRepository;

@SpringBootApplication
public class ProjetInnovationEcommerceApplication implements CommandLineRunner{
	@Autowired
	Utils util;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private FournisseurRepository fournisseurRepository;
	
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
		//String code = util.generateStringId(20);
		//categorieRepository.save(new CategorieEntity(0, code, "Ordinateur","Description Ordinateurs portables"));
		//roleRepository.save(new RoleEntity(null,"ADMIN",null));
		//roleRepository.save(new RoleEntity(null,"USER",null));
		

		//roleRepository.save(new RoleEntity(null,"ADMIN","role admin"));
		//roleRepository.save(new RoleEntity(null,"USER","role user"));
	}
	
}
