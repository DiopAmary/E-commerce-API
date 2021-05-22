package com.enset.repositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.enset.entities.AddressEntity;
import com.enset.entities.LivreurEntity;
import com.enset.entities.UserEntity;

@Repository
public interface LivreurRepository extends JpaRepository<LivreurEntity, Long>{
	
	@Query(value = "select * from livreur",nativeQuery=true)
	public Page<LivreurEntity> findAll(Pageable pageable);
	
	public Page<LivreurEntity> findByNom(String nom, Pageable pageable);
	
	public Page<LivreurEntity> findByPrenom(String prenom, Pageable pageable);
	
	public LivreurEntity findByEmail(String email);
	
	public LivreurEntity findByCodeLivreur(String codeLivreur);
	
	//public LivreurEntity findByAddress(AddressEntity address);
}

