package com.enset.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enset.dto.LivraisonDto;
import com.enset.dto.LivreurDto;
import com.enset.requests.LivraisonRequest;
import com.enset.requests.LivreurRequest;
import com.enset.responses.LivraisonResponse;
import com.enset.responses.LivreurResponse;
import com.enset.services.LivraisonService;

@RestController
@RequestMapping("/livraison")
public class LivraisonController {
	
	@Autowired
	LivraisonService livraisonService;
	
	
	@GetMapping(
			path = "/{codeLivraison}", 
			produces = { 
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE 
					}
			)
	public ResponseEntity<LivraisonResponse> getLivraison(@PathVariable String codeLivraison){
		LivraisonDto livraisonDto = livraisonService.findByCodeLvr(codeLivraison);
		ModelMapper modelMapper = new ModelMapper();
		return new ResponseEntity<>(modelMapper.map(livraisonDto, LivraisonResponse.class), HttpStatus.OK);
	}
	
	@GetMapping(
			path = "/livreur/{codeLiveur}", 
			produces = { 
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE 
					}
			)
	public ResponseEntity<List<LivraisonResponse>> getLivraisonByLivreur(String codeLivreur, int page, int limit) {
		
		List<LivraisonDto> livraisonsDto = livraisonService.findByCodeLivreur(codeLivreur, page, limit);
		List<LivraisonResponse> responses = new ArrayList<LivraisonResponse>();
		
		ModelMapper modelMapper = new ModelMapper();
		for(LivraisonDto livraison : livraisonsDto) {
			LivraisonResponse livRes = modelMapper.map(livraison, LivraisonResponse.class);
			responses.add(livRes);
		}
		
		return new ResponseEntity<List<LivraisonResponse>>(responses, HttpStatus.OK);
		
	}
	
	@PostMapping(
			path = "/add",
			consumes = { MediaType.APPLICATION_JSON_VALUE}, 
			produces = { MediaType.APPLICATION_JSON_VALUE }
			)
	public ResponseEntity<LivraisonResponse> addLivraison(
			@Valid LivraisonRequest livraison,
			@Valid LivreurRequest livreur) {
		
		ModelMapper modelMapper = new ModelMapper();
		LivreurDto livreurDto = modelMapper.map(livreur, LivreurDto.class);
		LivraisonDto livraisonDto = modelMapper.map(livraison, LivraisonDto.class);
		livraisonDto.setLivreur(livreurDto);
		
		LivraisonDto liv = livraisonService.addLivraison(livraisonDto);
	
		return new ResponseEntity<>(modelMapper.map(liv, LivraisonResponse.class), HttpStatus.ACCEPTED);
		
	}
	@DeleteMapping(path="/delete/{codeLivraison}")
	public ResponseEntity<Object> deleteLivraison(String codeLivraison){
		LivraisonDto livEntity = livraisonService.findByCodeLvr(codeLivraison);
		if(livEntity != null) {
			livraisonService.deletLivraison(codeLivraison);
		}
		else
			throw new EntityNotFoundException();

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
