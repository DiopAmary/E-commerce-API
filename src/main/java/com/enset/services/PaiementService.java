package com.enset.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.enset.dto.CommandeDto;
import com.enset.dto.PaiementDto;
import com.enset.repositories.PaiementRepository;

public interface PaiementService {
	public PaiementDto getPaiement(long id);
	public PaiementDto getPaiementbyCommande(CommandeDto commandeDto);
	public PaiementDto savePaiement(PaiementDto paiementdto, String codeCommande);
	public void deletePaiement(long id);
	public PaiementDto updatePaiement(PaiementDto paiement, String idPaiement);
}
