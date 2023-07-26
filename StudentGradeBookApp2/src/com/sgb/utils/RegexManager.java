package com.sgb.utils;

public class RegexManager {
	public static final String NAME = "\\w+(\\s\\w+)*";
	public static final String EMAIL = ".+@.+\\..+";
	public static final String PASSWORD = ".{6,24}";
	public static final String NUMBER = "\\d{1,6}";
	public static final String ANY = "(.|\\s)*";
}
