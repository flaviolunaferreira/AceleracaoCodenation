package com.cit.projetoPratico.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cit.projetoPratico.Model.Entity.ErrosEntity;
import com.cit.projetoPratico.Model.Repository.ErrosRepository;

@RestController
@RequestMapping("/api")
public class ErrosController {

	@Autowired
	private ErrosRepository repository;
		
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}) 
	public ErrosEntity saveErros(@RequestBody ErrosEntity errosEntity) {
		repository.save(errosEntity);
		return errosEntity;
	}
	
	
	public @ResponseBody Iterable<ErrosEntity> findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = "/{pageNumber}/{itens}")
	public Iterable<ErrosEntity> findAllPage(@PathVariable int pageNumber,@PathVariable int itens) {
		if (itens > 50 ) itens = 50;
		Pageable page = PageRequest.of(pageNumber, itens);
		return repository.findAll(page);
	}
	
	
}
