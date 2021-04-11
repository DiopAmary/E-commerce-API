package com.enset.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enset.entities.Commande;
import com.enset.entities.Livraison;

public interface LivraisonRepository extends JpaRepository<Livraison,Long> {

	public Page<Livraison> findAll(Pageable pageable);
	public Page<Livraison> findByCodeLvr(String codeLvr,Pageable pageable);
	public Page<Livraison> findByDateLvr(Date dateLvr,Pageable pageable);
}
