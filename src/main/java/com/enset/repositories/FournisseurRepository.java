package com.enset.repositories;

import com.enset.entities.FournisseurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends JpaRepository<FournisseurEntity, Long> {
    FournisseurEntity findByCodeFournisseur(String codeFournisseur);
}
