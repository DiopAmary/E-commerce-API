package com.enset.controllers;

import com.enset.dto.ProduitDto;
import com.enset.dto.ProduitImagesDto;
import com.enset.requests.ProduitImagesRequest;
import com.enset.responses.ProduitImagesResponse;
import com.enset.services.ProduitImagesService;
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
@RequestMapping(value="/produit/images")
public class ProduitImagesController {
    @Autowired
    ProduitService produitService;
    @Autowired
    ProduitImagesService produitImagesService;

    @PostMapping(
            path = "/add",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    @ResponseBody
    public ResponseEntity<ProduitImagesResponse> createUser(
            @Valid ProduitImagesRequest produitImagesRequest,
            @RequestPart(name = "image", required = false) MultipartFile image
    ) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        ProduitDto produitDto = produitService.getProduitByCodeProd(produitImagesRequest.getCodeProduit());
        ProduitImagesDto produitImagesDto = produitImagesService.createProduitImage(image, produitDto);
        return new ResponseEntity<>(modelMapper.map(produitImagesDto, ProduitImagesResponse.class), HttpStatus.CREATED);
    }

    @GetMapping(
            path = "/{codeProduit}",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    @ResponseBody
    public ResponseEntity<List<ProduitImagesResponse>> getProduitImages(
            @Valid ProduitImagesRequest produitImagesRequest
    ) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        List<ProduitImagesDto> produitImagesDto = produitImagesService.findProduitImageByProduit(produitImagesRequest.getCodeProduit());
        List<ProduitImagesResponse> produitImagesResponses = new ArrayList<>();
        for(ProduitImagesDto p : produitImagesDto){
            produitImagesResponses.add(modelMapper.map(p, ProduitImagesResponse.class));
        }
        return new ResponseEntity<>(produitImagesResponses, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteProduitImages(@PathVariable(name = "id")long id) {
        try {
            produitImagesService.deleteProduitImage(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
