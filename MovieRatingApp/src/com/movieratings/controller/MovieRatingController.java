package com.movieratings.controller;

import java.util.ArrayList;
import java.util.List;

import com.movieratings.exception.InvalidInputException;
import com.movieratings.model.AccountUser;
import com.movieratings.utils.ColorPrinter;
import com.movieratings.utils.ConsolePrinter;
import com.movieratings.utils.ConsoleScanner;
import com.movieratings.utils.PrettyFormatter;

public class MovieRatingController {
	private static List<AccountUser> users = new ArrayList<>();
	private static final String EMAIL_REGEX = ".+@.+\\..+";
	private static final String PASSWORD_REGEX = ".{6,24}";

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
	}

	public static void viewMovies() {
		ColorPrinter.println(ColorPrinter.BLUE, PrettyFormatter.format(new String[] {
				"VIEW MOVIES"
		}));
	}
}
