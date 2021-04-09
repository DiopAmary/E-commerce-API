package com.enset.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class RoleDto {
	
	private long id;
	
	private String libelle;
	
	private List<UserDto> listUser = new ArrayList<UserDto>();

}
