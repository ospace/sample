package com.tistory.ospace;

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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/{id}")
	public Mono<User> getUserById(@PathVariable String id) {
		return userRepo.findById(id);
	}
	
	@GetMapping
	public Flux<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<User> saveUser(@RequestBody User user) {
		return userRepo.save(user);
	}
	
	@PutMapping("/{id}")
	public Mono<User> updateUser(@PathVariable String id, @RequestBody User user) {
		user.setId(id);
		return userRepo.save(user);
	}
	
	@DeleteMapping
	public void deleteUser(@PathVariable String id) {
		userRepo.delete(id);
	}
}
