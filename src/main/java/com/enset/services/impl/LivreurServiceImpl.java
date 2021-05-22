package com.enset.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enset.dto.LivreurDto;
import com.enset.entities.LivreurEntity;
import com.enset.repositories.LivreurRepository;
import com.enset.services.LivreurService;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;


@Service
@Transactional
public class LivreurServiceImpl implements LivreurService {

	@Autowired
	LivreurRepository livreurRepository;
	
	@Override
	public LivreurDto getLivreur(String id){
		LivreurEntity livEntity = livreurRepository.findByCodeLivreur(id);
		LivreurDto livDto = new LivreurDto();
		
		if(livEntity == null) {
			throw new EntityNotFoundException("Livreur with code = "+id+"  not found");
		}
		else {
			livDto = LivreurEntity.Mapper(livEntity);
			return livDto;
		}
		
	}
	
	@Override
	public List<LivreurDto> getAllLivreurs(int page , int limit) {
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<LivreurEntity> livreurs =  livreurRepository.findAll(pageableRequest);
		List<LivreurDto> livreursDto = new ArrayList<LivreurDto>();
		if(livreurs != null) {
			livreurs.forEach(element -> {
				livreursDto.add(LivreurEntity.Mapper(element));
			});
		}
		return livreursDto;
	}
	@Override
	public LivreurDto getLivreurByEmail(String email) {
		LivreurEntity livEntity = livreurRepository.findByEmail(email);
		LivreurDto livDto = new LivreurDto();
		
		if(livEntity == null) {
			throw new EntityNotFoundException("Livreur with email : "+email+"  not found");
		}
		else {
			livDto = LivreurEntity.Mapper(livEntity);
			return livDto;
		}
	}

//	@Override
//	public LivreurDto getLivreurByAddress(AddressDto address) {
//		ModelMapper mapper = new ModelMapper();
//		AddressEntity addressEntity = mapper.map(address, AddressEntity.class);
//		//LivreurEntity livEntity = livreurRepository.findByAddress(addressEntity);
//		LivreurDto livDto = new LivreurDto();
//		if(livEntity == null) {
//			throw new EntityNotFoundException("Livreur with address = "+addressEntity+"  not found");
//		}
//		else {
//			livDto = LivreurEntity.Mapper(livEntity);
//			return livDto;
//		}
//	}

	@Override
	public void addLivreur(LivreurDto livreur) {
		
		LivreurEntity livEntity = livreurRepository.findByCodeLivreur(livreur.getCodeLivreur());
		if(livEntity != null) {
			throw new RuntimeException("Livreur Already exists !");
		}
		
		else {
			/*
			 * create a Livreur Entity to save, by getting information from the given LivreurDto
			 * */
			livEntity = LivreurDto.Mapper(livreur);
			livreurRepository.save(livEntity);
			
			}	
		}

	@Override
	public void deleteLivreur(String id) {
		LivreurEntity livEntity = livreurRepository.findByCodeLivreur(id);
		if(livEntity == null)
			throw new EntityNotFoundException("Livreur with id = "+id+" not found");
		else {
			try {
				livreurRepository.delete(livEntity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	


}
