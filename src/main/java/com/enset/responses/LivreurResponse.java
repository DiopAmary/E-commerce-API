package com.enset.responses;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LivreurResponse {

	private Long id;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	private String telephone;
	
	private String codeLivreur;

	AddressResponse address;
	
	List<LivraisonResponse> livraisons;
}
