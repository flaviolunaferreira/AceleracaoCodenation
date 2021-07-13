package com.cit.projetoPratico.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cit.projetoPratico.Model.Entity.ErrosEntity;
import com.cit.projetoPratico.Model.Entity.LevelEnum;


public interface ErrosJpaRepository extends JpaRepository<ErrosEntity, Long>{
	
	Iterable<ErrosEntity> findByLevel(LevelEnum level);
	
}
