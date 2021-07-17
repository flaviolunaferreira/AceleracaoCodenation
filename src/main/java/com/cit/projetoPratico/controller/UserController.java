package com.cit.projetoPratico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cit.projetoPratico.model.entity.UserEntity;
import com.cit.projetoPratico.model.repository.UserRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user")
public class UserController {
	
	private UserRepository repository;

	@Autowired
	public UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value="Save a new user")
	@RequestMapping(value = "/", method =  RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserEntity saveUser(@RequestBody UserEntity userEntity) {
		return repository.save(userEntity);
	}
	
	@ApiOperation(value="Receive all users")
	@RequestMapping(value = "/", method =  RequestMethod.GET, produces="application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public Iterable<UserEntity> findAllUserAll() {
		return repository.findAll();
	}
	
	@ApiOperation(value="Find user by id")
	@RequestMapping(value = "/{id}", method =  RequestMethod.GET, produces="application/json")
	@ResponseStatus(code = HttpStatus.FOUND)
	public UserEntity findByIdUser(@PathVariable Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não Encontrado!"));
	}
	
	@ApiOperation(value="Find user by name")
	@RequestMapping(value = "/name/{name}", method =  RequestMethod.GET, produces="application/json")
	@ResponseStatus(code = HttpStatus.FOUND)
	public UserEntity findByName(@PathVariable String name) {
		return repository.findByNameContainingIgnoreCase(name)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Desculpe, mas não consguir encontar o nome do Usuário!"
			)); 
	}
	
	@ApiOperation(value="Find user by email")
	@RequestMapping(value = "/email/{email}", method =  RequestMethod.GET, produces="application/json")
	@ResponseStatus(code = HttpStatus.FOUND)
	public UserEntity findByEmail(@PathVariable String email) {
		return repository.findByEmailContainingIgnoreCase(email)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email do usuário não encontrado!"));
	}
	
	@ApiOperation(value="Update user")
	@RequestMapping(value = "/", method =  RequestMethod.PUT, produces="application/json", consumes="application/json")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public UserEntity putUser(@RequestBody UserEntity userEntity) {
		return repository.findById(userEntity.getId())
			.map( user -> repository.save(userEntity))
			.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
	}
	
	@ApiOperation(value="Delete user by id")
	@RequestMapping(value = "/delete/{id}", method =  RequestMethod.DELETE, produces="application/json")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);			
	}
	
}
