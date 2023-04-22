package com.sgb.dao;

import java.util.List;

import com.sgb.model.SchoolClass;
import com.sgb.model.Teacher;

public interface TeacherDao {

	/* Login */
	public List<Teacher> getAllTeachers();

	public boolean addTeacher(String name, String email, String password);

	public Teacher getTeacherByEmailAndPassword(String email, String password);

	/* Teacher Menu */
	public List<SchoolClass> getTeacherClasses(String teacherName);

	public boolean createClass(int teacherId, String className);

}
