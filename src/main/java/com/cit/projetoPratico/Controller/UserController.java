package com.cit.projetoPratico.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cit.projetoPratico.Model.Entity.UserEntity;
import com.cit.projetoPratico.Model.Repository.UserRepository;

@RestController
@RequestMapping("user")
public class UserController {

	private UserRepository repository;

	@Autowired
	public UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserEntity saveUser(@RequestBody UserEntity userEntity) {
		return repository.save(userEntity);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Iterable<UserEntity> findAllUserAll() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public UserEntity findByIdUser(@PathVariable Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não Encontrado!"));
	}
	
	@GetMapping("/name/{name}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public UserEntity findByName(@PathVariable String name) {
		return repository.findByNameContainingIgnoringCase(name)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Desculpe, mas não consguir encontar o nome do Usuário!"
			)); 
	}
	
	@GetMapping("/email/{email}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public UserEntity findByEmail(@PathVariable String email) {
		return repository.findByEmailContainingIgnoringCase(email)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email do usuário não encontrado!"));
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public UserEntity putUser(@RequestBody UserEntity userEntity) {
		return repository.findById(userEntity.getId())
			.map( user -> repository.save(userEntity))
			.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);			
	}
	
}
