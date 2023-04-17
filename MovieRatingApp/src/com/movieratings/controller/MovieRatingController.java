package com.movieratings.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.movieratings.exception.InvalidInputException;
import com.movieratings.model.AccountUser;
import com.movieratings.model.Movie;
import com.movieratings.utils.ColorPrinter;
import com.movieratings.utils.ConsolePrinter;
import com.movieratings.utils.ConsoleScanner;
import com.movieratings.utils.MovieCalculator;
import com.movieratings.utils.PrettyFormatter;

public class MovieRatingController {
	private static List<Movie> movies = new ArrayList<>();
	private static List<AccountUser> users = new ArrayList<>();
	private static AccountUser currentUser = null;

	private static final String EMAIL_REGEX = ".+@.+\\..+";
	private static final String PASSWORD_REGEX = ".{6,24}";
	private static final String ANY_REGEX = "(.|\\s)*";

	public static void setMovies(List<Movie> movies) {
		MovieRatingController.movies = movies;
	}

	public static void setUsers(List<AccountUser> users) {
		MovieRatingController.users = users;
	}

	public static void mainMenu() {
		String[] menuOptions = {
				"1. REGISTER",
				"2. LOGIN",
				"3. VIEW MOVIES",
				"4. EXIT"
		};

		boolean applicationShouldRun = true;
		while (applicationShouldRun) {
			ColorPrinter.println(ColorPrinter.CYAN, PrettyFormatter.format(menuOptions));

			int selectedOption = -1;
			try {
				ColorPrinter.print(ColorPrinter.GREEN, "Choose an option (1-4): ");
				selectedOption = ConsoleScanner.readInt(1, 4);
				ConsolePrinter.println("");
			} catch (InvalidInputException e) {
				ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
				continue;
			}

			switch (selectedOption) {
			case 1:
				MovieRatingController.register();
				break;

			case 2:
				MovieRatingController.login();
				if (currentUser != null) {
					MovieRatingController.accountMenu();
				}
				break;

			case 3:
				MovieRatingController.viewMovies();
				break;

			case 4:
				applicationShouldRun = false;
			}
		}


		ConsoleScanner.close();
	}

	public static void register() {
		ColorPrinter.println(ColorPrinter.BLUE, PrettyFormatter.format(new String[] {
				"REGISTER"
		}));

		String email = null;
		try {
			ColorPrinter.print(ColorPrinter.GREEN, "Enter your email: ");
			email = ConsoleScanner.readString(EMAIL_REGEX, "Invalid email format. Valid format ex:'email@yahoo.com'");
		} catch (InvalidInputException e) {
			ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
			return;
		}

		String password = null;
		try {
			ColorPrinter.print(ColorPrinter.GREEN, "Enter your password: ");
			password = ConsoleScanner.readString(PASSWORD_REGEX, "Invalid password format. Valid format ex:'xxxx...'");
		} catch (InvalidInputException e) {
			ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
			return;
		}

		AccountUser newUser = new AccountUser(email, password);
		users.add(newUser);

		ColorPrinter.println(ColorPrinter.YELLOW, "New Account Created!\n");
	}

	public static void login() {
		ColorPrinter.println(ColorPrinter.BLUE, PrettyFormatter.format(new String[] {
				"LOGIN"
		}));

		final String email;
		try {
			ColorPrinter.print(ColorPrinter.GREEN, "Enter your email: ");
			email = ConsoleScanner.readString(ANY_REGEX, "Unexpected format.");
		} catch (InvalidInputException e) {
			ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
			return;
		}

		final String password;
		try {
			ColorPrinter.print(ColorPrinter.GREEN, "Enter your password: ");
			password = ConsoleScanner.readString(ANY_REGEX, "Unexpected format.");
		} catch (InvalidInputException e) {
			ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
			return;
		}

		Optional<AccountUser> accountUser = users.stream().filter(account ->
		account.getEmail().equals(email) && account.getPassword().equals(password)
				).findFirst();

		if (accountUser.isEmpty()) {
			currentUser = null;
			ColorPrinter.println(ColorPrinter.RED, "Failed to login.\n");
			return;
		}

		currentUser = accountUser.get();
		ColorPrinter.println(ColorPrinter.YELLOW, "Logged in successfully.\n");
	}

	public static void viewMovies() {
		ColorPrinter.println(ColorPrinter.CYAN, PrettyFormatter.format(new String[] {
				"VIEWING MOVIES"
		}));

		List<String> lines = new ArrayList<>();
		lines.add("Movie Avg.           Rating           # of Ratings");

		//		List<String> movieName


		for (int i = 0; i < movies.size(); i++) {
			lines.add(i + ". " + movies.get(i).getName() + " ".repeat(10) + 
					MovieCalculator.getAverage(movies.get(i), users) + " ".repeat(10) + 
					MovieCalculator.getNumberOfRatings(movies.get(i), users));
		}

		if (movies.isEmpty()) {
			lines.add("None");
		}
		
		

		System.out.println(String.join("\n", lines));


		//		ColorPrinter.println(ColorPrinter.CYAN, PrettyFormatter.format((String[])lines.toArray()));
	}


	public static void accountMenu() {

	}
}
