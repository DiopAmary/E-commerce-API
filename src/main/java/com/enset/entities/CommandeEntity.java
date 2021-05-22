package com.enset.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import com.enset.dto.CommandeDto;
import com.enset.dto.LigneCommandeDto;
import com.enset.dto.LivraisonDto;
import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor 
@AllArgsConstructor	
@Entity(name = "commande")
public class CommandeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Size(min=6,max=20)
	private String codeCmd;
	
	@Column(nullable = false)
	private double totalCmd;
	
	private String etatCmd;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCmd;
	
	@Column(nullable = false)
	private String methodePaiment;
	
	private String CommentaireCmd;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private UserEntity user;
	
	@OneToMany(mappedBy = "commande")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<LigneCommandeEntity> lignesCommande = new ArrayList<LigneCommandeEntity>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "livraison_id")
	private LivraisonEntity livraison;
	
	@OneToOne
	@JoinColumn(name = "paiement_id")
	private PaiementEntity paiement;

	public static CommandeDto Mapper(CommandeEntity commande) {
		CommandeDto cmdDto = new CommandeDto();
		
		cmdDto.setCodeCmd(commande.getCodeCmd());
		cmdDto.setTotalCmd(commande.getTotalCmd());
		cmdDto.setEtatCmd(commande.getEtatCmd());
		cmdDto.setDateCmd(commande.getDateCmd());
		cmdDto.setMethodePaiment(commande.getMethodePaiment());
		cmdDto.setCommentaireCmd(commande.getCommentaireCmd());
		/*
		 * add mapper to others entities (user)
		 * */
		
		List<LigneCommandeDto>lignesCmd = new ArrayList<LigneCommandeDto>();
		
		commande.getLignesCommande().forEach(ligne->{
		
			lignesCmd.add(LigneCommandeEntity.mapper(ligne));
		
		});
		cmdDto.setLignesCommande(lignesCmd);
		cmdDto.setLivraison(LivraisonEntity.Mapper(commande.getLivraison()));
		return cmdDto;
	}
	
}