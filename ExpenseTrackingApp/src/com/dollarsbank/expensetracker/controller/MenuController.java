package com.dollarsbank.expensetracker.controller;

import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.expensetracker.model.Account;

public class MenuController {
	private List<Account> account = new ArrayList<>();
	ExpenseController controller = new ExpenseController();
	
	public void displayOptions() {
		
	}
	
	public void createAccount() {
		
	}
	
	private void login() {
		
		controller.setAccount(null);
	}
	
	private void endSession() {
		System.exit(0);
	}
}
