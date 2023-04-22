package com.sgb.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.sgb.dao.TeacherDaoSql;
import com.sgb.model.SchoolClass;
import com.sgb.model.Teacher;

class TeacherDaoTest {
	static TeacherDaoSql dao;

	@BeforeAll
	static void initTeacherDaoSql() {
		dao = new TeacherDaoSql();
	}

	@Test
	void testGetAllTeachers() {
		dao.getAllTeachers().stream().forEach(System.out::println);
	}
	
	@Test
	void testAddTeacher() {
		assertTrue(dao.addTeacher("joseph admin", "joseph@gmail.com", "joseph"));
	}
	
	@Test
	void testGetTeacherByEmailAndPassword() {
		Teacher teacher = dao.getTeacherByEmailAndPassword("joseph@gmail.com", "joseph");
		System.out.println(teacher);
		assertTrue(teacher.getName().equals("joseph admin") 
				&& teacher.getEmail().equals("joseph@gmail.com"));
	}

	@Test
	void testGetAllClassesForTeacher() {
		Teacher teacher = dao.getTeacherByEmailAndPassword("joseph@gmail.com", "joseph");
		List<SchoolClass> classes = dao.getTeacherClasses(teacher);
		System.out.println("Classes for " + teacher.getName());
		classes.stream().forEach(System.out::println);
	}
	

}
