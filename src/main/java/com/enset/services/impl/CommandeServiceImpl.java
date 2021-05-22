package com.enset.services.impl;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enset.dto.CommandeDto;
import com.enset.entities.CommandeEntity;
import com.enset.entities.UserEntity;
import com.enset.repositories.CommandeRepository;
import com.enset.repositories.UserRepository;
import com.enset.services.CommandeService;

@Service
@Transactional
public class CommandeServiceImpl implements CommandeService{

	
	@Autowired
	CommandeRepository commandeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public CommandeDto findByCodeCmd(String codeCmd) {
		CommandeEntity cmdEntity = commandeRepository.findByCodeCmd(codeCmd);
		if(cmdEntity == null)
			throw new EntityNotFoundException("No Commande with code= "+codeCmd+" founded");
		else
			return CommandeEntity.Mapper(cmdEntity);
	}

	@Override
	public List<CommandeDto> findByEtatCmd(String etatCmd, int page, int limit) {
		Pageable pageableRequest = PageRequest.of(page, limit);
		List<CommandeEntity> listCmd = commandeRepository.findByEtatCmd(etatCmd,pageableRequest).toList();
		List<CommandeDto> listCmdDto = new ArrayList<CommandeDto>();
		listCmd.forEach(cmd ->{
			listCmdDto.add(CommandeEntity.Mapper(cmd));
		});
		return listCmdDto;
	}

	@Override
	public List<CommandeDto> findByDateCmd(Date dateCmd, int page, int limit) {
		Pageable pageableRequest = PageRequest.of(page, limit);
		List<CommandeEntity> listCmd = commandeRepository.findByDateCmd(dateCmd,pageableRequest).toList();
		List<CommandeDto> listCmdDto = new ArrayList<CommandeDto>();
		listCmd.forEach(cmd ->{
			listCmdDto.add(CommandeEntity.Mapper(cmd));
		});
		return listCmdDto;
	}

	@Override
	public List<CommandeDto> findByCodeUser(String codeUser, int page, int limit) {
		UserEntity user = userRepository.findByUserId(codeUser);
		if(user == null)
			throw new EntityNotFoundException("User with code="+codeUser+" not found");
		else {
			Pageable pageableRequest = PageRequest.of(page, limit);
			List<CommandeEntity> listCmd = commandeRepository.findByUser(user,pageableRequest).toList();
			List<CommandeDto> listCmdDto = new ArrayList<CommandeDto>();
			listCmd.forEach(cmd ->{
				listCmdDto.add(CommandeEntity.Mapper(cmd));
			});
			return listCmdDto;
		}
	}
	
	@Override
	public void deleteCommande(String codeCommaande) {
		CommandeEntity cmdEntity = commandeRepository.findByCodeCmd(codeCommaande);
		if(cmdEntity != null) {
			commandeRepository.delete(cmdEntity);
		}
		else
			throw new EntityNotFoundException("No commande with code = "+ codeCommaande+" founded");		
	}
}
