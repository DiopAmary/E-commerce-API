package com.enset.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enset.dto.LigneCommandeDto;
import com.enset.entities.CommandeEntity;
import com.enset.entities.LigneCommandeEntity;
import com.enset.entities.ProduitEntity;
import com.enset.repositories.CommandeRepository;
import com.enset.repositories.LigneCommandeRepository;
import com.enset.repositories.ProduitRepository;
import com.enset.services.LigneCommandeService;


@Service
@Transactional
public class LigneCommandeServiceImpl implements LigneCommandeService{

	@Autowired
	LigneCommandeRepository ligneRepository;
	
	@Autowired
	CommandeRepository commandeRepository;
	
	@Autowired
	ProduitRepository produitRepository;
	
	@Override
	public List<LigneCommandeDto> findByCommande(String codeCmd, int page, int limit) {
		List<LigneCommandeDto> listLigneDto= new ArrayList<LigneCommandeDto>();
		List<LigneCommandeEntity> listLigneEntity;
		Pageable pageable = PageRequest.of(page, limit);
		CommandeEntity cmdEntity = commandeRepository.findByCodeCmd(codeCmd);
		if(cmdEntity == null)
			throw new EntityNotFoundException("No commande with code="+codeCmd+" was Founded");
		else {
			listLigneEntity = ligneRepository.findByCommande(cmdEntity, pageable).toList();
			listLigneEntity.forEach(cmd -> {
				listLigneDto.add(LigneCommandeEntity.mapper(cmd));
			});
		}
		return listLigneDto;
	}

	@Override
	public List<LigneCommandeDto> findByProduit(String codeProduit, int page, int limit) {
		List<LigneCommandeDto> listLigneDto= new ArrayList<LigneCommandeDto>();
		List<LigneCommandeEntity> listLigneEntity;
		Pageable pageable = PageRequest.of(page, limit);
		ProduitEntity produitEntity = produitRepository.findByCodeProd(codeProduit);
		if(produitEntity == null)
			throw new EntityNotFoundException("No product with code="+codeProduit+" was Founded");
		else {
			listLigneEntity = ligneRepository.findByProduit(produitEntity, pageable).toList();
			listLigneEntity.forEach(cmd -> {
				listLigneDto.add(LigneCommandeEntity.mapper(cmd));
			});
		}
		return listLigneDto;
	}

	@Override
	public void deleteLigne(Long codeLigne) {
		Optional<LigneCommandeEntity> ligne = ligneRepository.findById(codeLigne);
		if(ligne != null) {
			ligneRepository.deleteById(codeLigne);
		}
		else
			throw new EntityNotFoundException("Ligne with code "+codeLigne+" not found");
		
	}

	@Override
	public LigneCommandeDto addLigne(LigneCommandeDto ligneDto) {
		LigneCommandeEntity ligneEntity = ligneRepository.findByCodeLigne(ligneDto.getCodeLigne());
		return null;
	}

}
