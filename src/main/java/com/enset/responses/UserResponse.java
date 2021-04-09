package com.enset.responses;

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
	private List<AddressResponse> addresses;	
}