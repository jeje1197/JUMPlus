package com.movieratings.utils;

import java.util.List;

import com.movieratings.model.AccountUser;
import com.movieratings.model.Movie;

public class MovieCalculator {
	
	public static double getAverage(Movie movie, List<AccountUser> users) {
		double sum = 0;
		int numberOfRatings = 0;
		
		for (AccountUser user: users) {
			if (user.getMovieRatings().containsKey(movie.getId())) {
				numberOfRatings++;
				System.out.println(user.getMovieRatings().get(movie.getId()));
				sum += user.getMovieRatings().get(movie.getId()); 
			}
		}
		
		return sum / numberOfRatings;
	}
	
	public static int getNumberOfRatings(Movie movie, List<AccountUser> users) {
		int numberOfRatings = 0;
		
		return numberOfRatings;
	}
}
