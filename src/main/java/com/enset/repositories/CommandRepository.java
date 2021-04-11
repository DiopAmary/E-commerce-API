package com.enset.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import com.enset.entities.Commande;

public interface CommandRepository extends JpaRepository<Commande,Long>{
	
	public Page<Commande> findAll(Pageable pageable);
	public Commande findByEtatCmd(String etatCmd);
	public Page<Commande> findByDateCmd(Date dateCmd,Pageable pageable);
}
