package com.dollarsbank.expensetracker.model;

import java.util.Date;

public class AccountExpense {
	private String name;
	private double amount;
	private Date date;

	public AccountExpense(String name, double amount, Date date) {
		this.name = name;
		this.amount = amount;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
