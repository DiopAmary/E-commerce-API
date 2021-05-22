package com.enset.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enset.dto.CommandeDto;
import com.enset.dto.LigneCommandeDto;
import com.enset.dto.ProduitDto;
import com.enset.requests.LigneCommandeRequest;
import com.enset.responses.CommandeResponse;
import com.enset.responses.LigneCommandeResponse;
import com.enset.responses.LivraisonResponse;
import com.enset.services.CommandeService;
import com.enset.services.LigneCommandeService;
import com.enset.services.ProduitService;

@RestController
@RequestMapping ("/lignes")
public class LigneCommandeController {

	@Autowired
	LigneCommandeService ligneService;
	
	@Autowired
	ProduitService produitService;
	
	@Autowired
	CommandeService commandeService;
	
	public ResponseEntity<LigneCommandeResponse> addLigne(@Valid LigneCommandeRequest ligne){
		ModelMapper modelMapper = new ModelMapper();
		 LigneCommandeDto ligneDto = modelMapper.map(ligne, LigneCommandeDto.class); 
		 LigneCommandeDto ligneDtoResult = ligneService.addLigne(ligneDto);
		
		return new ResponseEntity<LigneCommandeResponse>(modelMapper.map(ligneDtoResult, LigneCommandeResponse.class),HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path="/produit/{codeProduit}")
	public ResponseEntity<List<LigneCommandeResponse>> getLigneByProduct(@PathVariable String codeProduit, int page, int limit){
		List<LigneCommandeResponse> ligneResponse = new ArrayList<LigneCommandeResponse>();
		List<LigneCommandeDto> ligneDto = new ArrayList<LigneCommandeDto>();
		
		try {
			ProduitDto produitDto = produitService.getProduitByCodeProd(codeProduit);
			if(produitDto != null)
				ligneDto = ligneService.findByProduit(codeProduit, page, limit);
			
			ModelMapper modelMapper = new ModelMapper();
			for(LigneCommandeDto ligne : ligneDto) {
				ligneResponse.add(modelMapper.map(ligne, LigneCommandeResponse.class));
			}
		
		} catch (Exception e) {
		}
		
		
		return new ResponseEntity<List<LigneCommandeResponse>>(ligneResponse, HttpStatus.OK);	
				
	}

	@GetMapping(path="/commande/{codeCommande}")
	public ResponseEntity<List<LigneCommandeResponse>> getLigneByCommande(@PathVariable String codeCommande, int page, int limit){
		List<LigneCommandeResponse> ligneResponse = new ArrayList<LigneCommandeResponse>();
		List<LigneCommandeDto> ligneDto = new ArrayList<LigneCommandeDto>();
		
		
			CommandeDto commandeDto = commandeService.findByCodeCmd(codeCommande);
			if(commandeDto != null) {
				ligneDto = ligneService.findByCommande(codeCommande, page, limit);
				
				ModelMapper modelMapper = new ModelMapper();
				for(LigneCommandeDto ligne : ligneDto) {
					ligneResponse.add(modelMapper.map(ligne, LigneCommandeResponse.class));
				}
			}
		
		return new ResponseEntity<List<LigneCommandeResponse>>(ligneResponse, HttpStatus.OK);			
	}
	
	@DeleteMapping(path = "/delete/{codeLigne}")
	public void deleteLigne(@PathVariable Long codeLigne) {
		ligneService.deleteLigne(codeLigne);
	}
}
