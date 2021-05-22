package com.enset.requests;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LivraisonRequest {

	@NotBlank(message = "ce champ ne doit pas etre null")
	@Size(min = 3, message = "ce champ doit avoir au moins 3 caracteres")
	private String codeLvr;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateLvr;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@DecimalMin(value = "0", message = "ce champ doit avoir une valeur min = 0")
	private double fraisLvr;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@Size(min = 5, message = "ce champ doit avoir au moins 5 caracteres")
	private String methodeLvr;
	
	//private LivreurRequest livreur;
	
	CommandeRequest commande;
}
