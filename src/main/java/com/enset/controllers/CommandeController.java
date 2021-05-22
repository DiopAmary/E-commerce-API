package com.enset.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enset.dto.CommandeDto;
import com.enset.dto.UserDto;
import com.enset.requests.CommandeRequest;
import com.enset.requests.LigneCommandeRequest;
import com.enset.requests.UserRequest;
import com.enset.responses.CommandeResponse;
import com.enset.services.CommandeService;
import com.enset.services.UserService;

@RestController
@RequestMapping("/commande")
public class CommandeController {
	
	@Autowired
	CommandeService commandeService;
	
	
	@Autowired
	UserService userService;
	
	
	@PostMapping(path="/add")
	public ResponseEntity<CommandeResponse> addCommande(
			@Valid CommandeRequest commande,
			@Valid UserRequest user,
			@Valid List<LigneCommandeRequest> lignes){
		
		ModelMapper modelMapper = new ModelMapper();
		if(commande != null && user != null) {
			commande.setUser(user);
			if(lignes != null)
				commande.setLignesCommande(lignes);
		}
		
		return new ResponseEntity<>(modelMapper.map(commande, CommandeResponse.class), HttpStatus.OK);
	}
	
	@PostMapping(path = "/user/{userCode}")
	public ResponseEntity<List<CommandeResponse>> getCommandeByUser(@PathVariable String userCode, int page, int limit){
		UserDto user = userService.getUserByUserId(userCode);
		List<CommandeDto> commandes = new ArrayList<CommandeDto>();
		List<CommandeResponse> commandesResponse = new ArrayList<CommandeResponse>();
		ModelMapper modelMapper = new ModelMapper();

		CommandeResponse commandeRes;
		if(user != null) {
			commandes = commandeService.findByCodeUser(userCode, page, limit);
			for(CommandeDto commande : commandes) {
				commandeRes = modelMapper.map(commande, CommandeResponse.class);
				commandesResponse.add(commandeRes);
			}
		}
		
		return new ResponseEntity<List<CommandeResponse>>(commandesResponse, HttpStatus.OK);	
	}
	
	@PostMapping(path= "/delete/{codeCommande}")
	public void deleteCommande(@PathVariable String codeCommande) {
		CommandeDto commandeDto = commandeService.findByCodeCmd(codeCommande);
		if(commandeDto != null) {
			commandeService.deleteCommande(codeCommande);
		}
	}
	
	
}


