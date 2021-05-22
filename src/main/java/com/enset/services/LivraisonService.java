package com.enset.services;

import java.sql.Date;
import java.util.List;
import com.enset.dto.LivraisonDto;

public interface LivraisonService{

	LivraisonDto findByCodeLvr(String codeLvr);
	
	List<LivraisonDto> findByCodeLivreur(String codeLivreurs, int page , int limit);
	
	List<LivraisonDto> findByDateLvr(Date dateLvr, int page , int limit);
	
	LivraisonDto addLivraison(LivraisonDto livraison);
	
	void deletLivraison(String codeLvr);
}
