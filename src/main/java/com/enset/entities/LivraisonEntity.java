package com.enset.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.enset.dto.LivraisonDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class LivraisonEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codeLvr;
	
	private Date dateLvr;
	
	private double fraisLvr;
	
	private String methodeLvr;
	
	@ManyToOne
	@JoinColumn(name = "livreur_id", nullable = true)
	private LivreurEntity livreur;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commande_id")
	CommandeEntity commande;

	
	public static LivraisonDto Mapper(LivraisonEntity livraison) {
		LivraisonDto livrDto = new LivraisonDto();
		livrDto.setCodeLvr(livraison.getCodeLvr());
		livrDto.setDateLvr(livraison.getDateLvr());
		livrDto.setFraisLvr(livraison.getFraisLvr());
		livrDto.setMethodeLvr(livraison.getMethodeLvr());
		livrDto.setLivreur(LivreurEntity.Mapper(livraison.getLivreur()));
		livrDto.setCommande(CommandeEntity.Mapper(livraison.getCommande()));
		return livrDto;
	}
}
