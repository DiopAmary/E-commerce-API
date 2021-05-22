package com.enset.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enset.entities.CommandeEntity;
import com.enset.entities.LigneCommandeEntity;
import com.enset.entities.ProduitEntity;
@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommandeEntity,Long>{
	public LigneCommandeEntity findByCodeLigne(String codeLigne);

	public Page<LigneCommandeEntity> findByCommande(CommandeEntity commande, Pageable pageable);
	
	public Page<LigneCommandeEntity> findByProduit(ProduitEntity produit, Pageable pageable);
}
