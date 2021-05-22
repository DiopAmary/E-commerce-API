package com.enset.services;

import com.enset.dto.CommandeDto;

import java.sql.Date;
import java.util.List;

import com.enset.dto.CommandeDto;

public interface CommandeService {

	public CommandeDto findByCodeCmd(String codeCmd);
	List<CommandeDto> findByEtatCmd(String etatCmd, int page, int limit);
	List<CommandeDto> findByDateCmd(Date dateCmd, int page, int limit);
	List<CommandeDto> findByCodeUser(String codeUser, int page, int limit);
	public void deleteCommande(String codeCommaande);
}
