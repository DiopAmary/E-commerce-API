package com.enset.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.enset.entities.LivraisonEntity;
import com.enset.entities.LivreurEntity;

import lombok.Data;

@Data
public class LivreurDto implements Serializable {

	private static final long serialVersionUID = 6046913687167747735L;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	private String telephone;
	
	private String codeLivreur;
	
	private AddressDto address;
	
	private List<LivraisonDto> livraisons;
	
	/*
	 * To map from LivreurDto to LivreurEntity
	 *  */
	public static LivreurEntity Mapper(LivreurDto livreur) {
		
		LivreurEntity livEntity =  new LivreurEntity();
		livEntity.setNom(livreur.getNom());
		livEntity.setPrenom(livreur.getPrenom());
		livEntity.setEmail(livreur.getEmail());
		livEntity.setTelephone(livreur.getTelephone());
		livEntity.setCodeLivreur(livreur.getCodeLivreur());
		
		List<LivraisonEntity> listLivrs = new ArrayList<LivraisonEntity>();
		livreur.getLivraisons().forEach(livraison->{
			listLivrs.add(LivraisonDto.Mapper(livraison));
		});
		livEntity.setLivraisons(listLivrs);
		
		return livEntity;
	}
}
