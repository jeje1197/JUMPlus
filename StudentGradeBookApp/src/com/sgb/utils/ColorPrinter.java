package com.sgb.utils;

public class ColorPrinter {
	// Reset
	private static final String RESET = "\033[0m"; // Text Reset 
	
	// Regular Colors
	public static final String BLACK = "\033[0;30m"; // BLACK
	public static final String RED = "\033[0;31m"; // RED
	public static final String GREEN = "\033[0;32m"; // GREEN
	public static final String YELLOW = "\033[0;33m"; // YELLOW
	public static final String BLUE = "\033[0;34m"; // BLUE
	public static final String PURPLE = "\033[0;35m"; // PURPLE
	public static final String CYAN = "\033[0;36m"; // CYAN
	public static final String WHITE = "\033[0;37m"; // WHITE


	public static void print(String color, String text) {
		System.out.print(color + text + RESET);
	}
	
	public static void println(String color, String text) {
		System.out.println(color + text + RESET);
	}
}
