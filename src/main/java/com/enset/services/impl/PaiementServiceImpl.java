package com.enset.services.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enset.dto.CommandeDto;
import com.enset.dto.PaiementDto;
import com.enset.entities.CommandeEntity;
import com.enset.entities.PaiementEntity;
import com.enset.repositories.CommandeRepository;
import com.enset.repositories.PaiementRepository;
import com.enset.services.CommandeService;
import com.enset.services.PaiementService;


@Service
@Transactional
public class PaiementServiceImpl implements PaiementService {
	@Autowired
	private PaiementRepository paiementRepository;
	
	@Autowired
	private CommandeRepository commandeRepository;
	
	
	@Override
	public PaiementDto getPaiement(long id) {
		ModelMapper modelMapper= new ModelMapper();
		PaiementEntity paiementEntity= paiementRepository.findById(id);
		
		return modelMapper.map(paiementEntity, PaiementDto.class);
	}
	@Override
	public PaiementDto savePaiement(PaiementDto paiementdto, String codeCommande) {
		ModelMapper modelMapper = new ModelMapper();
		
		CommandeEntity commande = commandeRepository.findByCodeCmd(codeCommande);
		
		if(commande == null) throw new RuntimeException("commande does not exist !!!");
		paiementdto.setCommande(modelMapper.map(commande, CommandeDto.class));
		PaiementEntity paiementEntity= modelMapper.map(paiementdto, PaiementEntity.class);
		paiementRepository.save(paiementEntity);
		return modelMapper.map(paiementEntity, PaiementDto.class);
	}
	
	
	
	@Override
	public void deletePaiement(long id) {
		PaiementEntity paiement = paiementRepository.findById(id);
		if(paiement == null) throw new RuntimeException("paiement does not exist !!!");	
		paiementRepository.delete(paiement);
	}
	
	@Override
	public PaiementDto getPaiementbyCommande(CommandeDto commandeDto) {
		ModelMapper modelMapper= new ModelMapper();
		CommandeEntity commandeEntity= modelMapper.map(commandeDto, CommandeEntity.class);
		PaiementEntity paiementEntity=paiementRepository.findByCommande(commandeEntity);
		return modelMapper.map(paiementEntity, PaiementDto.class);
	}
	@Override
	public PaiementDto updatePaiement(PaiementDto p, String idPaiement) {
		ModelMapper modelMapper = new ModelMapper();
		PaiementEntity paiement = paiementRepository.findByCodePaiement(idPaiement);
		if(paiement==null) throw new RuntimeException("paiement does not exist !!!");
		PaiementEntity modifieddPaiement= paiementRepository.save(modelMapper.map(p, PaiementEntity.class));
		return modelMapper.map(modifieddPaiement, PaiementDto.class);
	}
}
