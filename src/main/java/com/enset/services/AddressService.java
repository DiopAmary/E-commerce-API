package com.enset.services;

import java.util.List;

import com.enset.dto.AddressDto;

public interface AddressService {
	public List<AddressDto> getAllAddresses(String email);
	public AddressDto createAddress(AddressDto addressDto,String email);
	public AddressDto getAddress(String id);
	public AddressDto updateAddress(String id,AddressDto addressDto);
	public void deleteAddress(String id);
	
}
