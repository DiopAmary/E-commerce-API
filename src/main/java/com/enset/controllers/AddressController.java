package com.enset.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enset.dto.AddressDto;
import com.enset.requests.AddressRequest;
import com.enset.responses.AddressResponse;
import com.enset.services.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	public AddressService addressService;

	@GetMapping
	public ResponseEntity<List<AddressResponse>> getAddresses(Principal principal) {
		
		List<AddressDto> addresses = addressService.getAllAddresses(principal.getName());
		
		Type listType = new TypeToken<List<AddressResponse>>() {}.getType();
		
		
		List<AddressResponse> addressesResponse = new ModelMapper().map(addresses, listType);
		return new ResponseEntity<List<AddressResponse>>(addressesResponse, HttpStatus.OK);
	}

	@PostMapping(
			path = "/add",
			consumes = { 
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE 
					}, 
			produces = { 
					MediaType.APPLICATION_JSON_VALUE 
					}
			)
	public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest,
			Principal principal) {
		ModelMapper modelMapper = new ModelMapper();

		AddressDto addressDto = modelMapper.map(addressRequest, AddressDto.class);
		AddressDto createdAddress = addressService.createAddress(addressDto, principal.getName());
		AddressResponse newAddress = modelMapper.map(createdAddress, AddressResponse.class);
		return new ResponseEntity<AddressResponse>(newAddress, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressResponse> getOneAddresse(@PathVariable(name = "id") String addressId) {
		AddressDto addressDto = addressService.getAddress(addressId);
		ModelMapper modelMapper = new ModelMapper();
		AddressResponse addressResponse = modelMapper.map(addressDto, AddressResponse.class);
		return new ResponseEntity<AddressResponse>(addressResponse, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<AddressResponse> updateAddresse(@PathVariable(name = "id") String addressId,
			@RequestBody AddressRequest addressRequest) {
		ModelMapper modelMapper = new ModelMapper();
		AddressDto address = modelMapper.map(addressRequest, AddressDto.class);
		AddressDto addressDto = addressService.updateAddress(addressId, address);
		AddressResponse addressResponse = modelMapper.map(addressDto, AddressResponse.class);
		return new ResponseEntity<AddressResponse>(addressResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAddresse(@PathVariable(name = "id") String addressId) {
		addressService.deleteAddress(addressId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
