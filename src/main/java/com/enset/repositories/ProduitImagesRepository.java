package com.enset.repositories;

import com.enset.entities.ProduitEntity;
import com.enset.entities.ProduitImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitImagesRepository extends JpaRepository<ProduitImagesEntity, Long> {
    public ProduitImagesEntity findByProduit(ProduitEntity produit);
}
