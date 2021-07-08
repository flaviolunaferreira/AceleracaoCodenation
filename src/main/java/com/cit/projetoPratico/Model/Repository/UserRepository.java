package com.cit.projetoPratico.Model.Repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cit.projetoPratico.Model.Entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmailContainingIgnoringCase(String email);
	Optional<UserEntity> findByNameContainingIgnoringCase(String name);

}
