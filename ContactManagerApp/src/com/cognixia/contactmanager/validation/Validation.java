package com.cognixia.contactmanager.validation;

public class Validation {
	private static String EMAIL_REGEX = ".+@.+";
	private static String PHONE_REGEX = "\\d{10}";
	
	public static boolean validateEmail(String email) {
		return email.matches(EMAIL_REGEX);
	}
	
	public static boolean validatePhone(String phone) {
		return phone.matches(PHONE_REGEX);
	}
}
