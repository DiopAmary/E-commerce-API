package com.enset.repositories;

import com.enset.entities.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<CategorieEntity, Long> {


    CategorieEntity findByCodeCategorie(String code);
}
