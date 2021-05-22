package com.enset.repositories;

import com.enset.entities.CategorieEntity;
import com.enset.entities.FournisseurEntity;
import com.enset.entities.ProduitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<ProduitEntity, Long> {
    List<ProduitEntity> findByCategorie(CategorieEntity categorie);
    List<ProduitEntity> findByFournisseur(FournisseurEntity fournisser);
    ProduitEntity findByCodeProd(String codeProduit);
    ProduitEntity findById(long id);
}
