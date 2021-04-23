package com.enset.services.impl;

import com.enset.dto.ProduitDto;
import com.enset.dto.ProduitImagesDto;
import com.enset.entities.ProduitEntity;
import com.enset.repositories.ProduitRepository;
import com.enset.services.ProduitService;
import com.enset.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProduitServiceImp implements ProduitService {

    @Autowired
    Utils utils;
    @Autowired
    ProduitRepository produitRepository;

    /*------------------------------------------------------------------------
    ----------------------->> createProduit <<--------------------------------
    ------------------------------------------------------------------------*/


    @Override
    public ProduitDto createProduit(ProduitDto produitDto, List<MultipartFile> photos) {
        ModelMapper modelMapper = new ModelMapper();
        String codeProduit = utils.generateStringId(30);
        produitDto.setCodeProd(codeProduit);
        double tva = produitDto.getPrixVente()*0.2;
        produitDto.setTva(tva);
        if(!photos.isEmpty()){
            int i=0;
            for(MultipartFile photo : photos){
                String uploadDir = "ecommerce-pi/produit-images/" + produitDto.getCodeProd();
                String fileName = photo.getOriginalFilename();
                try {
                    Utils.saveFile(uploadDir,fileName, photo);
                    ProduitImagesDto image= new ProduitImagesDto(0, photo.getOriginalFilename(), produitDto);
                    produitDto.getProduitImages().set(i, image);
                    i++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ProduitEntity produitEntity = modelMapper.map(produitDto, ProduitEntity.class);
        ProduitEntity produit = produitRepository.save(produitEntity);
        return modelMapper.map(produit, ProduitDto.class);
    }


    /*-------------------------------------------------------------------------------
    ----------------------->> getProduitByCodeProd <<--------------------------------
    -------------------------------------------------------------------------------*/


    @Override
    public ProduitDto getProduitByCodeProd(String codeProd) throws Exception {
        ProduitEntity produitEntity = produitRepository.findByCodeProd(codeProd);
        if(produitEntity == null)
            throw new Exception();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(produitEntity, ProduitDto.class);
    }


    /*------------------------------------------------------------------------
    ----------------------->> updateProduit <<--------------------------------
    ------------------------------------------------------------------------*/


    @Override
    public ProduitDto updateProduit(String codeProd, ProduitDto produitDto, List<MultipartFile> photos) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        ProduitEntity produitEntity = produitRepository.findByCodeProd(codeProd);
        if (produitEntity != null){
            Date dateCreation = produitEntity.getCreatedAt();
            long id = produitEntity.getId();
            double tva = produitDto.getPrixVente() * 0.2;
            produitDto.setId(id);
            produitDto.setCodeProd(codeProd);
            produitDto.setCreatedAt(dateCreation);
            produitDto.setTva(tva);

            if(!photos.isEmpty()){
                int i=0;
                for(MultipartFile photo : photos){
                    String uploadDir = "ecommerce-pi/produit-images/" + produitDto.getCodeProd();
                    String fileName = photo.getOriginalFilename();
                    try {
                        Utils.saveFile(uploadDir,fileName, photo);
                        ProduitImagesDto image= new ProduitImagesDto(0, photo.getOriginalFilename(), produitDto);
                        produitDto.getProduitImages().set(i, image);
                        i++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            produitEntity = modelMapper.map(produitDto, ProduitEntity.class);
            ProduitEntity nouveauProduit = produitRepository.save(produitEntity);
            return modelMapper.map(nouveauProduit, ProduitDto.class);
        }else
            throw new Exception("Ce produit n'existe pas");
    }


    /*------------------------------------------------------------------------
    ----------------------->> deleteProduit <<--------------------------------
    ------------------------------------------------------------------------*/


    @Override
    public void deleteProduit(String codeProd) throws Exception {
        ProduitEntity produitEntity = produitRepository.findByCodeProd(codeProd);
        if (produitEntity == null)
            throw new Exception("Ce produit n'existe pas !!!");
        try {
            produitRepository.delete(produitEntity);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Produit supprimé avec succés !!!");
    }


    /*----------------------------------------------------------------------
    ----------------------->> getProduits <<--------------------------------
    ----------------------------------------------------------------------*/


    @Override
    public List<ProduitDto> getProduits(int page, int size, String search, int status) {
        if (page > 0)
            page -= 1;

        List<ProduitDto> produitDtos = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, size);

        Page<ProduitEntity> produits;
        if(search.isEmpty()) {
            produits = produitRepository.findAll(pageableRequest);
        }else {
            produits = produitRepository.findByNomProdContains(pageableRequest, search, status);
        }
        ModelMapper modelMapper  =new ModelMapper();
        for (ProduitEntity produitEntity : produits) {
            ProduitDto produit =modelMapper.map(produitEntity, ProduitDto.class);
            produitDtos.add(produit);
        }
        return produitDtos;
    }
}
