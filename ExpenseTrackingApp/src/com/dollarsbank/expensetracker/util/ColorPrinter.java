package com.dollarsbank.expensetracker.util;

public class ColorPrinter {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";
	
	public static void print(String color, String text){
		if (color != ANSI_BLUE && color != ANSI_GREEN && color != ANSI_RED) {
			return;
		}
		System.out.println(color + text + ANSI_RESET);
	}
	
	public static void setConsoleInputColor(String color) {
		System.out.print(color);
	}

}
