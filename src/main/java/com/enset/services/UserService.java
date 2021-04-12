package com.enset.services;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.enset.dto.RoleDto;
import com.enset.dto.UserDto;

public interface UserService extends UserDetailsService {
	
	public UserDto createUser(UserDto userDto, MultipartFile photo);

	public UserDto getUser(String email);

	public UserDto getUserByUserId(String UserId);

	public UserDto updateUser(String id, UserDto userDtoe, MultipartFile photo);

	public void deleteUser(String id);

	public List<UserDto> getUsers(int page, int limit, String search, int status);

	
}
