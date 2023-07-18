package com.cognixia.contactmanager.model;

import java.util.Date;

public class Contact {
	private String name;
	private String phone;
	private String email;
	private Date dateCreated = new Date();

	public Contact(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Contact [Name: " + name + ", Phone:" + phone + ", Email:" + email + ", Date Created: " + dateCreated + "]";
	}
	
	
	
}
