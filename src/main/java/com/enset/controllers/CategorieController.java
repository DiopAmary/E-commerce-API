package com.enset.controllers;

import com.enset.dto.CategorieDto;
import com.enset.requests.CategorieRequest;
import com.enset.requests.ProduitRequest;
import com.enset.responses.CategorieResponse;
import com.enset.services.CategorieService;
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
@RequestMapping(value="/categorie")
public class CategorieController {

    @Autowired
    CategorieService categorieService;


    /*------------------------------------------------------------------------
    ---------------------------->> POST <<------------------------------------
    ----------------------->> /api/categorie/add <<-----------------------------
    ------------------------------------------------------------------------*/

    @PostMapping(
            path = "/add",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    @ResponseBody
    public ResponseEntity<CategorieResponse> createCategorie(
            @Valid ProduitRequest categorieRequest
    ){
        ModelMapper modelMapper = new ModelMapper();
        CategorieDto categorieDto = modelMapper.map(categorieRequest, CategorieDto.class);
        CategorieDto cat = categorieService.createCategorie(categorieDto);
        return new ResponseEntity<>(modelMapper.map(cat, CategorieResponse.class), HttpStatus.CREATED);
    }


    /*------------------------------------------------------------------------
    ---------------------------->> GET <<-------------------------------------
    -------------------->> /api/categorie/{codeCategorie} <<----------------------
    ------------------------------------------------------------------------*/

    @GetMapping(
            path = "/{codeCategorie}",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CategorieResponse> getCategorie(@PathVariable String codeCategorie) throws Exception {
        CategorieDto categorieDto = categorieService.findCategorieByCodeCategorie(codeCategorie);
        ModelMapper modelMapper = new ModelMapper();
        return new ResponseEntity<>(modelMapper.map(categorieDto, CategorieResponse.class), HttpStatus.OK);
    }


    /*------------------------------------------------------------------------
    ---------------------------->> PUT <<-------------------------------------
    ------------------->> /api/categorie/{codeCategorie} <<-----------------------
    ------------------------------------------------------------------------*/

    @PutMapping(
            path = "/update/{codeCategorie}",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public @ResponseBody ResponseEntity<CategorieResponse> updateCategorie(
            @PathVariable String codeCategorie,
            @Valid CategorieRequest categorieRequest
    ) {
        ModelMapper modelMapper = new ModelMapper();
        CategorieDto categorieDto = modelMapper.map(categorieRequest, CategorieDto.class);
        CategorieDto updateCategorie = null;
        try {
            updateCategorie = categorieService.updateCategorie(codeCategorie, categorieDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(modelMapper.map(updateCategorie, CategorieResponse.class), HttpStatus.ACCEPTED);
    }


    /*------------------------------------------------------------------------
    --------------------------->> DELETE <<-----------------------------------
    --------------------->> /api/categorie/{codeCategorie} <<---------------------
    ------------------------------------------------------------------------*/

    @DeleteMapping(path = "/{codeCategorie}")
    public ResponseEntity<Object> deleteCategorie(@PathVariable String codeCategorie) {
        try {
            categorieService.deleteCategorie(codeCategorie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*------------------------------------------------------------------------
    ---------------------------->> GET <<-------------------------------------
    ----------------------->> /api/categorie/ <<--------------------------------
    ------------------------------------------------------------------------*/

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<CategorieResponse>> getCategories(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "search",defaultValue = "") String search,
            @RequestParam(value = "status",defaultValue = "1") int status
    ){
        List<CategorieResponse> categorieResponse = new ArrayList<>();
        List<CategorieDto> categories = categorieService.getCategories(page,size,search,status);
        for(CategorieDto categorieDto : categories) {
            ModelMapper modelMapper = new ModelMapper();
            CategorieResponse categorie = modelMapper.map(categorieDto, CategorieResponse.class);
            categorieResponse.add(categorie);
        }
        return new ResponseEntity<>(categorieResponse, HttpStatus.OK);
    }
}
