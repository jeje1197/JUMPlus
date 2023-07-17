package com.dollarsbank.expensetracker.util;

public class ColorPrinter {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public static void print(String color, String text){
		if (color != ANSI_BLUE && color != ANSI_GREEN && color != ANSI_YELLOW && color != ANSI_RED) {
			return;
		}
		System.out.println(color + text + ANSI_RESET);
	}
	
	public static void printSameLine(String color, String text){
		if (color != ANSI_BLUE && color != ANSI_GREEN && color != ANSI_RED) {
			return;
		}
		System.out.print(color + text + ANSI_RESET);
	}
	
	public static void setConsoleInputColor(String color) {
		System.out.print(color);
	}

}
