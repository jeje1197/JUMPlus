package com.movieratings.model;

import java.util.HashMap;
import java.util.Map;

public class AccountUser {
	String email;
	String password;
	
	Map<Integer, Integer> movieRatings = new HashMap<>();
	
	public AccountUser(String email, String password) {
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

	public Map<Integer, Integer> getMovieRatings() {
		return movieRatings;
	}

	public void setMovieRatings(Map<Integer, Integer> movieRatings) {
		this.movieRatings = movieRatings;
	}

}
