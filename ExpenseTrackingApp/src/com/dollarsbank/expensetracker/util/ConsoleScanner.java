package com.dollarsbank.expensetracker.util;

import java.util.Scanner;

import com.dollarsbank.expensetracker.exception.UnexpectedInputException;

public class ConsoleScanner {
	private static Scanner sc = new Scanner(System.in);

	public static int readInt(String prompt) throws Exception {
		ColorPrinter.printSameLine(ColorPrinter.ANSI_GREEN, prompt);
		String input = sc.nextLine().trim();
		if (input.matches("\\d+")) {
			return Integer.parseInt(input);
		} else {
			throw new UnexpectedInputException("Expected a number.");
		}
	}

	public static int readInt(int min, int max, String prompt) throws Exception {
		ColorPrinter.printSameLine(ColorPrinter.ANSI_GREEN, prompt);
		String input = sc.nextLine().trim();
		if (input.matches("\\d+")) {
			int val = Integer.parseInt(input);
			if (val >= min && val <= max) return val;
		}
		throw new UnexpectedInputException("Expected a number between " + min + " and " + max + ".");
	}

	public static double readDouble(String prompt) throws Exception {
		ColorPrinter.printSameLine(ColorPrinter.ANSI_GREEN, prompt);
		String input = sc.nextLine().trim();
		if (input.matches("\\d+(\\.\\d+)?")) {
			return Double.parseDouble(input);
		} else {
			throw new UnexpectedInputException("Expected a number.");
		}
	}
	
	public static double readDouble(double min, double max, String prompt) throws Exception {
		ColorPrinter.printSameLine(ColorPrinter.ANSI_GREEN, prompt);
		String input = sc.nextLine().trim();
		if (input.matches("\\d+(\\.\\d+)?")) {
			double val = Double.parseDouble(input);
			if (val >= min && val <= max) return val;
		}
		throw new UnexpectedInputException("Expected a number between " + min + " and " + max + ".");
	}
	
	public static String readString(String prompt) {
		ColorPrinter.printSameLine(ColorPrinter.ANSI_GREEN, prompt);
		return sc.nextLine().trim();
	}
	
	public static String readString(String regex, String prompt, String error) throws Exception {
		ColorPrinter.printSameLine(ColorPrinter.ANSI_GREEN, prompt);

		String input = sc.nextLine().trim();
		if (input.matches(regex)) {
			return input;
		} else {
			throw new UnexpectedInputException(error);
		}
	}
	
	public static void close() {
		if (sc != null) {
			sc.close();
			sc = null;
		}
	}
}
