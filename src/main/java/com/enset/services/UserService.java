package com.enset.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.enset.dto.UserDto;

public interface UserService extends UserDetailsService {
	
	public UserDto createUser(UserDto userDto, String role);

	public UserDto getUser(String email);

	public UserDto getUserByUserId(String UserId);

	public UserDto updateUser(String id, UserDto userDto);

	public void deleteUser(String id);

	public List<UserDto> getUsers(int page, int limit, String search, int status);

	
}
