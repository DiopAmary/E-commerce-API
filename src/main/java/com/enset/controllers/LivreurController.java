package com.enset.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enset.dto.LivreurDto;
import com.enset.exceptions.UserException;
import com.enset.requests.AddressRequest;
import com.enset.requests.LivraisonRequest;
import com.enset.requests.LivreurRequest;
import com.enset.requests.RoleRequest;
import com.enset.requests.UserRequest;
import com.enset.responses.ErrorMessages;
import com.enset.responses.LivreurResponse;
import com.enset.responses.UserResponse;
import com.enset.services.LivreurService;

@RestController
@RequestMapping("/livreur")
public class LivreurController {
	
	@Autowired
	LivreurService livreurService;

	@GetMapping(
			path = "/{id}", 
			produces = { 
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE 
					}
			)
	public ResponseEntity<LivreurResponse> getLivreur(@PathVariable String id){
		LivreurDto livreurDto = livreurService.getLivreur(id);
		ModelMapper modelMapper = new ModelMapper();
		return new ResponseEntity<>(modelMapper.map(livreurDto, LivreurResponse.class), HttpStatus.OK);
	}
	@GetMapping(
			path = "/all", 
			produces = { 
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE 
					}
			)
	public ResponseEntity<List<LivreurResponse>> getAllLivreur(
			@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size){
		
		List<LivreurDto> livreurDto = livreurService.getAllLivreurs(page,size);
		List<LivreurResponse> livreurResponse = new ArrayList<LivreurResponse>();
		ModelMapper modelMapper = new ModelMapper();
		for(LivreurDto livreur : livreurDto) {
			LivreurResponse liv = modelMapper.map(livreur, LivreurResponse.class);;
			livreurResponse.add(liv);
		}
		return new ResponseEntity<List<LivreurResponse>>(livreurResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(
			path = "/delete/{id}", 
			produces = { 
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE 
					}
			)
	public ResponseEntity<Object> deleteLivreur(@PathVariable String id) {
		livreurService.deleteLivreur(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(
			path = "/add",
			consumes = { MediaType.APPLICATION_JSON_VALUE, "multipart/form-data" }, 
			produces = { MediaType.APPLICATION_JSON_VALUE }
			)
	@ResponseBody
	public ResponseEntity<LivreurResponse> addLivreur(
			@Valid LivreurRequest livreurRequest,
			@RequestPart(name = "address", required = false) AddressRequest addresse,
			@RequestPart(name = "livraisons", required = false) List<LivraisonRequest> livraisons){
			
			if(livreurRequest.getNom().isEmpty() || livreurRequest.getPrenom().isEmpty() || livreurRequest.getCodeLivreur().isEmpty())
				throw new RuntimeException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
			
			ModelMapper modelMapper = new ModelMapper();
			livreurRequest.setAddress(addresse);
			livreurRequest.setLivraisons(livraisons);
			LivreurDto livDto = modelMapper.map(livreurRequest, LivreurDto.class);
			livreurService.addLivreur(livDto);
			return new ResponseEntity<>(modelMapper.map(livDto, LivreurResponse.class), HttpStatus.ACCEPTED);
			}
}
