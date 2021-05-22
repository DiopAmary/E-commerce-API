package com.enset.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enset.dto.LivraisonDto;
import com.enset.entities.LivraisonEntity;
import com.enset.entities.LivreurEntity;
import com.enset.repositories.LivraisonRepository;
import com.enset.repositories.LivreurRepository;
import com.enset.services.LivraisonService;

@Service
@Transactional
public class LivraisonServiceImpl implements LivraisonService{

	@Autowired
	LivraisonRepository livraisonRepository;
	
	@Autowired
	LivreurRepository livreurRepository;
	
	@Override
	public LivraisonDto findByCodeLvr(String codeLvr) {
		LivraisonEntity livrEntity = livraisonRepository.findByCodeLvr(codeLvr);
		LivraisonDto livrDto;
		if(livrEntity == null)
			throw new EntityNotFoundException("Livraison with code = "+codeLvr+" not found");
		else
			livrDto = LivraisonEntity.Mapper(livrEntity);
		
		return livrDto;
	}

	@Override
	public List<LivraisonDto> findByCodeLivreur(String codeLivreur, int page, int limit) {
		LivreurEntity livreur = livreurRepository.findByCodeLivreur(codeLivreur);
		List<LivraisonEntity> livraisons;
		Page<LivraisonEntity> livraisonsPage;
		List<LivraisonDto> livraisonsDto = new ArrayList<LivraisonDto>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		if (livreur == null)
			throw new EntityNotFoundException("LivreurEntity with code = " + codeLivreur + " Not found");
		else {
			livraisonsPage = livraisonRepository.findByLivreur(livreur, pageableRequest);
			if (livraisonsPage != null) {
				livraisons = livraisonsPage.toList();
				livraisons.forEach(livraison -> {
					livraisonsDto.add(LivraisonEntity.Mapper(livraison));
				});
			}
			return livraisonsDto;

		}
	}

	@Override
	public List<LivraisonDto> findByDateLvr(Date dateLvr, int page, int limit) {
		List<LivraisonDto> listLivraison = new ArrayList<LivraisonDto>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<LivraisonEntity> livraisonsPage = livraisonRepository.findByDateLvr(dateLvr, pageableRequest);
		if(livraisonsPage != null) {
			livraisonsPage.toList().forEach(liv -> {
				listLivraison.add(LivraisonEntity.Mapper(liv));
			});
		}
		return listLivraison;
	}

	@Override
	public LivraisonDto addLivraison(LivraisonDto livraison) {
		LivraisonEntity livraisonEntity = LivraisonDto.Mapper(livraison);
		if(livraisonEntity != null) {
			livraisonRepository.save(livraisonEntity);
			return livraison;
		}
		else
		return null;
	}

	@Override
	public void deletLivraison(String codeLvr) {
		LivraisonEntity livraisonEntity = livraisonRepository.findByCodeLvr(codeLvr);
		if(livraisonEntity == null)
			throw new EntityNotFoundException("No livraison with code = "+codeLvr+" found");
		else
			livraisonRepository.delete(livraisonEntity);
	}

}
