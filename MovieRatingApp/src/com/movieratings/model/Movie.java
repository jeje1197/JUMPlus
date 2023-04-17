package com.movieratings.model;

public class Movie {
	private static int generatedId = 1;
	private final Integer id;
	private final String name;
	
	public Movie(String name) {
		this.id = generatedId++;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
