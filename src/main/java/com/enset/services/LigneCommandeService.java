package com.enset.services;

import java.util.List;

import com.enset.dto.LigneCommandeDto;

public interface LigneCommandeService {

	public List<LigneCommandeDto> findByCommande(String codeCmd, int page, int limit);
	
	public List<LigneCommandeDto> findByProduit(String codeProduit, int page, int limit);
	
	public void deleteLigne(Long codeLigne);

	public LigneCommandeDto addLigne(LigneCommandeDto ligneDto);
}
