package com.enset.dto;

import java.io.Serializable;
import java.util.Date;

import com.enset.entities.LivraisonEntity;

import lombok.Data;

@Data
public class LivraisonDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4151009174093536016L;
	
	private String codeLvr;
	
	private Date dateLvr;
	
	private double fraisLvr;
	
	private String methodeLvr;
	
	private LivreurDto livreur;
	
	private CommandeDto commande;

	public static LivraisonEntity Mapper(LivraisonDto livraison) {
		LivraisonEntity livrEntity = new LivraisonEntity();
		livrEntity.setCodeLvr(livraison.getCodeLvr());
		livrEntity.setDateLvr(livraison.getDateLvr());
		livrEntity.setFraisLvr(livraison.getFraisLvr());
		livrEntity.setMethodeLvr(livraison.getMethodeLvr());
		livrEntity.setLivreur(LivreurDto.Mapper(livraison.getLivreur()));
		livrEntity.setCommande(CommandeDto.Mapper(livraison.getCommande()));
		return null;
	}

	

}
