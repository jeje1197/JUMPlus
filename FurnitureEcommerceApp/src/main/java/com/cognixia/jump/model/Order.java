package com.cognixia.jump.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "orders")
@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	Integer id;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="furniture_id", referencedColumnName="id")
	private Furniture furniture;

	public Order(Integer id, User user, Furniture furniture) {
		super();
		this.id = id;
		this.user = user;
		this.furniture = furniture;
	}
	
}
