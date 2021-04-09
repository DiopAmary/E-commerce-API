package com.enset.entities;


import javax.persistence.Entity;
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
	
}
