package com.dollarsbank.expensetracker.model;

import java.util.Date;

public class AccountExpense {
	public static enum Period {
		MONTHLY,
		YEARLY
	}

	private String name;
	private double amount;
	private Period period;

	public AccountExpense(String name, double amount, Period period) {
		this.name = name;
		this.amount = amount;
		this.period = period;
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

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

}
