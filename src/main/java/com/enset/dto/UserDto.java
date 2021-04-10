package com.enset.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

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
	private String numTel;
	private String sexUser;
	private boolean status=true;
	private String userName;
	private Boolean emailVerificationStatus=false;
	private String photo=null;
	private Date createdAt;
	private Date updatedAt;
	private List<AddressDto> addresses=null;
	private RoleDto role;
	
	
}