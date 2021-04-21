package com.enset.repositories;

import com.enset.entities.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<CategorieEntity, Long> {
    public CategorieEntity findByLibelle(String libelle);
}
