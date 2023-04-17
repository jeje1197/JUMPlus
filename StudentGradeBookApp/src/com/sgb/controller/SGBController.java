package com.sgb.controller;

import java.util.ArrayList;
import java.util.List;

import com.sgb.exception.InvalidInputException;
import com.sgb.model.SchoolClass;
import com.sgb.model.Teacher;
import com.sgb.utils.ColorPrinter;
import com.sgb.utils.ConsolePrinter;
import com.sgb.utils.ConsoleScanner;
import com.sgb.utils.PrettyFormatter;

public class SGBController {
	private static List<SchoolClass> classes = new ArrayList<>();
	private static List<Teacher> teachers = new ArrayList<>();
	private static Teacher currentAccount = null;


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
					SGBController.teacherMenu();
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

	}

	private static void login() {

	}

	private static boolean isLoggedIn() {
		return currentAccount != null;
	}

	private static void teacherMenu() {

	}
}
