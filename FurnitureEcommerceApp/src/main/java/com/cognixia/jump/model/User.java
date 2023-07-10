package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
    private List<Order> orders;
	
	public static enum Role {
		ROLE_USER, ROLE_ADMIN	// roles should start with capital ROLE_ and has to be completely in capital letters
	}

	@Id //Set as primary key for model
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Auto increment
	private Integer id;

	@Column(unique=true, nullable=false) // Column must be unique and non-null
	@NotBlank
	private String username;

	@Column(nullable=false)
	@NotBlank
	private String password;

	@Pattern(regexp=".+@.+\\..+")
	@Column(unique=true, nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String phone;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="varchar(16) default 'ROLE_USER'")
	private Role role = Role.ROLE_USER;
	
	@Column(name="enabled", columnDefinition = "boolean default true", nullable=false)
	private Boolean enabled = true; // true or false if the user is enabled
 
	public User() {}

	public User(Integer id, @NotBlank String username, @NotBlank String password, String email, String phone,
			Role role, Boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.enabled = enabled;
		System.out.println(role + " " + enabled);
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
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", phone="
				+ phone + ", role=" + role + ", enabled=" + enabled + "]";
	}
}
