package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
	
	// Autowire the repository to create an instance
	@Autowired
	UserRepository repo;
	
	public List<User> getUsers() {
		return repo.findAll();
	}
	
	public User getUserById(int id) throws ResourceNotFoundException {
		Optional<User> found = repo.findById((long) id);
		
		if (found.isEmpty()) {
			throw new ResourceNotFoundException("User");
		}
		
		return found.get();
	}
	
	public User createUser(User user) {
		return repo.save(user);
	}
	
	public boolean deleteUser(int id) throws ResourceNotFoundException {
		boolean exists = repo.existsById((long) id);
		
		if (!exists) {
			throw new ResourceNotFoundException("User");
		}
		
		repo.deleteById((long) id);
		return true;
	}
	
	
}
