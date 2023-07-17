package com.dollarsbank.expensetracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dollarsbank.expensetracker.exception.InvalidCredentialsException;
import com.dollarsbank.expensetracker.model.Account;
import com.dollarsbank.expensetracker.model.Customer;
import com.dollarsbank.expensetracker.util.ColorPrinter;
import com.dollarsbank.expensetracker.util.ConsolePrinter;
import com.dollarsbank.expensetracker.util.ConsoleScanner;
import com.dollarsbank.expensetracker.util.PrettyFormatter;

public class MenuController {
	private static List<Account> accounts = new ArrayList<>();

	public static void displayOptions() {

		while (true) {
			String[] lines = {
					"Option 1: Create new account",
					"Option 2: Login",
					"Option 3: Exit",
			};

			ColorPrinter.print(ColorPrinter.ANSI_BLUE, PrettyFormatter.format(lines));

			int option;
			try {
				option = ConsoleScanner.readInt(1, 3, "Choose an option 1-3:");
			} catch (Exception e) {
				ColorPrinter.print(ColorPrinter.ANSI_RED, e.getMessage());
				continue;
			} finally {
				ConsolePrinter.newline();
			}

			switch (option) {
				case 1: createAccount(); break;
				case 2: login(); break;
				case 3: endSession(); break;
			}
		}
	}

	private static void createAccount() {
		String[] lines = {
				"Create new account"
		};

		ColorPrinter.print(ColorPrinter.ANSI_BLUE, PrettyFormatter.format(lines));
		
		try {
			String name = ConsoleScanner.readString(
					"([A-Za-z\\.]\\s?)+", 
					"Enter your name:",
					"Please enter a valid name."
			);
			String email = ConsoleScanner.readString(
					".+@.+\\..+", 
					"Enter your email:",
					"Please enter a valid email address."
			);
			String password = ConsoleScanner.readString(
					".{8}.*", 
					"Create a password:",
					"Please enter a password of 8+ charactes."
			);
			
			Customer customer = new Customer(name, email, password);
			Account account = new Account(customer);
			accounts.add(account);
			ColorPrinter.print(ColorPrinter.ANSI_GREEN, "Account created!");
		} catch (Exception e) {
			ColorPrinter.print(ColorPrinter.ANSI_RED, e.getMessage());
		} finally {
			ConsolePrinter.newline();
		}
	}

	private static void login() {
		String[] lines = {
				"Login"
		};

		ColorPrinter.print(ColorPrinter.ANSI_BLUE, PrettyFormatter.format(lines));
		final String email = ConsoleScanner.readString("Email: ");
		final String password = ConsoleScanner.readString("Username: ");
		
		try {
			Optional<Account> found = accounts.stream()
					.filter(account -> {
						Customer customer = account.getCustomer();
						return customer.getUsername().equals(email) &&
								customer.getPassword().equals(password);
					})
					.findFirst();
			
			if (found.isEmpty()) {
				throw new InvalidCredentialsException();
			}
			
			ExpenseController controller = new ExpenseController(found.get());
			displayExpenseMenu(controller);
			ColorPrinter.print(ColorPrinter.ANSI_GREEN, "Logged in!");
		} catch (Exception e) {
			ColorPrinter.print(ColorPrinter.ANSI_RED, e.getMessage());
		} finally {
			ConsolePrinter.newline();
		}
		
		
	}

	private static void endSession() {
		ColorPrinter.print(ColorPrinter.ANSI_GREEN, "Session terminated.\nGOODBYE!");
		System.exit(0);
	}
	
	private static void displayExpenseMenu(ExpenseController controller) {
		while (true) {
			String[] lines = {
					"Option 1: Add new expense",
					"Option 2: Remove expense",
					"Option 3: Set Monthly & Yearly Budget",
					"Option 4: View 5 Upcoming Expenses",
					"Option 5: Compare Monthly Budget",
					"Option 6: Compare Yearly Budget",
					"Option 7: Display Customer Information",
					"Option 8: Sign Out"
			};

			ColorPrinter.print(ColorPrinter.ANSI_BLUE, PrettyFormatter.format(lines));

			int option;
			try {
				option = ConsoleScanner.readInt(1, 8, "Choose an option 1-8:");
			} catch (Exception e) {
				ColorPrinter.print(ColorPrinter.ANSI_RED, e.getMessage());
				continue;
			} finally {
				ConsolePrinter.newline();
			}

			switch (option) {
				case 1: controller.addExpense(); break;
				case 2: controller.removeExpense(); break;
				case 3: controller.setMonthlyAndYearlyBudget(); break;
				case 4: controller.view5UpcomingExpenses(); break;
				case 5: controller.compareMonthlyBudget(); break;
				case 6: controller.compareYearlyBudget(); break;
				case 7: controller.displayCustomerInformation(); break;
				case 8: endSession(); break;
			}
		}
	}

}
