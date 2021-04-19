package com.enset.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.enset.entities.AddressEntity;
import com.enset.entities.Livreur;

public interface LivreurRepository extends JpaRepository<Livreur, Long>{
	
	public Page<Livreur> findAll(Pageable pageable);
	
	public Page<Livreur> findByNom(String nom, Pageable pageable);
	
	public Page<Livreur> findByPrenom(String prenom, Pageable pageable);
	
	public Livreur findByEmail(String email);
	
	public Livreur findByCodeLivreur(String codeLivreur);
	
	public Livreur findByAddress(AddressEntity address);
}
