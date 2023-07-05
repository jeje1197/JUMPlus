package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceAlreadyExistsException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Furniture;
import com.cognixia.jump.service.FurnitureService;

@RestController
@RequestMapping("/api")
public class FurnitureController {

	@Autowired
	FurnitureService service;

	@GetMapping("/furniture")
	public List<Furniture> getAllFurniture() {
		return service.getAllFurniture();
	}

	@GetMapping("/furniture/{id}")
	public Furniture getFurnitureById(@PathVariable Integer id) throws ResourceNotFoundException {
		return service.getFurnitureById(id);
	}

	@PostMapping("/furniture")
	public ResponseEntity<?> createFurniture(@Valid @RequestBody Furniture furniture) throws ResourceAlreadyExistsException {
		Furniture created = service.createFurniture(furniture);
		
		return ResponseEntity.status(201).body(created);
	}
	
	@PutMapping("/furniture/{id}")
	public ResponseEntity<?> updateFurnitureById(@PathVariable Integer id, @Valid @RequestBody Furniture furniture) throws ResourceNotFoundException {
		service.updateFurniture(id, furniture);
		
		return ResponseEntity.status(204).body(furniture);
	}

	@DeleteMapping("/furniture/{id}")
	public ResponseEntity<?> deleteFurniture(@PathVariable Integer id) throws ResourceNotFoundException {
		service.deleteFurniture(id);
		return ResponseEntity.status(204).body(true);
	}

}
