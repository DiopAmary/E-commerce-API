package com.enset.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaiementEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String codePaiement;
	@Column(nullable = false)
	private String typePaiement;
	@Column(nullable = false)
	private long numCard;
	@Column(nullable = false)
	private String typeCard;
	@Column(nullable = false)
	private Date datePaiement;

	@OneToOne(mappedBy = "paiement")
	private CommandeEntity commande;
	
}
