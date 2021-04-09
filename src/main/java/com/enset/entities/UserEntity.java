package com.enset.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Data
@Entity(name = "users") @ToString
public class UserEntity implements Serializable{

	private static final long serialVersionUID = -5763827745308343856L;

	@Id //auto increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false, length = 50)
	private String firstName;
	
	@Column(nullable = false,length = 50)
	private String lastName;
	
	@Column(nullable = false, length = 120, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String encryptedPassword;
	
	@Column(nullable = true)
	private String emailVerificationToken;
	
	@Column(nullable = true)
	private boolean admin=false;
	
	@Column(nullable = true)
	private String photo=null;
	
	@Column(nullable = false)
	private Boolean emailVerificationStatus=false;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<AddressEntity> addresses;
	
	@ManyToOne
	@JoinColumn(name = "roles_id", nullable = true)
	private RoleEntity role=null;
	
	//rating review drame property !!!!
	
}
