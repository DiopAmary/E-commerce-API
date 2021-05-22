package com.enset.requests;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivreurRequest {
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@Size(min = 3, message = "ce champ doit avoir au moins 3 caracteres")
	private String nom;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@Size(min = 15, message = "ce champ doit avoir au moins 3 caracteres")
	private String prenom;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@Size(min = 15, message = "ce champ doit avoir au moins 15 caracteres")
	private String email;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@Size(min = 10, message = "ce champ doit avoir au moins 10 caracteres")
	private String telephone;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@Size(min = 3, message = "ce champ doit avoir au moins 3 caracteres")
	private String codeLivreur;
	
	private AddressRequest address;
	
	private List<LivraisonRequest> livraisons;
}
