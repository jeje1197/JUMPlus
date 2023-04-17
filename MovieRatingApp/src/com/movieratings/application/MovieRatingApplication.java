package com.movieratings.application;

import java.util.List;

import com.movieratings.controller.MovieRatingController;
import com.movieratings.model.AccountUser;
import com.movieratings.model.Movie;

public class MovieRatingApplication {

	public static void main(String[] args) {
		setDefaultMovies();
		setDefaultUsers();
		MovieRatingController.mainMenu();
	}

	private static void setDefaultMovies() {
		MovieRatingController.setMovies(List.of(
				new Movie("The Lion King"),
				new Movie("The Super Mario Bros. Movie"),
				new Movie("Endgame"),
				new Movie("The Lion King"),
				new Movie("The Lion King")
				));
	}
	
	private static void setDefaultUsers() {
		AccountUser account1 = new AccountUser("Travis", "travis");
		AccountUser account2 = new AccountUser("Sean", "sean");
		
		account1.getMovieRatings().put(1, 4);
		account1.getMovieRatings().put(2, 5);
		
		
		account2.getMovieRatings().put(1, 3);
		account2.getMovieRatings().put(2, 5);
		account2.getMovieRatings().put(4, 4);
		
		MovieRatingController.setUsers(List.of(account1, account2));
	}

}
