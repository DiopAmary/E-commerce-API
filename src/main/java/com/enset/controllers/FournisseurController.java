package com.enset.controllers;

import com.enset.dto.FournisseurDto;
import com.enset.requests.FournisseurRequest;
import com.enset.responses.FournisseurResponse;
import com.enset.services.FournisseurService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/fournisseur")
public class FournisseurController {
    @Autowired
    FournisseurService fournisseurService;

    /*------------------------------------------------------------------------
    ---------------------------->> POST <<------------------------------------
    ----------------------->> /api/fournisseur/add <<-------------------------
    ------------------------------------------------------------------------*/

    @PostMapping(
            path = "/add",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    @ResponseBody
    public ResponseEntity<FournisseurResponse> addFournisseur(
            @Valid FournisseurRequest fournisseurRequest
    ){
        ModelMapper modelMapper = new ModelMapper();
        FournisseurDto fournisseurDto = modelMapper.map(fournisseurRequest, FournisseurDto.class);
        FournisseurDto fournisseur = fournisseurService.createFournisseur(fournisseurDto);
        return new ResponseEntity<>(modelMapper.map(fournisseur, FournisseurResponse.class), HttpStatus.CREATED);
    }


    /*------------------------------------------------------------------------
    ---------------------------->> GET <<-------------------------------------
    -------------------->> /api/fournisseur/{codeFournisseur} <<----------------------
    ------------------------------------------------------------------------*/

    @GetMapping(
            path = "/{codeFournisseur}",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<FournisseurResponse> getFournisseur(@PathVariable String codeFournisseur) throws Exception {
        FournisseurDto fournisseurDto = fournisseurService.getFournisseurByCode(codeFournisseur);
        ModelMapper modelMapper = new ModelMapper();
        return new ResponseEntity<>(modelMapper.map(fournisseurDto, FournisseurResponse.class), HttpStatus.OK);
    }



    /*------------------------------------------------------------------------
    ---------------------------->> PUT <<-------------------------------------
    ------------------->> /api/fournisseur/{codeFournisseur} <<-----------------------
    ------------------------------------------------------------------------*/

    @PutMapping(
            path = "/update/{codeFournisseur}",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public @ResponseBody ResponseEntity<FournisseurResponse> updateFournisseur(
            @PathVariable String codeFournisseur,
            @Valid FournisseurRequest fournisseurRequest
    ) {
        ModelMapper modelMapper = new ModelMapper();
        FournisseurDto fournisseurDto = modelMapper.map(fournisseurRequest, FournisseurDto.class);
        FournisseurDto updateFournisseur = null;
        try {
            updateFournisseur = fournisseurService.UpdateFournisseur(codeFournisseur, fournisseurDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(modelMapper.map(updateFournisseur, FournisseurResponse.class), HttpStatus.ACCEPTED);
    }



    /*------------------------------------------------------------------------
    --------------------------->> DELETE <<-----------------------------------
    --------------------->> /api/fournisseur/{codeFournisseur} <<---------------------
    ------------------------------------------------------------------------*/

    @DeleteMapping(path = "/{codeFournisseur}")
    public ResponseEntity<Object> deleteFournisseur(@PathVariable String codeFournisseur) {
        try {
            fournisseurService.deleteFournisseur(codeFournisseur);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*------------------------------------------------------------------------
    ---------------------------->> GET <<-------------------------------------
    ----------------------->> /api/fournisseur/ <<--------------------------------
    ------------------------------------------------------------------------*/

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<FournisseurResponse>> getFournisseurs(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ){
        List<FournisseurResponse> fournisseurResponse = new ArrayList<>();
        List<FournisseurDto> fournisseurs = fournisseurService.getFournisseurs(page,size);
        for(FournisseurDto fournisseurDto:fournisseurs) {
            ModelMapper modelMapper = new ModelMapper();
            FournisseurResponse fournisseur = modelMapper.map(fournisseurDto, FournisseurResponse.class);
            fournisseurResponse.add(fournisseur);
        }
        return new ResponseEntity<>(fournisseurResponse, HttpStatus.OK);
    }
}
