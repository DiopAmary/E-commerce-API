package com.enset.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Entity
@Table
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Livraison {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codeLvr;
	
	private Date dateLvr;
	
	private double fraisLvr;
	
	private String methodeLvr;
	
	@ManyToOne
	@JoinColumn(name = "livreur_id", nullable = true)
	private Livreur livreur=null;
	

<<<<<<< HEAD
}
=======
}
>>>>>>> master
