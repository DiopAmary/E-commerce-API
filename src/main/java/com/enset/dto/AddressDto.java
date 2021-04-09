package com.enset.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class AddressDto implements Serializable{

	private static final long serialVersionUID = 8117673563854927317L;
	
	private long id;
	
	private String addressId;
	
	private String city;
	
	private String country;
	
	private String street;
	
	private String postal;
	
	private String type;
	
	private UserDto user;
}