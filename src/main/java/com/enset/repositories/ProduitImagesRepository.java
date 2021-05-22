package com.enset.repositories;

import com.enset.entities.ProduitEntity;
import com.enset.entities.ProduitImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitImagesRepository extends JpaRepository<ProduitImagesEntity, Long> {
    public List<ProduitImagesEntity> findByProduit(ProduitEntity produit);
    public ProduitImagesEntity findById(long id);
}
