package com.enset.dto;

import java.io.Serializable;

import com.enset.entities.CommandeEntity;
import com.enset.entities.LigneCommandeEntity;

import lombok.Data;

@Data
public class LigneCommandeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2463796193815275265L;
	
	private Long id;
	
	private String codeLigne;
	
	private int qte;
	
	private double sousTotale;
	
	private CommandeDto commande;
	
	private ProduitDto produit;

	public static LigneCommandeEntity mapper(LigneCommandeDto ligne) {


		LigneCommandeEntity ligneEntity = new LigneCommandeEntity();
		ligneEntity.setQte(ligne.getQte());
		ligneEntity.setCommande(CommandeDto.Mapper(ligne.getCommande()));
		/*
		 * add mapper to product
		 * */
		return ligneEntity;
	}

}
