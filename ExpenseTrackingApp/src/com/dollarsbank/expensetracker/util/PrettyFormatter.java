package com.dollarsbank.expensetracker.util;

public class PrettyFormatter {
	
	public static String format(String[] lines) {
		String text = String.join("\n", lines);
		StringBuilder stringBuilder = new StringBuilder();
		int numDashes = 2 + text.length();
		String topAndBottom = "+" + "-".repeat(numDashes) + "+";

		stringBuilder.append(topAndBottom + "\n");
		stringBuilder.append("| " + text + " |\n");
		stringBuilder.append(topAndBottom);
		return stringBuilder.toString();
	}
}
