package com.cit.projetoPratico.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cit.projetoPratico.model.entity.ErrosEntity;

@Repository
public interface ErrosRepository extends PagingAndSortingRepository<ErrosEntity, Long> {

	Iterable<ErrosEntity> findByDescriptionContainingIgnoreCase(String description);

}
