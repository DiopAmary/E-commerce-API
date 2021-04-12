package com.enset.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
public class RoleDto implements Serializable{
	
	private static final long serialVersionUID = 7777679630373678489L;
	
	private long id;
	private String libelle;
	private String designation;

}
