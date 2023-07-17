package com.dollarsbank.expensetracker.util;

public class ConsolePrinter {
	public static final String ANSI_RESET = "\u001B[0m";

	public static void print(String text) {
		System.out.println(ANSI_RESET + text);
	}
	
	public static void newline() {
		System.out.println("");
	}
}
