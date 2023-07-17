package com.dollarsbank.expensetracker.exception;

public class InvalidCredentialsException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
		super("Invalid Credentials.");
	}
	
}
