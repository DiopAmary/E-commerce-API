package com.enset.repositories;

import com.enset.entities.ProduitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProduitRepository extends JpaRepository<ProduitEntity, Long> {
    public Page<ProduitEntity> findByNomProdContains(String motCle, Pageable pageable);
    public Page<ProduitEntity> findByCategorie(Pageable pageable);
    public Page<ProduitEntity> findByFournisseur(Pageable pageable);
}
