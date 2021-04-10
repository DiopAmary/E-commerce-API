package com.enset.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enset.entities.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	public UserEntity findByEmail(String email);
	public UserEntity findByUserId(String userId);
	
	@Query(value = "select * from users",nativeQuery=true)
	public Page<UserEntity> findAllUser(Pageable pageableRequest);
	
	@Query(value = "select * from users u where (u.first_name LIKE %:search% or u.last_name LIKE %:search%) AND u.email_verification_status=:status",nativeQuery=true)
	public Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest,@Param("search") String search,@Param("status") int status);

}
