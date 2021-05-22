package com.enset.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.enset.entities.CommandeEntity;
import com.enset.entities.LigneCommandeEntity;
import com.enset.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDto implements Serializable {

	private static final long serialVersionUID = -443256214966370548L;
	
	private String codeCmd;
	
	private double totalCmd;
	
	private String etatCmd;
	
	private Date dateCmd;
	
	private String methodePaiment;
	
	private String CommentaireCmd;
	
	private UserDto userDto;
	
	private String commentaireCmd;
	
	private List<LigneCommandeDto> lignesCommande;
	
	LivraisonDto livraison;

	public static CommandeEntity Mapper(CommandeDto commande) {
		CommandeEntity cmdEntity = new CommandeEntity();
		
		cmdEntity.setCodeCmd(commande.getCodeCmd());
		cmdEntity.setTotalCmd(commande.getTotalCmd());
		cmdEntity.setEtatCmd(commande.getEtatCmd());
		cmdEntity.setDateCmd(commande.getDateCmd());
		cmdEntity.setMethodePaiment(commande.getMethodePaiment());
		cmdEntity.setCommentaireCmd(commande.getCommentaireCmd());
		/*
		 * add mapper to others entities
		 * */
		List<LigneCommandeEntity>lignesCmd = new ArrayList<LigneCommandeEntity>();
		commande.getLignesCommande().forEach(ligne->{
			lignesCmd.add(LigneCommandeDto.mapper(ligne));
		});
		cmdEntity.setLignesCommande(lignesCmd);
		cmdEntity.setLivraison(LivraisonDto.Mapper(commande.getLivraison()));
		return cmdEntity;
	}
}
