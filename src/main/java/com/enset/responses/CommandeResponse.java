package com.enset.responses;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;



@Data @ToString
public class CommandeResponse {

private Long id; 
	
	private String codeCmd;
	
	private double totalCmd;
	
	private String etatCmd;
	
	private Date dateCmd;
	
	private String methodePaiment;
	
	private int CommentaireCmd;
	
	private UserResponse user;
	
	private List<LigneCommandeResponse> lignesCommande;
	
	LivraisonResponse livraison;
}
