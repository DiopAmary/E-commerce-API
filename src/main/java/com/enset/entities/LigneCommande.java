package com.enset.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class LigneCommande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int qte;
	
	private double sousTotale;
	
	@ManyToOne
	@JoinColumn(name = "commande_id", nullable = true)
	private Commande commande=null;

}
