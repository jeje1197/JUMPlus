package com.sgb.controller;

import java.util.ArrayList;
import java.util.List;

import com.sgb.dao.TeacherDaoSql;
import com.sgb.exception.InvalidInputException;
import com.sgb.model.SchoolClass;
import com.sgb.model.Student;
import com.sgb.utils.ColorPrinter;
import com.sgb.utils.ConsolePrinter;
import com.sgb.utils.ConsoleScanner;
import com.sgb.utils.PrettyFormatter;

public class ClassController {
	private static TeacherDaoSql teacherDao;
	private static SchoolClass currentClass;
	
	public static void classMenu(TeacherDaoSql teacherDao, SchoolClass schoolClass) {
		ClassController.teacherDao = teacherDao;
		ClassController.currentClass = schoolClass;
		
		List<Student> students = teacherDao.getAllStudentsInClass(schoolClass.getId());
		List<String> menuOptions = new ArrayList<>();
		menuOptions.add("Viewing Class: + " + schoolClass.getName());
		menuOptions.add("-".repeat(menuOptions.get(0).length()));
		
		String averageGrade = "";
		String medianGrade = "";
		
		menuOptions.add("Avg: " + averageGrade + " Median: " + medianGrade);
		menuOptions.add("");
		
		if (students.isEmpty()) {
			menuOptions.add("None");
		} else {
			for (int i = 0; i < students.size(); i++) {
				menuOptions.add((i + 1) + ". " + students.get(i).getName() + " Grade: "
						+ students.get(i).getName() + "%");
			}
		}

		menuOptions.add("");
		menuOptions.add((students.size() + 1) + ". VIEW SORTED");
		menuOptions.add((students.size() + 2) + ". ADD NEW STUDENT");
		menuOptions.add((students.size() + 3) + ". RETURN");

		while (true) {
			ColorPrinter.println(ColorPrinter.CYAN, 
					PrettyFormatter.format(menuOptions.toArray(String[]::new)));

			int selectedOption = -1;
			try {
				ColorPrinter.print(ColorPrinter.GREEN, "Choose an option: ");
				selectedOption = ConsoleScanner.readInt(1, students.size() + 2);
				ConsolePrinter.println("");
			} catch (InvalidInputException e) {
				ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
				continue;
			}

			if (selectedOption == students.size() + 1) { // View Sorted
				ClassController.viewSorted();
			} else if (selectedOption == students.size() + 2) { // Add new student
				ClassController.addStudent();
			} else if (selectedOption == students.size() + 3) { // View Sorted
				break;
			} else { // Selected a student
				ClassController.viewStudentOptions(students.get(selectedOption - 1));
			}
		}

	}
	
	private static void viewSorted() {
		
	}
	
	private static void addStudent() {
		
	}
	
	private static void viewStudentOptions(Student student) {
		
	}
	
	private static void updateGrade() {
		
	}
	
	private static void removeStudent() {
		
	}
}
