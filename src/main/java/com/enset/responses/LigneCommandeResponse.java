package com.enset.responses;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class LigneCommandeResponse {

	private Long id;
	
	private int qte;
	
	private double sousTotale;
	
	CommandeResponse commande;
	
	ProduitResponse produit;
}
