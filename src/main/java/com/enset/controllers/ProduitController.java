package com.enset.controllers;

import com.enset.dto.CategorieDto;
import com.enset.dto.FournisseurDto;
import com.enset.dto.ProduitDto;
import com.enset.requests.*;
import com.enset.responses.ProduitResponse;
import com.enset.services.CategorieService;
import com.enset.services.FournisseurService;
import com.enset.services.ProduitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/produit")
public class ProduitController {

    @Autowired
    ProduitService produitService;
    @Autowired
    CategorieService categorieService;
    @Autowired
    FournisseurService fournisseurService;


    /*------------------------------------------------------------------------
    ---------------------------->> POST <<------------------------------------
    ----------------------->> /api/produit/add <<-----------------------------
    ------------------------------------------------------------------------*/

    @PostMapping(
            path = "/add",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    @ResponseBody
    public ResponseEntity<ProduitResponse> createProduit(
            @Valid ProduitRequest produitRequest
            ) throws Exception
    {
        CategorieDto categorieDto = categorieService.findCategorieByCodeCategorie(produitRequest.getCodeCategorie());
        FournisseurDto fournisseurDto = fournisseurService.getFournisseurByCode(produitRequest.getCodeFournisseur());
        ModelMapper modelMapper = new ModelMapper();
        ProduitDto produitDto = modelMapper.map(produitRequest, ProduitDto.class);
        produitDto.setCategorie(categorieDto);
        produitDto.setFournisseur(fournisseurDto);
        ProduitDto prod = produitService.createProduit(produitDto);
        return new ResponseEntity<>(modelMapper.map(prod, ProduitResponse.class), HttpStatus.CREATED);
    }


    /*------------------------------------------------------------------------
    ---------------------------->> GET <<-------------------------------------
    -------------------->> /api/produit/{codeProduit} <<----------------------
    ------------------------------------------------------------------------*/

    @GetMapping(
            path = "/{codeProduit}",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<ProduitResponse> getProduit(@PathVariable String codeProduit) throws Exception {
        ProduitDto produitDto = produitService.getProduitByCodeProd(codeProduit);
        ModelMapper modelMapper = new ModelMapper();
        return new ResponseEntity<>(modelMapper.map(produitDto, ProduitResponse.class), HttpStatus.OK);
    }


    /*------------------------------------------------------------------------
    ---------------------------->> PUT <<-------------------------------------
    ------------------->> /api/produit/{codeProduit} <<-----------------------
    ------------------------------------------------------------------------*/

    @PutMapping(
            path = "/update/{codeProduit}",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public @ResponseBody ResponseEntity<ProduitResponse> updateProduit(
            @PathVariable String codeProduit,
            @Valid ProduitRequest produitRequest,
            @RequestParam(name = "photos", required = false) List<MultipartFile> photos
    ) {
        ModelMapper modelMapper = new ModelMapper();
        ProduitDto produitDto = modelMapper.map(produitRequest, ProduitDto.class);
        ProduitDto updateProduit = null;
        try {
            updateProduit = produitService.updateProduit(codeProduit, produitDto, photos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(modelMapper.map(updateProduit, ProduitResponse.class), HttpStatus.ACCEPTED);
    }


    /*------------------------------------------------------------------------
    --------------------------->> DELETE <<-----------------------------------
    --------------------->> /api/produit/{codeProduit} <<---------------------
    ------------------------------------------------------------------------*/

    @DeleteMapping(path = "/{codeProduit}")
    public ResponseEntity<Object> deleProduit(@PathVariable String codeProduit) {
        try {
            produitService.deleteProduit(codeProduit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*------------------------------------------------------------------------
    ---------------------------->> GET <<-------------------------------------
    ----------------------->> /api/produit/ <<--------------------------------
    ------------------------------------------------------------------------*/

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<ProduitResponse>> getProduits(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ){
        List<ProduitResponse> produitResponse = new ArrayList<>();
        List<ProduitDto> produits = produitService.getProduits(page,size);
        for(ProduitDto produitDto:produits) {
            ModelMapper modelMapper = new ModelMapper();
            ProduitResponse produit = modelMapper.map(produitDto, ProduitResponse.class);
            produitResponse.add(produit);
        }
        return new ResponseEntity<>(produitResponse, HttpStatus.OK);
    }
}
