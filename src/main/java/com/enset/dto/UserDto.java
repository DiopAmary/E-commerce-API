package com.enset.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserDto implements Serializable{
	
	private static final long serialVersionUID = -8184097076852721160L;
	private long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;
	private boolean admin;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus=false;
	private List<AddressDto> addresses=null;
	
	
}