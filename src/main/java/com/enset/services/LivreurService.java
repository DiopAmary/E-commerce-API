package com.enset.services;

import java.util.List;

import com.enset.dto.AddressDto;
import com.enset.dto.LivreurDto;

public interface LivreurService{
	
	public LivreurDto getLivreur(String id);
	
	public List<LivreurDto> getAllLivreurs(int page, int limit);
	
	public LivreurDto getLivreurByEmail(String email);
	
	//public LivreurDto getLivreurByAddress(AddressDto address);
	
	public void addLivreur(LivreurDto livreur);
	
	public void deleteLivreur(String id);

}
