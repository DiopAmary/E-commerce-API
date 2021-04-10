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

import org.springframework.security.core.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Transient
@Data
@Entity(name = "roles") @AllArgsConstructor @NoArgsConstructor
public class RoleEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = true,length = 5)
	private String libelle;
	
	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<UserEntity> listUser = new ArrayList<UserEntity>();
}
