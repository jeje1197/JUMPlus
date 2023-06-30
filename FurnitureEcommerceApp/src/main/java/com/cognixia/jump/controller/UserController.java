package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceAlreadyExistsException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	// Autowiring is when you let Spring's IoC container create the instance for you
	@Autowired
	UserService service;
	
	@GetMapping("/user")
	public List<User> getUsers() {
		return service.getUsers(); 
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Integer id) throws ResourceNotFoundException {
		return service.getUserById(id);
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) throws ResourceAlreadyExistsException {
		service.createUser(user);

		// Using ResponseEntity allows you to send a custom status code along
		// with the response body. Without using ResponseEntity you get a default status
		// code of 200 (ok).
		return ResponseEntity.status(201).body(user);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) throws ResourceNotFoundException {
		service.deleteUser(id);
		return ResponseEntity.status(200).body(true);
	}
	
}
