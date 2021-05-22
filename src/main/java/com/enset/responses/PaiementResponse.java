package com.enset.responses;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaiementResponse {

	private long id;

	private String codePaiement;

	private String typePaiement;
	
	private long numCard;
	
	private String typeCard;
	
	private Date datePaiement;
	
	private CommandeResponse commande;
}
