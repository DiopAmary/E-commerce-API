package com.enset.services.impl;

import com.enset.dto.ProduitDto;
import com.enset.dto.ProduitImagesDto;
import com.enset.entities.ProduitEntity;
import com.enset.entities.ProduitImagesEntity;
import com.enset.repositories.ProduitImagesRepository;
import com.enset.repositories.ProduitRepository;
import com.enset.services.ProduitImagesService;
import com.enset.services.ProduitService;
import com.enset.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProduitImagesServiceImpl implements ProduitImagesService
{
    @Autowired
    ProduitImagesRepository produitImagesRepository;
    @Autowired
    ProduitService produitService;
    @Autowired
    Utils utils;

    /*------------------------------------------------------------------------
    --------------------->> createProduitImage <<-----------------------------
    ------------------------------------------------------------------------*/
    @Override
    public ProduitImagesDto createProduitImage(MultipartFile image, ProduitDto produitDto) {
        ModelMapper modelMapper = new ModelMapper();
        ProduitImagesEntity produitImagesEntity = new ProduitImagesEntity();
        if(image != null) {
            produitImagesEntity.setProduit(modelMapper.map(produitDto, ProduitEntity.class));
            String uploadDir = "ecommerce-pi/produits-images/" + produitDto.getCodeProd();
            String fileName = image.getOriginalFilename();
            produitImagesEntity.setImage(uploadDir +"/"+ fileName);
            try {
                Utils.saveFile(uploadDir,fileName, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ProduitImagesEntity newImagesEntity = produitImagesRepository.save(produitImagesEntity);
        return modelMapper.map(newImagesEntity, ProduitImagesDto.class);
    }

    @Override
    public ProduitImagesDto updateProduitImage(ProduitImagesDto produitImageDto, MultipartFile image, ProduitDto produitDto) {
        return null;
    }


    /*------------------------------------------------------------------------
    --------------------->> deleteProduitImage <<-----------------------------
    ------------------------------------------------------------------------*/
    @Override
    public void deleteProduitImage(long id) throws Exception {
        ProduitImagesEntity produitImagesEntity = produitImagesRepository.findById(id);
        System.out.println("\n\n\n------------------------------------------------------------\n\n\n\n");
        System.out.println(produitImagesEntity);
        if(produitImagesEntity == null)
            throw new Exception("Cette images de produit n'existe pas !!!");

        try{
            produitImagesRepository.delete(produitImagesEntity);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Produit image DELETE : SUCCESS !!!");
    }

    /*------------------------------------------------------------------------
    --------------------->> findProduitImageByProduit <<----------------------
    ------------------------------------------------------------------------*/
    @Override
    public List<ProduitImagesDto> findProduitImageByProduit(String codeProduit) throws Exception {
        ProduitDto produitDto = produitService.getProduitByCodeProd(codeProduit);
        ModelMapper modelMapper = new ModelMapper();
        ProduitEntity produitEntity = modelMapper.map(produitDto, ProduitEntity.class);
        List<ProduitImagesEntity> produitImagesEntity = produitImagesRepository.findByProduit(produitEntity);
        List<ProduitImagesDto> produitImageDto = new ArrayList<>();
        for(ProduitImagesEntity p : produitImagesEntity){
            produitImageDto.add(modelMapper.map(p, ProduitImagesDto.class));
        }
        return produitImageDto;
    }
}
