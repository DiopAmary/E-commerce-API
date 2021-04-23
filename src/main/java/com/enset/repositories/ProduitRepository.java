package com.enset.repositories;

import com.enset.entities.CategorieEntity;
import com.enset.entities.FournisseurEntity;
import com.enset.entities.ProduitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProduitRepository extends JpaRepository<ProduitEntity, Long> {
    @Query(value = "select * from produit p where (p.nomProd LIKE %:search%)", nativeQuery=true)
    Page<ProduitEntity> findProduitByCritere(Pageable pageable, @Param("search") String search, @Param("status") int status);
    List<ProduitEntity> findByCategorie(CategorieEntity categorie);
    List<ProduitEntity> findByFournisseur(FournisseurEntity fournisser);
    ProduitEntity findByCodeProd(String codeProduit);
}
