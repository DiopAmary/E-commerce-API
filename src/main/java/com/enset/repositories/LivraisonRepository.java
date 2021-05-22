package com.enset.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enset.entities.LivraisonEntity;
import com.enset.entities.LivreurEntity;

@Repository
public interface LivraisonRepository extends JpaRepository<LivraisonEntity,Long> {

	public Page<LivraisonEntity> findAll(Pageable pageable);
	public LivraisonEntity findByCodeLvr(String codeLvr);
	public Page<LivraisonEntity> findByDateLvr(Date dateLvr,Pageable pageable);
	public Page<LivraisonEntity> findByLivreur(LivreurEntity livreur,Pageable pageable);
}

