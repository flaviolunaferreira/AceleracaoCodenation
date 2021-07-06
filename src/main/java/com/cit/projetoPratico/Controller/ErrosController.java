package com.cit.projetoPratico.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cit.projetoPratico.Model.Entity.ErrosEntity;
import com.cit.projetoPratico.Model.Repository.ErrosJpaRepository;
import com.cit.projetoPratico.Model.Repository.ErrosRepository;

@RestController
@RequestMapping("/api")
public class ErrosController {

	@Autowired		
	private ErrosRepository repository;
	
	@Autowired
	private ErrosJpaRepository jpaRepository;

	@PostMapping
	public ErrosEntity saveErros(@RequestBody ErrosEntity errosEntity) {
		repository.save(errosEntity);
		return errosEntity;
	}
	

	@GetMapping(path = "/{pageNumber}/{itens}")
	@ResponseBody
	public Iterable<ErrosEntity> findAllPage(@PathVariable int pageNumber,@PathVariable int itens) {
		if (itens > 50 ) itens = 50;
		Pageable page = PageRequest.of(pageNumber, itens);
		return repository.findAll(page);
	}
	
	@GetMapping(path = "/descricao/{description}")
	@ResponseBody
	public Iterable<ErrosEntity> findByDescription(@PathVariable String description) {
		return repository.findByDescriptionContainingIgnoreCase(description);
	}
	
	@GetMapping("/id/{id}")
	@ResponseBody
	public ResponseEntity<?> getErrosById(@PathVariable Long id) { 			
		Optional<ErrosEntity> errosEntity = repository.findById(id);
		if (errosEntity.isPresent()) return ResponseEntity.ok(errosEntity.get());
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ErrosEntity errosEntity) {
		return repository
				.findById(id)
				.map( erroId -> {
					errosEntity.setId(erroId.getId());
					repository.save(errosEntity);
					return ResponseEntity.ok(errosEntity);
				}).orElseGet( () -> ResponseEntity.notFound().build() ); 	
	}
	

    @GetMapping("/api")
    public ResponseEntity<?> find( ErrosEntity filtro ){
    	
        ExampleMatcher matcher = ExampleMatcher.matching()
        							.withIgnoreNullValues()
                                    .withIgnoreCase()
                                    .withStringMatcher(StringMatcher.CONTAINING);

        Example<ErrosEntity> example = Example.of(filtro, matcher);
        List<ErrosEntity> lista = jpaRepository.findAll(example);
        return ResponseEntity.ok(lista);
    }

	
}
