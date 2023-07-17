package com.dollarsbank.expensetracker.exception;

public class UnexpectedInputException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UnexpectedInputException(String message) {
		super(message);
	}
}
