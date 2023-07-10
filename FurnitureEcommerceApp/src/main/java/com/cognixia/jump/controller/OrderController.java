package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.service.FurnitureService;
import com.cognixia.jump.service.OrderService;
import com.cognixia.jump.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FurnitureService furnitureService;

	@GetMapping("/order")
	public List<Order> getAllOrders() {
		return service.getAllOrders();
	}

	@PostMapping("/order")
	public ResponseEntity<?> createOrder(@RequestParam(value="userId") Integer userId,
			@RequestParam(value="furnitureId") Integer furnitureId) throws ResourceNotFoundException {
		Order created = service.createOrder(
				userService.getUserById(userId), furnitureService.getFurnitureById(furnitureId));
		return ResponseEntity.status(401).body(created);
	}
	
	@GetMapping("/order/{userId}")
	public ResponseEntity<?> getOrderByUserId(@PathVariable Integer userId) {
		return ResponseEntity.status(401).body(service.getAllOrdersByUser(userId));
	}
}
