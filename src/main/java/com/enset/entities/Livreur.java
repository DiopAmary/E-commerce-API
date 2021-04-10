package com.enset.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
=======
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
>>>>>>> 592fe5bc980457e919bec3ec06bbc7463fb590d6
import lombok.Data;

@Data
@Entity(name = "livreurs")
public class Livreur{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codeLivreur;
	
	@OneToMany(mappedBy = "livreur",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Livraison> livraisons;
	
}
