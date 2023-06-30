package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceAlreadyExistsException;
import com.cognixia.jump.model.Furniture;
import com.cognixia.jump.repository.FurnitureRepository;

@Service
public class FurnitureService {
	
	@Autowired
	FurnitureRepository repo;
	
	public List<Furniture> getAllFurniture() {
		return repo.findAll();
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
}
