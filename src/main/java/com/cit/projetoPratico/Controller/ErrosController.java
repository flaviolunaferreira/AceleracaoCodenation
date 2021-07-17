package com.cit.projetoPratico.Controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cit.projetoPratico.Model.Entity.ErrosEntity;
import com.cit.projetoPratico.Model.Entity.LevelEnum;
import com.cit.projetoPratico.Model.Entity.Views;
import com.cit.projetoPratico.Model.Repository.ErrosJpaRepository;
import com.cit.projetoPratico.Model.Repository.ErrosRepository;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:3000") //so requesições deste endereço que vão ser respondidas
@RestController
@RequestMapping("/api")
public class ErrosController {

	@Autowired		
	private ErrosRepository repository;
	
	@Autowired
	private ErrosJpaRepository jpaRepository;
	
	@ApiOperation(value="Save a new error")
	@RequestMapping(value = "/", method =  RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseStatus(code = HttpStatus.CREATED )
	public ErrosEntity saveErros(@RequestBody ErrosEntity errosEntity) {
		repository.save(errosEntity);
		return errosEntity;
	}	
	
	@ApiOperation(value="Receive a list of errors by page number and number of items")
	@RequestMapping(value = "/{pageNumber}/{itens}", method =  RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(Views.Public.class)
	public Iterable<ErrosEntity> findAllPage(@PathVariable int pageNumber,@PathVariable int itens) {
		if (itens > 50 ) itens = 50;
		Pageable page = PageRequest.of(pageNumber, itens);
		return repository.findAll(page);
	}
	
	@ApiOperation(value="Receive a list of errors with the description matching a string")
	@RequestMapping(value = "/descricao/{string}", method =  RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(Views.Public.class)
	public Iterable<ErrosEntity> findByDescription(@PathVariable String string) {
		return repository.findByDescriptionContainingIgnoreCase(string);
	}
	
	@ApiOperation(value="Receive an error by id")
	@RequestMapping(value = "/id/{id}", method =  RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(Views.Log.class)
	public ErrosEntity getErrosById(@PathVariable Long id) { 			
		return repository.findById(id).orElseThrow( () -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro não encontrado"));
	}
	
	@ApiOperation(value="Receive a list of errors filtered by attribute",
			notes="Use optional params to filter the request (api/filter/{pageNumber}/{itens}?{attribute}={string})")
	@RequestMapping(value = "filter/{pageNumber}/{itens}", method =  RequestMethod.GET, produces="application/json")
	@JsonView(Views.Public.class)
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
	
	@ApiOperation(value="Receive a list of errors filtered by level type")
	@RequestMapping(value = "/level/{level}", method =  RequestMethod.GET, produces="application/json")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
	@JsonView(Views.Public.class)
    public Iterable<ErrosEntity> listByLevel(@PathVariable LevelEnum level) {
    	return jpaRepository.findByLevel(level);
    }
}
