package com.enset.repositories;

import com.enset.entities.CategorieEntity;
import com.enset.entities.FournisseurEntity;
import com.enset.entities.ProduitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProduitRepository extends JpaRepository<ProduitEntity, Long> {
    Page<ProduitEntity> findByNomProdContains(Pageable pageable, String search, int status);
    Page<ProduitEntity> findByCategorie(CategorieEntity categorie, Pageable pageable);
    Page<ProduitEntity> findByFournisseur(FournisseurEntity fournisser, Pageable pageable);
    ProduitEntity findByCodeProd(String codeProduit);
}
