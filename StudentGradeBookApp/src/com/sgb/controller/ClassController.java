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
import com.sgb.utils.RegexManager;

public class ClassController {
	private static TeacherDaoSql teacherDao;
	private static SchoolClass currentClass;
	
	public static void classMenu(TeacherDaoSql teacherDao, SchoolClass schoolClass) {
		ClassController.teacherDao = teacherDao;
		ClassController.currentClass = schoolClass;
		
		List<Student> students = teacherDao.getAllStudentsInClass(schoolClass.getId());
		List<String> menuOptions = new ArrayList<>();
		menuOptions.add("Viewing Class: " + schoolClass.getName());
		menuOptions.add("-".repeat(menuOptions.get(0).length()));
		
		String averageGrade = "";
		String medianGrade = "";
		
		menuOptions.add("Avg: " + averageGrade + "%      Median: " + medianGrade + "%");
		menuOptions.add("");
		
		menuOptions.add("Students");
		menuOptions.add("-".repeat(menuOptions.get(0).length()));
		if (students.isEmpty()) {
			menuOptions.add("None");
		} else {
			for (int i = 0; i < students.size(); i++) {
				menuOptions.add((i + 1) + ". " + students.get(i).getName() + " Grade: "
						+ students.get(i).getGrade() + "%");
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
				selectedOption = ConsoleScanner.readInt(1, students.size() + 3);
				ConsolePrinter.println("");
			} catch (InvalidInputException e) {
				ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
				continue;
			}

			if (selectedOption == students.size() + 1) { // View Sorted
				ConsolePrinter.println("");
				ClassController.viewSorted();
			} else if (selectedOption == students.size() + 2) { // Add new student
				ClassController.addStudent();
			} else if (selectedOption == students.size() + 3) { // Return
				break;
			} else { // Selected a student
				ClassController.viewStudentOptions(students.get(selectedOption - 1));
			}
		}

	}
	
	private static void viewSorted() {
		List<Student> students = teacherDao.getStudentsInClassSorted(currentClass.getId());
		List<String> menuOptions = new ArrayList<>();
		menuOptions.add("Students (Sorted by name & grade)");
		menuOptions.add("-".repeat(menuOptions.get(0).length()));
		if (students.isEmpty()) {
			menuOptions.add("None");
		} else {
			for (int i = 0; i < students.size(); i++) {
				menuOptions.add((i + 1) + ". " + students.get(i).getName() + " Grade: "
						+ students.get(i).getGrade() + "%");
			}
		}
		
		ColorPrinter.println(ColorPrinter.YELLOW, 
				PrettyFormatter.format(menuOptions.toArray(String[]::new)));
		ConsolePrinter.println("");
	}
	
	private static void addStudent() {
		ColorPrinter.println(ColorPrinter.CYAN, PrettyFormatter.format(new String[] {"ADD STUDENT"}));
		
		List<Student> unenrolledStudents = teacherDao.getAllStudentsNotInClass(currentClass.getId());
		List<String> menuOptions = new ArrayList<>();
		
		menuOptions.add("Students Not Enrolled");
		menuOptions.add("-".repeat(menuOptions.get(0).length()));
		if (unenrolledStudents.isEmpty()) {
			menuOptions.add("None");
		} else {
			for (int i = 0; i < unenrolledStudents.size(); i++) {
				menuOptions.add((i + 1) + ". " + unenrolledStudents.get(i).getName());
			}
		}

		ColorPrinter.println(ColorPrinter.CYAN, 
				PrettyFormatter.format(menuOptions.toArray(String[]::new)));

		int studentId = -1;
		try {
			ColorPrinter.print(ColorPrinter.GREEN, "Select the student you want to add: ");
			studentId = ConsoleScanner.readInt(1, unenrolledStudents.size());

		} catch (InvalidInputException e) {
			ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
			return;
		}

		boolean success = teacherDao.addStudentToClass(currentClass.getId(), studentId);
		if (success) {
			ColorPrinter.println(ColorPrinter.YELLOW, "Student added to class!\n");
		} else {
			ColorPrinter.println(ColorPrinter.RED, "Failed to add student to class!\n");
		}
	}

	private static void viewStudentOptions(Student student) {
		
	}

	private static void updateGrade() {
		
	}
	
	private static void removeStudent() {
		
	}
}
