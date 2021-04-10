package com.enset.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enset.dto.UserDto;
import com.enset.exceptions.UserException;
import com.enset.requests.UserRequest;
import com.enset.responses.ErrorMessages;
import com.enset.responses.UserResponse;
import com.enset.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> getUser(@PathVariable String id){
		UserDto userDto = userService.getUserByUserId(id);
		ModelMapper modelMapper = new ModelMapper();
		return new ResponseEntity<>(modelMapper.map(userDto, UserResponse.class), HttpStatus.OK);
	}
	

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<UserResponse>> getAllUsers(
										  @RequestParam(value = "page", defaultValue = "1") int page,
										  @RequestParam(value = "limit", defaultValue = "5") int limit,
										  @RequestParam(value = "search",defaultValue = "") String search,
										  @RequestParam(value = "status",defaultValue = "1") int status							  
			){
		List<UserResponse> userResponse = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page,limit,search,status);
		for(UserDto userDto:users) {
			ModelMapper modelMapper = new ModelMapper();
			UserResponse user = modelMapper.map(userDto, UserResponse.class);;
			userResponse.add(user);
		}
		return new ResponseEntity<List<UserResponse>>(userResponse, HttpStatus.OK);
	}
	
	@PostMapping(
				path = "/add",
				consumes = { MediaType.APPLICATION_JSON_VALUE, "multipart/form-data" }, 
				produces = { MediaType.APPLICATION_JSON_VALUE }
				)
	public @ResponseBody ResponseEntity<UserResponse> createUser(
			@ModelAttribute @Valid UserRequest userRequest,
			@RequestParam(name = "roleName", defaultValue = "USER") String role,
			@RequestParam("photo") MultipartFile photo
			) throws Exception {

		if (userRequest.getUserName().isEmpty())
			throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto createUser = userService.createUser(userDto, role, photo);
		return new ResponseEntity<>( modelMapper.map(createUser, UserResponse.class), HttpStatus.CREATED );
	}

	
	@PutMapping(
			path = "/update/{id}", 
			consumes = { 
						MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_VALUE,
						MediaType.MULTIPART_FORM_DATA_VALUE
						}, 
			produces = { 
						MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_VALUE 
						}
			) // update user
	public @ResponseBody ResponseEntity<UserResponse> updateUser(
			@PathVariable String id,
			@Valid UserRequest userRequest,
			@RequestParam MultipartFile photo,
			@RequestParam(name = "roleName",defaultValue = "USER") String role
			) {
		ModelMapper modelMapper = new ModelMapper();
		
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);

		UserDto updateUser = userService.updateUser(id, userDto, role, photo);

		return new ResponseEntity<>(modelMapper.map(updateUser, UserResponse.class), HttpStatus.ACCEPTED);

	}

	
	@DeleteMapping(path = "/{id}") // remove user
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		
		userService.deleteUser(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
