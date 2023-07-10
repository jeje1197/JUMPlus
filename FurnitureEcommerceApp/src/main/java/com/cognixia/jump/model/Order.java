package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "orders")
@Entity
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	@JsonProperty("user")
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@JsonProperty("furniture")
	@ManyToOne
	@JoinColumn(name="furniture_id")
	private Furniture furniture;
	
	public Order() {}

	public Order(Integer id, User user, Furniture furniture) {
		super();
		this.id = id;
		this.user = user;
		this.furniture = furniture;
	}
	
}
