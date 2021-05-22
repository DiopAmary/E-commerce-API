package com.enset.services.impl;

import com.enset.dto.FournisseurDto;
import com.enset.entities.FournisseurEntity;
import com.enset.repositories.FournisseurRepository;
import com.enset.services.FournisseurService;
import com.enset.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class FournisseurServiceImpl implements FournisseurService {

    @Autowired
    Utils utils;
    @Autowired
    FournisseurRepository fournisseurRepository;

    /*------------------------------------------------------------------------
    ----------------------->> createFournisseur <<----------------------------
    ------------------------------------------------------------------------*/

    @Override
    public FournisseurDto createFournisseur(FournisseurDto fournisseurDto) {
        ModelMapper modelMapper = new ModelMapper();
        String codeFournisseur = utils.generateStringId(30);
        fournisseurDto.setCodeFournisseur(codeFournisseur);
        FournisseurEntity fournisseurEntity = modelMapper.map(fournisseurDto, FournisseurEntity.class);
        FournisseurEntity fournisseur = fournisseurRepository.save(fournisseurEntity);
        return modelMapper.map(fournisseur, FournisseurDto.class);
    }


    /*------------------------------------------------------------------------
    --------------------->> getFournisseurByCode <<---------------------------
    ------------------------------------------------------------------------*/

    @Override
    public FournisseurDto getFournisseurByCode(String codeFournisseur) throws Exception {
        FournisseurEntity fournisseurEntity = fournisseurRepository.findByCodeFournisseur(codeFournisseur);
        if(fournisseurEntity == null)
            throw new Exception();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(fournisseurEntity, FournisseurDto.class);
    }


    /*------------------------------------------------------------------------
    ---------------------->> UpdateFournisseur <<-----------------------------
    ------------------------------------------------------------------------*/

    @Override
    public FournisseurDto UpdateFournisseur(String codeFournisseur, FournisseurDto fournisseurDto) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        FournisseurEntity fournisseurEntity = fournisseurRepository.findByCodeFournisseur(codeFournisseur);
        if (fournisseurEntity != null){
            long id = fournisseurEntity.getId();
            fournisseurDto.setId(id);
            fournisseurDto.setCodeFournisseur(codeFournisseur);
            fournisseurEntity = modelMapper.map(fournisseurDto, FournisseurEntity.class);
            FournisseurEntity nouveauFournisseur = fournisseurRepository.save(fournisseurEntity);
            return modelMapper.map(nouveauFournisseur, FournisseurDto.class);
        }else
            throw new Exception("Ce Fournisseur n'existe pas");
    }


    /*------------------------------------------------------------------------
    ------------------------>> deleteFournisseur <<---------------------------
    ------------------------------------------------------------------------*/

    @Override
    public void deleteFournisseur(String codeFournisseur) throws Exception {
        FournisseurEntity fournisseurEntity = fournisseurRepository.findByCodeFournisseur(codeFournisseur);
        if (fournisseurEntity == null)
            throw new Exception("Ce fournisseur n'existe pas !!!");
        try {
            fournisseurRepository.delete(fournisseurEntity);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Fournisseur supprimé avec succés !!!");
    }


    /*------------------------------------------------------------------------
    ------------------------->> getFournisseurs <<----------------------------
    ------------------------------------------------------------------------*/

    @Override
    public List<FournisseurDto> getFournisseurs(int page, int size) {
        if (page > 0)
            page -= 1;

        List<FournisseurDto> fournisseurDto = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, size);

        Page<FournisseurEntity> fournisseurs;
        fournisseurs = fournisseurRepository.findAll(pageableRequest);
        ModelMapper modelMapper  =new ModelMapper();
        for (FournisseurEntity fournisseurEntity : fournisseurs) {
            FournisseurDto fournisseur =modelMapper.map(fournisseurEntity, FournisseurDto.class);
            fournisseurDto.add(fournisseur);
        }
        return fournisseurDto;
    }
}
