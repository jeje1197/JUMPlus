package com.dollarsbank.expensetracker.util;

public class PrettyFormatter {
	
	public static String format(String[] lines) {
		StringBuilder stringBuilder = new StringBuilder();
		
		int maxLengthOfString = 0;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].length() > maxLengthOfString) {
				maxLengthOfString = lines[i].length();
			}
		}
		
		// Top design
		stringBuilder.append("+==")
			.append("=".repeat(maxLengthOfString))
			.append("==+\n");
		
		for (String line: lines) {
			stringBuilder.append("|  ")
				.append(line)
				.append(" ".repeat(maxLengthOfString - line.length() + 2))
				.append("|\n");
		}
		
		// Bottom design
		stringBuilder.append("+==")
			.append("=".repeat(maxLengthOfString))
			.append("==+");
		
		return stringBuilder.toString();
	}
}
