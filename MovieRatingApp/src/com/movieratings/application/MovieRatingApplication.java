package com.movieratings.application;

import java.util.ArrayList;
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
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("The Lion King"));
		movies.add(new Movie("The Super Mario Bros. Movie"));
		movies.add(new Movie("Endgame"));
		MovieRatingController.setMovies(movies);
	}

	private static void setDefaultUsers() {
		AccountUser account1 = new AccountUser("Travis", "travis");
		AccountUser account2 = new AccountUser("Sean", "sean");

		account1.getMovieRatings().put(1, 4);
		account1.getMovieRatings().put(2, 5);


		account2.getMovieRatings().put(1, 3);
		account2.getMovieRatings().put(2, 5);

		List<AccountUser> users = new ArrayList<>();
		users.add(account1);
		users.add(account2);

		MovieRatingController.setUsers(users);
	}

}
