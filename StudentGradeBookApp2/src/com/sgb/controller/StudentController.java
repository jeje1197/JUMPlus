package com.sgb.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import com.sgb.dao.StudentDaoSql;
import com.sgb.exception.InvalidInputException;
import com.sgb.model.StudentClass;
import com.sgb.model.StudentUser;
import com.sgb.utils.ColorPrinter;
import com.sgb.utils.ConsoleScanner;
import com.sgb.utils.PrettyFormatter;
import com.sgb.utils.RegexManager;

public class StudentController {
	
	public static void displayInformation(StudentDaoSql studentDao, StudentUser student) {
		List<StudentClass> classes = studentDao.getStudentClasses(student);
	
		List<String> display = new ArrayList<>();
		
		display.add("Student Portal");
		display.add("  Id: " + student.getStudentId() + "   Name: " + student.getName());
		display.add("");
		display.add("Your Classes: ");
		display.add("");
		
		classes.forEach(studentClass -> {
			display.add(studentClass.getClassName());
			display.add("  Grade: " + studentClass.getGrade() + 
					"%   Teacher: " + studentClass.getTeacherName());
		});
		
		display.add("");
		display.add("Press ENTER to return...");
		
		ColorPrinter.println(ColorPrinter.CYAN, 
				PrettyFormatter.format(display.toArray(String[]::new)));
		
		try {
			ConsoleScanner.readString(RegexManager.ANY, "");
		} catch (InvalidInputException e) {
			ColorPrinter.println(ColorPrinter.RED, e.getMessage() + "\n");
		}
	}
}
