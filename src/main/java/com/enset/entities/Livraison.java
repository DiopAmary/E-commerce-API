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

<<<<<<< HEAD
//@Entity
//@Table
//@Data
//@NoArgsConstructor 
//@AllArgsConstructor
//public class Livraison {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	Long id;
//	String codeLvr;
//	Date dateLvr;
//	double fraisLvr;
//	String methodeLvr;
//	Livreur livreur;
//}
=======
@Entity
@Table
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Livraison {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String codeLvr;
	
	Date dateLvr;
	
	double fraisLvr;
	
	String methodeLvr;
	
	@ManyToOne
	@JoinColumn(name = "livreur_id", nullable = true)
	private Livreur livreur=null;
	

}
>>>>>>> 592fe5bc980457e919bec3ec06bbc7463fb590d6
