package com.enset.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import com.enset.entities.CommandeEntity;
import com.enset.entities.UserEntity;

@Repository
public interface CommandeRepository extends JpaRepository<CommandeEntity,Long>{
	
	public Page<CommandeEntity> findAll(Pageable pageable);
	public CommandeEntity findByCodeCmd(String codeCmd);
	public Page<CommandeEntity> findByEtatCmd(String etatCmd,Pageable pageable);
	public Page<CommandeEntity> findByDateCmd(Date dateCmd,Pageable pageable);
	public Page<CommandeEntity> findByUser(UserEntity user,Pageable pageable);
}
