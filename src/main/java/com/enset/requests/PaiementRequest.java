package com.enset.requests;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.enset.dto.CommandeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PaiementRequest {
	
	@NotNull(message="ce champ ne doit pas etre null")
	private String codePaiement;
	@NotNull(message="ce champ ne doit pas etre null")
	private String typePaiement;
	@NotNull(message="ce champ ne doit pas etre null")
	private long numCard;
	@NotNull(message="ce champ ne doit pas etre null")
	private String typeCard;
	
	private CommandeRequest commande;
	
}
