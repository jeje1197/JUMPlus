package com.sgb.controller;

import java.util.List;

import com.sgb.dao.StudentDaoSql;
import com.sgb.model.StudentClass;
import com.sgb.model.StudentUser;

public class StudentController {
	
	public static void displayInformation(StudentDaoSql studentDao, StudentUser student) {
		List<StudentClass> classes = studentDao.getStudentClasses(student);
		
		System.out.println(classes);
	}
}
