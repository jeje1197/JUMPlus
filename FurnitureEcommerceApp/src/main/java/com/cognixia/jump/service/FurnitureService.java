package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceAlreadyExistsException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Furniture;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.FurnitureRepository;
import com.cognixia.jump.repository.OrderRepository;
import com.cognixia.jump.repository.UserRepository;

@Service
public class FurnitureService {

	@Autowired
	FurnitureRepository repo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	OrderRepository orderRepo;

	public List<Furniture> getAllFurniture() {
		return repo.findAll();
	}

	public Furniture getFurnitureById(int id) throws ResourceNotFoundException {
		Optional<Furniture> found = repo.findById(id);
		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Furniture", id);
		}

		return found.get();
	}

	public Furniture createFurniture(Furniture furniture) throws ResourceAlreadyExistsException {
		Optional<Furniture> found = repo.findByName(furniture.getName());
		if (found.isPresent()) {
			throw new ResourceAlreadyExistsException("Furniture with name '" + furniture.getName() +
					"'");
		}
		
		found = repo.findByImageUrl(furniture.getImageUrl());
		if (found.isPresent()) {
			throw new ResourceAlreadyExistsException("Furniture with imageUrl '" + furniture.getImageUrl() +
					"'");
		}
		
		return repo.save(furniture);
	}
	
	public boolean updateFurniture(int id, Furniture furniture) throws ResourceNotFoundException {
		Furniture furnitureToUpdate = repo.getReferenceById(id);
		if (furnitureToUpdate == null) {
			throw new ResourceNotFoundException("Furniture", id);
		}

		furnitureToUpdate.setName(furniture.getName());
		furnitureToUpdate.setQuantity(furniture.getQuantity());
		furnitureToUpdate.setPrice(furniture.getPrice());
		furnitureToUpdate.setImageUrl(furniture.getImageUrl());
		
		repo.save(furnitureToUpdate);
		return true;
	}

	public boolean deleteFurniture(int id) throws ResourceNotFoundException {
		boolean exists = repo.existsById(id);
		if (!exists) {
			throw new ResourceNotFoundException("Furniture", id);
		}

		repo.deleteById(id);
		return true;
	}

	public boolean purchaseFurniture(Integer furnitureId, Integer userId) throws ResourceNotFoundException {
		Furniture furniture = repo.getReferenceById(furnitureId);
		User user = userRepo.getReferenceById(userId);
		if (furniture == null || furniture.getQuantity() <= 0) {
			throw new ResourceNotFoundException("Furniture", furnitureId);
		}
		
		if (user == null) {
			throw new ResourceNotFoundException("User", userId);
		}

		furniture.setQuantity(furniture.getQuantity() - 1);
		return true;
	}
}
