package com.sgb.utils;

import java.util.Scanner;

import com.sgb.exception.InvalidInputException;

public class ConsoleScanner {
	private static Scanner scanner = new Scanner(System.in);
	
	public static int readInt(int min, int max) throws InvalidInputException {
		String input = scanner.nextLine();
		int value = -1;
		if (!input.matches("^[0-9]+$")) {
			throw new InvalidInputException("Expected integer value.");
		}
		value = Integer.parseInt(input);
		
		if (value < min || value > max) {
			throw new InvalidInputException(String.format("Expected integer value between %d and %d",
					min, max));
		}
		return value;
	}

	public static double readDouble() {
		double value = scanner.nextDouble();
		scanner.nextLine();
		return value;
	}
	
	public static String readString(String regex, String error) throws InvalidInputException {
		String input = scanner.nextLine();
		if (!input.matches(regex)) {
			throw new InvalidInputException(error);
		}
		return input;
	}

	public static void close() {
		if (scanner != null) {
			scanner.close();
			scanner = null;
		}
	}
}
