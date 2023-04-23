package com.sgb.controller;

import java.util.ArrayList;
import java.util.List;

import com.sgb.dao.TeacherDaoSql;
import com.sgb.exception.InvalidInputException;
import com.sgb.model.SchoolClass;
import com.sgb.model.Teacher;
import com.sgb.utils.ColorPrinter;
import com.sgb.utils.ConsolePrinter;
import com.sgb.utils.ConsoleScanner;
import com.sgb.utils.PrettyFormatter;
import com.sgb.utils.RegexManager;

public class TeacherController {
	private static TeacherDaoSql teacherDao;
	private static Teacher currentTeacher;
	
	public static void teacherMenu(TeacherDaoSql teacherDao, Teacher currentTeacher) {
		TeacherController.teacherDao = teacherDao;
		TeacherController.currentTeacher = currentTeacher;
		
		List<SchoolClass> classes = teacherDao.getTeacherClasses(currentTeacher);
		List<String> menuOptions = new ArrayList<>();
		menuOptions.add("Your Classes:");
		
		if (classes.isEmpty()) {
			menuOptions.add("None");
		} else {
			for (int i = 0; i < classes.size(); i++) {
				menuOptions.add((i + 1) + ". " + classes.get(i).getName());
			}
		}

		menuOptions.add("");
		menuOptions.add((classes.size() + 1) + ". CREATE A NEW CLASS");
		menuOptions.add((classes.size() + 2) + ". EXIT");

		while (true) {
			ColorPrinter.println(ColorPrinter.CYAN, 
					PrettyFormatter.format(menuOptions.toArray(String[]::new)));

			int selectedOption = -1;
			try {
				ColorPrinter.print(ColorPrinter.GREEN, "Choose an option: ");
				selectedOption = ConsoleScanner.readInt(1, classes.size() + 2);
				ConsolePrinter.println("");
			} catch (InvalidInputException e) {
				ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
				continue;
			}

			if (selectedOption == classes.size() + 1) { // Create new class
				TeacherController.createNewClass();
			} else if (selectedOption == classes.size() + 2) { // Exit
				break;
			} else { // Selected a class
				TeacherController.viewClassOptions(classes.get(selectedOption - 1));
			}
		}
	}
	
	private static void createNewClass() {
		ColorPrinter.println(ColorPrinter.CYAN, PrettyFormatter.format(new String[] {"CREATE A NEW CLASS"}));

		String className = null;
		try {
			ColorPrinter.print(ColorPrinter.GREEN, "Enter a name for the class: ");
			className = ConsoleScanner.readString(RegexManager.ANY, "Invalid name format. Valid format ex: 'Intro to Calc.'");
		} catch (InvalidInputException e) {
			ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
			return;
		}
		
		boolean success = teacherDao.createClass(currentTeacher.getId(), className);
		if (success) {
			ColorPrinter.println(ColorPrinter.YELLOW, "New class created!\n");
		} else {
			ColorPrinter.println(ColorPrinter.RED, "Failed to create class!\n");
		}

	}
	
	private static void viewClassOptions(SchoolClass selectedClass) {
		ClassController.classMenu(selectedClass);
	}
}
