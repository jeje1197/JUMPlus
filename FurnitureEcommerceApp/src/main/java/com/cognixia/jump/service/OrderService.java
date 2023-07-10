package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository repo;

	public List<Order> getAllOrders() {
		return repo.findAll();
	}

	public List<Order> getAllOrdersByUser(Integer id) {
		return repo.findAllByUserId(id);
	}

	public Order createOrder(Order order) {
		return repo.save(order);
	}
	
	public Order getOrderById(Integer id) throws ResourceNotFoundException {
		Optional<Order> found = repo.findById(id);
		if (found == null) {
			throw new ResourceNotFoundException("Order", id);
		}

		return found.get();
	}

	public boolean deleteOrder(int id) throws ResourceNotFoundException {
		boolean exists = repo.existsById(id);
		if (!exists) {
			throw new ResourceNotFoundException("Order", id);
		}

		repo.deleteById(id);
		return true;
	}
}

