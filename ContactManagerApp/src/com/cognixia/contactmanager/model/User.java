package com.cognixia.contactmanager.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String email;
	private String password;
	private List<Contact> contacts = new ArrayList<>();

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void addContact(Contact contact) {
		contacts.add(contact);
	}

}
