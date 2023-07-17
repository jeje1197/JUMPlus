package com.dollarsbank.expensetracker.controller;

import com.dollarsbank.expensetracker.model.Account;
import com.dollarsbank.expensetracker.util.CustomerPrinter;

public class ExpenseController {
	private Account account;
	
	public ExpenseController(Account account) {
		this.account = account;
	}

	public void addExpense() {
		
	}

	public void removeExpense() {

	}

	public void setMonthlyAndYearlyBudget() {
		
	}

	public void view5UpcomingExpenses() {
		
	}

	public void compareMonthlyBudget() {
		
	}

	public void compareYearlyBudget() {
		
	}

	public void displayCustomerInformation() {
		CustomerPrinter.print(account.getCustomer());
	}



	



}
