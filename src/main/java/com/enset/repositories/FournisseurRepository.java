package com.enset.repositories;

import com.enset.entities.FournisseurEntity;
import com.enset.entities.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<FournisseurEntity, Long> {
    public FournisseurEntity findByNomFournisseur(String nomFournisseur);
}
