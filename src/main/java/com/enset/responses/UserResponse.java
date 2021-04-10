package com.enset.responses;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class UserResponse {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private boolean admin;
	private String numTel;
	private String sexUser;
	private boolean status=true;
	private String userName;
	private String photo;
	private Date createdAt;
	private Date updatedAt;
	private List<AddressResponse> addresses;
	
	private RoleResponse role;
}