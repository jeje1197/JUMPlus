package com.sgb.controller;

import java.util.ArrayList;
import java.util.List;

import com.sgb.dao.TeacherDaoSql;
import com.sgb.exception.InvalidInputException;
import com.sgb.model.Teacher;
import com.sgb.utils.ColorPrinter;
import com.sgb.utils.ConsolePrinter;
import com.sgb.utils.ConsoleScanner;
import com.sgb.utils.PrettyFormatter;
import com.sgb.utils.RegexManager;

public class SGBController {

	// DAOs
	private static TeacherDaoSql teacherDao = new TeacherDaoSql();

	// Application State
	private static Teacher currentTeacher = null;


	public static void mainMenu() {
		String[] menuOptions = {
				"1. REGISTER",
				"2. LOGIN",
				"3. EXIT"
		};

		boolean applicationShouldRun = true;
		while (applicationShouldRun) {
			ColorPrinter.println(ColorPrinter.CYAN, PrettyFormatter.format(menuOptions));

			int selectedOption = -1;
			try {
				ColorPrinter.print(ColorPrinter.GREEN, "Choose an option (1-3): ");
				selectedOption = ConsoleScanner.readInt(1, 3);
				ConsolePrinter.println("");
			} catch (InvalidInputException e) {
				ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
				continue;
			}

			switch (selectedOption) {
			case 1:
				SGBController.register();
				break;

			case 2:
				SGBController.login();
				if (SGBController.isLoggedIn()) {
					SGBController.showTeacherMenu();
				}
				break;

			case 3:
				applicationShouldRun = false;
				break;
			}
		}

		ConsoleScanner.close();
	}

	private static void register() {
		ColorPrinter.println(ColorPrinter.CYAN, PrettyFormatter.format(new String[] {"REGISTER"}));

		String name = null, 
			   email = null, 
			   password = null;
		try {
			
			ColorPrinter.print(ColorPrinter.GREEN, "Enter your name: ");
			name = ConsoleScanner.readString(RegexManager.NAME, "Invalid name format. Valid format ex: 'Randy Ralph'");

			ColorPrinter.print(ColorPrinter.GREEN, "Enter your email: ");
			email = ConsoleScanner.readString(RegexManager.EMAIL, "Invalid email format. Valid format ex:'email@yahoo.com'");

			ColorPrinter.print(ColorPrinter.GREEN, "Enter your password: ");
			password = ConsoleScanner.readString(RegexManager.PASSWORD, "Invalid password format. Valid format ex:'xxxx...'");

		} catch (InvalidInputException e) {
			ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
			return;
		}

		boolean success = teacherDao.addTeacher(name, email, password);
		if (success) {
			ColorPrinter.println(ColorPrinter.YELLOW, "New Account Created!\n");
		} else {
			ColorPrinter.println(ColorPrinter.RED, "Failed to create account!\n");
		}
	}

	private static void login() {
		ColorPrinter.println(ColorPrinter.CYAN, PrettyFormatter.format(new String[] {"LOGIN"}));

		String email = null, password = null;
		try {

			ColorPrinter.print(ColorPrinter.GREEN, "Enter your email: ");
			email = ConsoleScanner.readString(RegexManager.ANY, "");

			ColorPrinter.print(ColorPrinter.GREEN, "Enter your password: ");
			password = ConsoleScanner.readString(RegexManager.ANY, "");

		} catch (InvalidInputException e) {
			ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
			return;
		}

		Teacher teacher = teacherDao.getTeacherByEmailAndPassword(email, password);
		if (teacher != null) {
			currentTeacher = teacher;
			ColorPrinter.println(ColorPrinter.YELLOW, "Successfully logged in!\n");
		} else {
			ColorPrinter.println(ColorPrinter.RED, "Failed to log in!\n");
		}
	}

	private static boolean isLoggedIn() {
		return currentTeacher != null;
	}

	private static void showTeacherMenu() {
		TeacherController.teacherMenu();
		currentTeacher = null;
	}
}
