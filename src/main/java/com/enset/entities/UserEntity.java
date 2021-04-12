package com.enset.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

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
	private String numTel;
	
	@Column(nullable = false, length = 1)
	private String sexUser;
	
	@Column(nullable = false)
	private boolean status=true;
	
	@Column(nullable = false)
	private String userName;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date createdAt;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date updatedAt;
	
	@Column(nullable = false)
	private Boolean emailVerificationStatus=false;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<AddressEntity> addresses;
	
//	@ManyToOne
//	@JoinColumn(name = "roles_id", nullable = true)
//	private RoleEntity role;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
            @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Set<RoleEntity> roles = new HashSet<>();
	
	
	
	
	@PrePersist
	public void setCreatedAt() {
		this.createdAt= new Date();
		this.updatedAt = new Date();
	}
	
	@PreUpdate
	public void seUpdatedAt() {
		this.updatedAt= new Date();
	}
	
	//rating review drame property !!!!
	
}
