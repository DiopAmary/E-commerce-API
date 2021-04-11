package com.enset.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor 
@AllArgsConstructor	
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Size(min=6,max=20)
	private String codeCmd;
	
	
	private double totalCmd;
	
	private String etatCmd;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCmd;
	
	private String methodePaiment;
	
	private int CommentaireCmd;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private UserEntity user;
	
	@OneToMany(mappedBy = "commande",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<LigneCommande> lignesCommande;
	
}
