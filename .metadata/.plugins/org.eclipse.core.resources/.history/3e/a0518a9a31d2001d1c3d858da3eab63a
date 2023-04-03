package com.dollarsbank.utility;

public class PrettyFormatter {
	
	public static String format(String text) {
		StringBuilder stringBuilder = new StringBuilder();
		int numDashes = 2 + text.length();
		String topAndBottom = "+" + "-".repeat(numDashes) + "+";

		stringBuilder.append(topAndBottom + "\n");
		stringBuilder.append("| " + text + " |\n");
		stringBuilder.append(topAndBottom);
		return stringBuilder.toString();
	}
}
