package com.enset.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;


@Entity(name = "roles") 
@Data
public class RoleEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = true,length = 5)
	private String libelle;
	
	@OneToMany(mappedBy = "role",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<UserEntity> listUser = new ArrayList<UserEntity>();
}
