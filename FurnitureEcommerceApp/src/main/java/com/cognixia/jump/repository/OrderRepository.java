package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	@Query("select * from order where id = user_id")
	public List<Order> findAllByUserId(Integer id);
}
