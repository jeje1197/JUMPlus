package com.dollarsbank.expensetracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.dollarsbank.expensetracker.model.Account;
import com.dollarsbank.expensetracker.model.AccountExpense;
import com.dollarsbank.expensetracker.model.AccountExpense.Period;
import com.dollarsbank.expensetracker.util.ColorPrinter;
import com.dollarsbank.expensetracker.util.ConsolePrinter;
import com.dollarsbank.expensetracker.util.ConsoleScanner;
import com.dollarsbank.expensetracker.util.PrettyFormatter;

public class ExpenseController {
	private Account account;

	public ExpenseController(Account account) {
		this.account = account;
	}

	public void addExpense() {
		String[] lines = { "Add expense" };
		ColorPrinter.print(ColorPrinter.ANSI_BLUE, PrettyFormatter.format(lines));

		try {
			String name = ConsoleScanner.readString(
					".+",
					"Expense name:",
					"Please enter a valid name."
					);

			double amount = ConsoleScanner.readDouble(0, Double.MAX_VALUE, "Expense amount: ");
			String period = ConsoleScanner.readString(
					"(Monthly|monthly|Yearly|yearly|m|y)",
					"Expense period: Monthly or Yearly",
					"Please choose a valid period."
					);

			AccountExpense.Period expensePeriod = period.startsWith("m") ? 
					AccountExpense.Period.MONTHLY : AccountExpense.Period.YEARLY;
			account.addExpense(new AccountExpense(name, amount, expensePeriod));
			ColorPrinter.print(ColorPrinter.ANSI_YELLOW, "Expense added!");
		} catch (Exception e) {
			ColorPrinter.print(ColorPrinter.ANSI_RED, e.getMessage());
		} finally {
			ConsolePrinter.newline();
		}
	}

	public void removeExpense() {
		String[] lines = {
				"Remove expense"
		};
		ColorPrinter.print(ColorPrinter.ANSI_BLUE, PrettyFormatter.format(lines));

		try {
			String name = ConsoleScanner.readString(
					".+",
					"Expense name:",
					"Please enter a valid name."
					);

			if (account.getExpenses().remove(name) != null) {
				ColorPrinter.print(ColorPrinter.ANSI_YELLOW, "Expense removed!");
			} else {
				ColorPrinter.print(ColorPrinter.ANSI_RED, "Expense could not be found!");
			}
		} catch (Exception e) {
			ColorPrinter.print(ColorPrinter.ANSI_RED, e.getMessage());
		} finally {
			ConsolePrinter.newline();
		}
	}

	public void setMonthlyAndYearlyBudget() {
		String[] lines = {
				"Set monthly and yearly budget"
		};
		ColorPrinter.print(ColorPrinter.ANSI_BLUE, PrettyFormatter.format(lines));

		try {
			account.setMonthlyBudget(ConsoleScanner.readDouble(0, Double.MAX_VALUE, "Monthly Budget: "));
			account.setYearlyBudget(ConsoleScanner.readDouble(0, Double.MAX_VALUE, "Yearly Budget: "));

			ColorPrinter.print(ColorPrinter.ANSI_YELLOW, "Monthly and Yearly Budgets Updated!");
		} catch (Exception e) {
			ColorPrinter.print(ColorPrinter.ANSI_RED, e.getMessage());
		} finally {
			ConsolePrinter.newline();
		}
	}

	public void view5UpcomingExpenses() {
		String[] lines = { "View 5 Upcoming Expenses" };
		
		ColorPrinter.print(ColorPrinter.ANSI_BLUE, PrettyFormatter.format(lines));
		
		List<AccountExpense> expenses = account.getExpenses().values().stream()
			.limit(5).collect(Collectors.toList());
		expenses.forEach(expense -> ColorPrinter.print(ColorPrinter.ANSI_YELLOW, expense.toString()));
		
		if (expenses.isEmpty()) {
			ColorPrinter.print(ColorPrinter.ANSI_YELLOW, "No expenses found.");
		}
	}

	public void compareMonthlyBudget() {
		double budget = account.getMonthlyBudget();
		double expenses = account.getExpenses().values().stream()
				.filter(expense -> expense.getPeriod() == AccountExpense.Period.MONTHLY)
				.mapToDouble(expense -> expense.getAmount()).sum();
		double difference = budget - expenses;

		String[] lines = {
				"Compare Monthly Budget",
				"",
				"Monthly Budget: " + budget,
				"Montly Expenses: " + expenses,
				"Net: " + difference
		};
		ColorPrinter.print(ColorPrinter.ANSI_YELLOW, PrettyFormatter.format(lines));
	}

	public void compareYearlyBudget() {
		double budget = account.getMonthlyBudget();
		double expenses = account.getExpenses().values().stream()
				.mapToDouble(expense -> expense.getAmount()).sum();
		double difference = budget = expenses;
		String[] lines = {
				"Compare Monthly Budget",
				"",
				"Yearly Budget: " + budget,
				"Yearly Expenses:" + expenses,
				"Net: " + difference
		};
		ColorPrinter.print(ColorPrinter.ANSI_YELLOW, PrettyFormatter.format(lines));
	}

	public void displayCustomerInformation() {
		ColorPrinter.print(ColorPrinter.ANSI_YELLOW, account.getCustomer().toString());
	}

}