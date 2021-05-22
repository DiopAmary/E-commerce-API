package com.enset.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.enset.dto.LivraisonDto;
import com.enset.dto.LivreurDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "livreurs")
@AllArgsConstructor
@NoArgsConstructor
public class LivreurEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	private String telephone;
	
	private String codeLivreur;
	
	/* How to add id livreur to table address*/
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "address_id", nullable = true) private AddressEntity
	 * address;
	 */
	
	@OneToMany(mappedBy = "livreur", cascade = CascadeType.ALL)
	private List<LivraisonEntity> livraisons;

	/*
	 * To map from a LivreurEntity to LivreurDto
	 * */
	public static LivreurDto Mapper(LivreurEntity livreur) {
		
			LivreurDto livDto =  new LivreurDto();
			livDto.setNom(livreur.getNom());
			livDto.setPrenom(livreur.getPrenom());
			livDto.setEmail(livreur.getEmail());
			livDto.setTelephone(livreur.getTelephone());
			livDto.setCodeLivreur(livreur.getCodeLivreur());
			
			List<LivraisonDto> listLivrs = new ArrayList<LivraisonDto>();
			livreur.getLivraisons().forEach(livraison->{
				listLivrs.add(LivraisonEntity.Mapper(livraison));
			});
			livDto.setLivraisons(listLivrs);
			
			return livDto;
	}
}
