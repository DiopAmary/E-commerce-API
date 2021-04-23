package com.enset.services.impl;

import com.enset.dto.CategorieDto;
import com.enset.dto.ProduitDto;
import com.enset.entities.CategorieEntity;
import com.enset.entities.ProduitEntity;
import com.enset.repositories.CategorieRepository;
import com.enset.repositories.ProduitRepository;
import com.enset.services.CategorieService;
import com.enset.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class CategorieServiceImpl implements CategorieService {
    @Autowired
    Utils utils;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    ProduitRepository produitRepository;


    /*------------------------------------------------------------------------
    ----------------------->> createCategorie <<------------------------------
    ------------------------------------------------------------------------*/

    @Override
    public CategorieDto createCategorie(CategorieDto categorieDto) {
        ModelMapper modelMapper = new ModelMapper();
        String codeCategorie = utils.generateStringId(30);
        categorieDto.setCodeCategorie(codeCategorie);
        CategorieEntity categorieEntity = modelMapper.map(categorieDto, CategorieEntity.class);
        CategorieEntity categorie = categorieRepository.save(categorieEntity);
        return modelMapper.map(categorie, CategorieDto.class);
    }


    /*------------------------------------------------------------------------
    -------------------->> findCategorieByCodeCategorie <<--------------------
    ------------------------------------------------------------------------*/

    @Override
    public CategorieDto findCategorieByCodeCategorie(String code) throws Exception {
        CategorieEntity categorieEntity = categorieRepository.findByCodeCategorie(code);
        if (categorieEntity == null){
            throw new Exception("Cette catégorie n'existe pas !!!");
        }else{
            List<ProduitEntity> produits = produitRepository.findByCategorie(categorieEntity);
            categorieEntity.setProduits(produits);
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(categorieEntity, CategorieDto.class);
        }
    }


    /*------------------------------------------------------------------------
    ----------------------->> updateCategorie <<--------------------------------
    ------------------------------------------------------------------------*/

    @Override
    public CategorieDto updateCategorie(String codeCategorie, CategorieDto categorieDto) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        CategorieEntity categorieEntity = categorieRepository.findByCodeCategorie(codeCategorie);
        if (categorieEntity != null){
            long id = categorieEntity.getId();
            categorieDto.setId(id);
            categorieDto.setCodeCategorie(codeCategorie);

            categorieEntity = modelMapper.map(categorieDto, CategorieEntity.class);
            CategorieEntity nouvelleCategorie = categorieRepository.save(categorieEntity);
            return modelMapper.map(nouvelleCategorie, CategorieDto.class);
        }else
            throw new Exception("Cette catégorie n'existe pas");
    }


    /*------------------------------------------------------------------------
    ----------------------->> deleteCategorie <<------------------------------
    ------------------------------------------------------------------------*/

    @Override
    public void deleteCategorie(String codeCategorie) throws Exception {
        CategorieEntity categorieEntity = categorieRepository.findByCodeCategorie(codeCategorie);
        if (categorieEntity == null)
            throw new Exception("Cette catégorie n'existe pas !!!");

        try {
            categorieRepository.delete(categorieEntity);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Catégorie supprimée avec succés !!!");
    }


    /*------------------------------------------------------------------------
    ----------------------->> getCategories<<---------------------------------
    ------------------------------------------------------------------------*/

    @Override
    public List<CategorieDto> getCategories(int page, int size, String search, int status) {
        if (page > 0)
            page -= 1;

        List<CategorieDto> categorieDto = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, size);

        Page<CategorieEntity> categories;
        if(search.isEmpty()) {
            categories = categorieRepository.findAll(pageableRequest);
        }else {
            categories = categorieRepository.findCategorieByCritere(pageableRequest, search, status);
        }
        ModelMapper modelMapper  =new ModelMapper();
        for (CategorieEntity categorieEntity : categories) {
            CategorieDto categorie =modelMapper.map(categorieEntity, CategorieDto.class);
            categorieDto.add(categorie);
        }
        return categorieDto;
    }
}
