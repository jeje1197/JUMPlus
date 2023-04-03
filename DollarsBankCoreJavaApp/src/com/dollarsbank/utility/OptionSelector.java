package com.dollarsbank.utility;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dollarsbank.exception.InvalidOptionException;

public class OptionSelector {
	private static Scanner scanner;
	static {
		scanner = new Scanner(System.in);
	}
	
	public static void close() {
		if (scanner != null) {
			scanner.close();
		}
	}
	
	public static int pickOption(int min, int max, String errorMessage) throws InvalidOptionException {
		int selectedOption = scanner.nextInt();
		scanner.nextLine();
		if (selectedOption < min || selectedOption > max) {
			throw new InvalidOptionException(errorMessage);
		}
		return selectedOption;
	}
	
	public static double pickOption(double min, double max, String errorMessage) throws InvalidOptionException {
		double selectedOption = scanner.nextDouble();
		scanner.nextLine();
		if (selectedOption < min || selectedOption > max) {
			throw new InvalidOptionException(errorMessage);
		}
		return selectedOption;
	}

	public static String pickOption(String regex, String errorMessage) throws InvalidOptionException {
		String selectedOption = scanner.nextLine().trim();
		Matcher matcher = Pattern.compile(regex)
								.matcher(selectedOption);
		if (!matcher.matches()) {
			throw new InvalidOptionException(errorMessage);
		}
		return selectedOption;
	}
}
