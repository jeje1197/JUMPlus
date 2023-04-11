package com.movieratings.utils;

public class PrettyFormatter {
	public static String format(String[] lines) {
		StringBuilder stringBuilder = new StringBuilder();
		
		int maxLengthOfString = 0;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].length() > maxLengthOfString) {
				maxLengthOfString = lines[i].length();
			}
		}
		
		stringBuilder.append()
	}
}
