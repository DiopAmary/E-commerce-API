package com.enset.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "addresses")
@Data @NoArgsConstructor @AllArgsConstructor
public class AddressEntity implements Serializable{
	
	private static final long serialVersionUID = 4337331917544341883L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 30, nullable = true)
	private String addressId;
	
	@Column(length = 20, nullable = true)
	private String city;
	
	@Column(length = 20, nullable = true)
	private String country;
	
	@Column(length = 50, nullable = true)
	private String street;
	
	@Column(length = 7, nullable = true)
	private String postal;
	
	@Column(length = 20, nullable = true)
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity user;
}