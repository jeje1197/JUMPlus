package com.movieratings.utils;

import java.util.List;

import com.movieratings.model.AccountUser;
import com.movieratings.model.Movie;

public class MovieCalculator {
	
	public static int longestMovieName(List<Movie> movies) {
		int longestName = 0;
		for (Movie movie: movies) {
			if (movie.getName().length() > longestName) {
				longestName = movie.getName().length();
			}
		}
		return longestName;
	}
	
	public static double getAverage(Movie movie, List<AccountUser> users) {
		double sum = 0;
		int numberOfRatings = 0;
		
		for (AccountUser user: users) {
			if (user.getMovieRatings().containsKey(movie.getId())) {
				numberOfRatings++;
				sum += user.getMovieRatings().get(movie.getId()); 
			}
		}
		
		
		
		return sum / numberOfRatings;
	}
	
	public static int getNumberOfRatings(Movie movie, List<AccountUser> users) {
		int numberOfRatings = 0;
		
		for (AccountUser user: users) {
			if (user.getMovieRatings().containsKey(movie.getId())) {
				numberOfRatings++;
			}
		}
		
		return numberOfRatings;
	}
}
