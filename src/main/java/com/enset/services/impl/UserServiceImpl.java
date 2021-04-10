package com.enset.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.enset.dto.AddressDto;
import com.enset.dto.UserDto;
import com.enset.entities.RoleEntity;
import com.enset.entities.UserEntity;
import com.enset.repositories.RoleRepository;
import com.enset.repositories.UserRepository;
import com.enset.services.UserService;
import com.enset.utils.Utils;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	Utils util;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user, String role) {

		UserEntity checkUser = userRepository.findByEmail(user.getEmail());
		RoleEntity roleType = roleRepository.findByLibelle(role);
		
		if (checkUser != null)
			throw new RuntimeException("User Already exists !");
		
		if(user.getAddresses()!=null) {	
		
			for (int i = 0; i < user.getAddresses().size(); i++) {
				AddressDto addressDto = user.getAddresses().get(i);
				addressDto.setUser(user);
				addressDto.setAddressId(util.generateStringId(30));
				//user.getAddresses().set(i, addressDto);
			}
		}
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);		
		userEntity.setRole(roleType);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(util.generateStringId(32));
		
		UserEntity newUser = userRepository.save(userEntity);
		
		UserDto userDto = modelMapper.map(newUser, UserDto.class);
		
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		
		ModelMapper modelMapper = new ModelMapper();
		
		UserDto userDto =modelMapper.map(userEntity, UserDto.class);
		
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userEntity, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {	
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		//userEntity.setFirstName(userDto.getFirstName());
		//userEntity.setLastName(userDto.getLastName());
		ModelMapper modelMapper = new ModelMapper();
		userEntity = modelMapper.map(userDto, UserEntity.class);
		UserEntity userUpdated = userRepository.save(userEntity);
		return modelMapper.map(userUpdated, UserDto.class);
	}
	
	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null)
			throw new UsernameNotFoundException(userId);
		userRepository.delete(userEntity);
		System.out.println("user deleted successfully !!");
	}
	
	@Override
	public List<UserDto> getUsers(int page, int limit,String search, int status) {
		if (page > 0)
			page -= 1;
		
		List<UserDto> userDtos = new ArrayList<UserDto>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> users;
		if(search.isEmpty()) {
			users = userRepository.findAllUser(pageableRequest);
		}else {
			users = userRepository.findAllUserByCriteria(pageableRequest, search,status);
		}
		ModelMapper modelMapper  =new ModelMapper();
		for (UserEntity userEntity : users) {
			UserDto user =modelMapper.map(userEntity, UserDto.class);
			userDtos.add(user);
		}
		return userDtos;
	}
}
