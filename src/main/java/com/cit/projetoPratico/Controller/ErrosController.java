package com.cit.projetoPratico.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cit.projetoPratico.Model.Entity.ErrosEntity;
import com.cit.projetoPratico.Model.Entity.LevelEnum;
import com.cit.projetoPratico.Model.Repository.ErrosJpaRepository;
import com.cit.projetoPratico.Model.Repository.ErrosRepository;

@CrossOrigin(origins = "http://localhost:3000") //so requesições deste endereço que vão ser respondidas
@RestController
@RequestMapping("/api")
public class ErrosController {

	@Autowired		
	private ErrosRepository repository;
	
	@Autowired
	private ErrosJpaRepository jpaRepository;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED )
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
	public ErrosEntity getErrosById(@PathVariable Long id) { 			
		return repository.findById(id).orElseThrow( () -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro não encontrado"));
	}

    @GetMapping(path = "api/{pageNumber}/{itens}")
    public ResponseEntity<?> find( ErrosEntity filtro, @PathVariable int pageNumber,@PathVariable int itens ){
    	
		if (itens > 50 ) itens = 50;
		Pageable page = PageRequest.of(pageNumber, itens);
		
        ExampleMatcher matcher = ExampleMatcher.matching()
        							.withIgnoreNullValues()
                                    .withIgnoreCase()
                                    .withStringMatcher(StringMatcher.CONTAINING);

        Example<ErrosEntity> example = Example.of(filtro, matcher);
        Page<ErrosEntity> lista = jpaRepository.findAll(example, page);
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/level/{level}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Iterable<ErrosEntity> listByLevel(@PathVariable LevelEnum level) {
    	return jpaRepository.findByLevel(level);
    }
    
//    public ResponseEntity<?> resumo() {    	
//    	Optional<ErrosEntity> erros = jpaRepository.findByLevel(LevelEnum.ERROR);
//    	Optional<ErrosEntity> worning = jpaRepository.findByLevel(LevelEnum.WARNING);
//    	Optional<ErrosEntity> info = jpaRepository.findByLevel(LevelEnum.INFO);
//    	
//
//    	
//    	return ResponseEntity.ok(erros);
//    }
    
	
}
