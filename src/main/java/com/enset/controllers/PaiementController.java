package com.enset.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.enset.dto.CommandeDto;
import com.enset.dto.PaiementDto;
import com.enset.entities.CommandeEntity;
import com.enset.repositories.CommandeRepository;
import com.enset.requests.CommandeRequest;
import com.enset.requests.PaiementRequest;
import com.enset.responses.PaiementResponse;
import com.enset.services.CommandeService;
import com.enset.services.PaiementService;

@RestController
public class PaiementController {
	@Autowired
	PaiementService paiementService;
	@Autowired
	CommandeService commandeService;
	@Autowired
	CommandeRepository commandeRepository;
	
	/* -- -------------------------------------------------------------------------------------------------------------------
	 --------------------------------------------- Save PAIEMENT------------------------------------------------------------
	            ------------------------------------------------------------------------------------------------------
	 */
@PostMapping("/savePaiement/{idCommande}")
public PaiementResponse savePaiement(@Valid PaiementRequest paiementRequest,@PathVariable String idCommande) {
	ModelMapper modelMapper= new ModelMapper();
	PaiementDto paiement = paiementService.savePaiement(
														modelMapper.map(paiementRequest,
														PaiementDto.class), idCommande
														);
	return modelMapper.map(paiement, PaiementResponse.class);
}
/* -- -------------------------------------------------------------------------------------------------------------------
 --------------------------------------------- DELETE PAIEMENT------------------------------------------------------------
            ------------------------------------------------------------------------------------------------------
 
 */
@DeleteMapping(path = "deletePaiement/{id}") 
public ResponseEntity<Object> deletePaiement(@PathVariable long id) {
	paiementService.deletePaiement(id);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

/* -- -------------------------------------------------------------------------------------------------------------------
--------------------------------------------- update PAIEMENT------------------------------------------------------------
           ------------------------------------------------------------------------------------------------------
*/

@GetMapping("/formPaiement/{idPaiement}")
public ResponseEntity<PaiementRequest> editPaiement(@RequestBody PaiementRequest paiementRequest, @PathVariable(name = "idPaiement") String idPaiement) {
	ModelMapper modeleMapper = new ModelMapper();
	PaiementDto paiement = paiementService.updatePaiement(
			modeleMapper.map(paiementRequest, PaiementDto.class), 
			idPaiement);
	return new ResponseEntity<PaiementRequest>(
							modeleMapper.map(paiement, PaiementRequest.class), 
							HttpStatus.CREATED);
}
}
