package com.cit.projetoPratico.Model.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cit.projetoPratico.Model.Entity.ErrosEntity;

@Repository
public interface ErrosRepository extends PagingAndSortingRepository<ErrosEntity, Long> {

	Iterable<ErrosEntity> findByDescriptionContainingIgnoreCase(String description);
	
}
