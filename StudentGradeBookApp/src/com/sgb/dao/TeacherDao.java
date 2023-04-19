package com.sgb.dao;

import java.util.List;

import com.sgb.model.Teacher;

public interface TeacherDao {
	
	public List<Teacher> getAllTeachers();
	
	public boolean addTeacher(String email, String password);
	
	public Teacher getTeacherById(int id);
	
	public Teacher getTeacherByEmailAndPassword(String email, String password);
		
}
