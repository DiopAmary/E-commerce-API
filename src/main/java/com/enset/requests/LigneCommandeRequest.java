package com.enset.requests;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LigneCommandeRequest {

	@NotBlank(message = "ce champ ne doit pas etre null")
	@Size(min = 3, message = "ce champ doit avoir au moins 3 caracteres")
	private Long id;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@DecimalMin(value = "1", message = "ce champ doit avoir une valeur min = 1")
	private int qte;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	private double sousTotale;
	
	private CommandeRequest commande;
	
	private ProduitRequest produit;
}
