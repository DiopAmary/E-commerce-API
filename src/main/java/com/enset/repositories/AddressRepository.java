package com.enset.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enset.entities.AddressEntity;
import com.enset.entities.UserEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long>{
	public List<AddressEntity> findByUser(UserEntity user);
	public AddressEntity findByAddressId(String id);
}
