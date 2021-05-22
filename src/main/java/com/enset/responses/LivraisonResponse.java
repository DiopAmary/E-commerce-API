package com.enset.responses;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class LivraisonResponse {

private Long id;
	
	private String codeLvr;
	
	private Date dateLvr;
	
	private double fraisLvr;
	
	private String methodeLvr;
	
	private LivreurResponse livreur;
	
	private CommandeResponse commande;
}
