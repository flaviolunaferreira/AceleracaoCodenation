package com.cit.projetoPratico.model.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cit.projetoPratico.model.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmailContainingIgnoreCase(String email);
	Optional<UserEntity> findByNameContainingIgnoreCase(String name);

}
