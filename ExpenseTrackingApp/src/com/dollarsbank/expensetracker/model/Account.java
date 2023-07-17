package com.dollarsbank.expensetracker.model;

import java.util.Map;
import java.util.TreeMap;

public class Account {
	private Customer customer;

	private Map<String, AccountExpense> expenses = new TreeMap<>();
	private Double monthlyBudget;
	private Double yearlyBudget;
	
	public Account(Customer customer) {
		this.customer = customer;
		this.monthlyBudget = 0.0;
		this.yearlyBudget = 0.0;
	}

	public Account(Customer customer, Double monthlyBudget, Double yearlyBudget) {
		this.customer = customer;
		this.monthlyBudget = monthlyBudget;
		this.yearlyBudget = yearlyBudget;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Map<String, AccountExpense> getExpenses() {
		return expenses;
	}
	
	public void setExpenses(Map<String, AccountExpense> expenses) {
		this.expenses = expenses;
	}

	public Double getMonthlyBudget() {
		return monthlyBudget;
	}

	public void setMonthlyBudget(Double monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
	}

	public Double getYearlyBudget() {
		return yearlyBudget;
	}

	public void setYearlyBudget(Double yearlyBudget) {
		this.yearlyBudget = yearlyBudget;
	}
	
	public void addExpense(AccountExpense expense) {
		this.expenses.put(expense.getName(), expense);
	}
	
	public void removeExpense(String name) {
		this.expenses.remove(name);
	}

}
