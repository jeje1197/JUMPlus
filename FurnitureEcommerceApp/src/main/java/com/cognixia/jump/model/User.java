package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id //Set as primary key for model
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
	private Integer id;

	@Column(unique = true, nullable = false) // Column must be unique and non-null
	@NotBlank
	private String username;

	@Column(nullable = false)
	@NotBlank
	private String password;

	@Column(unique=true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String phone;
	
	public User() {}

	public User(Integer id, String username, String password, String email, String phone) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
