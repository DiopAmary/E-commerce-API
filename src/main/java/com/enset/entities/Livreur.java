package com.enset.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor	
public class Livreur{

	String codeLivreur;
	
	@OneToMany(mappedBy = "livreur",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Livraison> livraisons;
	
}
