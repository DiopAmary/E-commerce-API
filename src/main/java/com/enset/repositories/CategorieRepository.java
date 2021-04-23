package com.enset.repositories;

import com.enset.entities.CategorieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategorieRepository extends JpaRepository<CategorieEntity, Long> {

    @Query(value = "select * from categorie c where (c.libelle LIKE %:search%)", nativeQuery=true)
    Page<CategorieEntity> findCategorieByCritere(Pageable pageable, @Param("search") String search, @Param("status") int status);
    CategorieEntity findByCodeCategorie(String code);
}
