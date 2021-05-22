package com.enset.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.enset.dto.LigneCommandeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommandeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	private String codeLigne;
	
	private int qte;
	
	private double sousTotale;
	
	@ManyToOne
	@JoinColumn(name = "commande_id", nullable = true)
	private CommandeEntity commande=null;
	
	@OneToOne
	@JoinColumn(name="produit_id")
	private ProduitEntity produit=null;
	

	public static LigneCommandeDto mapper(LigneCommandeEntity ligne) {
		
		LigneCommandeDto ligneDto = new LigneCommandeDto();
		ligneDto.setQte(ligne.getQte());
		ligneDto.setCommande(CommandeEntity.Mapper(ligne.getCommande()));
		/*
		 * add mapper to product
		 * */
		return ligneDto;
	}

}
