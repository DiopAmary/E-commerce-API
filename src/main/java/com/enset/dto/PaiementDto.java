package com.enset.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;

import com.enset.entities.CommandeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaiementDto implements Serializable {

	private static final long serialVersionUID = -5789054540450642638L;

	private long id;

	private String codePaiement;

	private String typePaiement;
	
	private long numCard;
	
	private String typeCard;
	
	private Date datePaiement;
	
	private CommandeDto commande;

}
