package com.enset.requests;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CommandeRequest {

private String codeCmd;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	private double totalCmd;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@Size(min = 3, message = "ce champ doit avoir au moins 3 caracteres")
	private String etatCmd;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCmd;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@Size(min = 3, message = "ce champ doit avoir au moins 3 caracteres")
	private String methodePaiment;
	
	@NotBlank(message = "ce champ ne doit pas etre null")
	@DecimalMin(value="0", message = "ce champ doit avoir une valeur min = 0")
	private int CommentaireCmd;
	
	private UserRequest user;
	
	private List<LigneCommandeRequest> lignesCommande;
	
	LivraisonRequest livraison;
}
