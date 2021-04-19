package com.enset.requests;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserRequest {

	@NotBlank(message = "ce champ ne doit pas etre null")
	@Size(min = 3, message = "ce champ doit avoir au moins 3 caracteres")
	private String firstName;
	
	@NotNull(message = "ce champ ne doit pas etre null")
	@Size(min = 3, message = "ce champ doit avoir au moins 3 caracteres")
	private String lastName;
	
	@NotNull(message = "ce champ ne doit pas etre null")
	@Email(message = "ce champ doit respecter le format email")
	private String email;
	
//	@NotNull(message = "ce champ ne doit pas etre null")
//	@Size(min = 8, message = "mot de passe doit avoir au moins 8 caracteres !")
//	@Size(max = 12, message = "mot de passe doit avoir au max 12 caracteres !")
//	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", 
//			 message = "ce mot de passe doit avoir des lettres en maj et minisc et numero")
	private String password;
	
	private String numTel;
	
	private String sexUser;
	
	private boolean status=true;
	
	private String userName;
	
	private boolean admin;

	private Set<RoleRequest> roles;
		
	private List<AddressRequest> addresses;
	
}
