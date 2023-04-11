package com.movieratings.utils;

import java.util.Scanner;

public class ConsoleScanner {
	private static Scanner scanner = new Scanner(System.in);

	public static double readDouble() {
		double value = scanner.nextDouble();
		scanner.nextLine();
		return value;
	}

	public static String readString() {
		return scanner.nextLine();
	}

	public static void close() {
		if (scanner != null) {
			scanner.close();
			scanner = null;
		}
	}
}
