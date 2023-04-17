package com.sgb.controller;

import com.sgb.exception.InvalidInputException;
import com.sgb.utils.ColorPrinter;
import com.sgb.utils.ConsolePrinter;
import com.sgb.utils.ConsoleScanner;
import com.sgb.utils.PrettyFormatter;

public class SGBController {

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
					ColorPrinter.print(ColorPrinter.GREEN, "Choose an option (1-4): ");
					selectedOption = ConsoleScanner.readInt(1, 4);
					ConsolePrinter.println("");
				} catch (InvalidInputException e) {
					ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
					continue;
				}

				switch (selectedOption) {
				case 1:
					break;

				case 2:
					break;

				case 3:
					break;

				case 4:
					applicationShouldRun = false;
				}
			}


			ConsoleScanner.close();
	}
}
