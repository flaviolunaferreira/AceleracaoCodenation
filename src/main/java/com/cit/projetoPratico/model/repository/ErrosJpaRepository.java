package com.cit.projetoPratico.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cit.projetoPratico.model.entity.ErrosEntity;
import com.cit.projetoPratico.model.entity.LevelEnum;


public interface ErrosJpaRepository extends JpaRepository<ErrosEntity, Long>{
	
	Iterable<ErrosEntity> findByLevel(LevelEnum level);
	
}
