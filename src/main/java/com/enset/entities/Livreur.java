package com.enset.entities;


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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "livreurs")
@AllArgsConstructor
@NoArgsConstructor
public class Livreur{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	private String telephone;
	
	private String codeLivreur;
	
	
	@ManyToOne
	@JoinColumn(name = "address_id", nullable = true)
	private AddressEntity address;
	
	@OneToMany(mappedBy = "livreur",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Livraison> livraisons;
	
}
