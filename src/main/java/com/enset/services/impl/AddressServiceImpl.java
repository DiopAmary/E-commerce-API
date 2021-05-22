package com.enset.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enset.dto.AddressDto;
import com.enset.entities.AddressEntity;
import com.enset.entities.UserEntity;
import com.enset.repositories.AddressRepository;
import com.enset.repositories.UserRepository;
import com.enset.services.AddressService;
import com.enset.utils.Utils;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	Utils util;
	
	@Override
	public List<AddressDto> getAllAddresses(String email) {
		UserEntity currentUser = userRepository.findByEmail(email);
		List<AddressEntity> addresses = 
				currentUser.isAdmin()==true?
						(List<AddressEntity>)addressRepository.findAll()
				:(List<AddressEntity>)addressRepository.findByUser(currentUser);
		System.out.println("isAdmin : " + currentUser.isAdmin());			
		Type listType = new TypeToken<List<AddressDto>>() {}.getType();
		List<AddressDto> addressesDto = new ModelMapper().map(addresses, listType);
		return addressesDto;
	}


	@Override
	public AddressDto createAddress(AddressDto addressDto, String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		ModelMapper modelMapper = new ModelMapper();
		
		AddressEntity addressEntity = modelMapper.map(addressDto, AddressEntity.class);
		
		addressEntity.setAddressId(util.generateStringId(30));
		
		addressEntity.setUser(userEntity);
		
		AddressEntity savedAddresse = addressRepository.save(addressEntity);
		
		AddressDto newAddress = modelMapper.map(savedAddresse, AddressDto.class);
		
		return newAddress;
	}


	@Override
	public AddressDto getAddress(String id) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		AddressEntity addressEntity =(AddressEntity) addressRepository.findByAddressId(id);
		
		AddressDto addressDto = modelMapper.map(addressEntity, AddressDto.class);
		
		return addressDto;
	}


	@Override
	public AddressDto updateAddress(String id, AddressDto addressDto) {
		AddressEntity addressEntity = addressRepository.findByAddressId(id);
		ModelMapper modelMapper = new ModelMapper();
		if (addressEntity == null)
			throw new RuntimeException("Addresse n'est pas trouvé");
		addressEntity.setCity(addressDto.getCity());
		addressEntity.setCountry(addressDto.getCountry());
		addressEntity.setStreet(addressDto.getStreet());
		addressEntity.setPostal(addressDto.getPostal());
		addressEntity.setType(addressDto.getType());
		AddressEntity addressUpdated = addressRepository.save(addressEntity);
		AddressDto newAddressDto = modelMapper.map(addressUpdated, AddressDto.class);
		return newAddressDto;
	}

	
	@Override
	public void deleteAddress(String id) {
		AddressEntity addressEntity = addressRepository.findByAddressId(id);
		ModelMapper modelMapper = new ModelMapper();
		if (addressEntity == null)
			throw new RuntimeException("Addresse n'est pas trouvé");
		addressRepository.delete(addressEntity);
	}
	
}
