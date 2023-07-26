package com.sgb.dao;

import java.util.List;

import com.sgb.model.StudentClass;
import com.sgb.model.StudentUser;

public interface StudentDao {
	
	public StudentUser getStudentById(int id);
	
	public List<StudentClass> getStudentClasses(StudentUser student);
}
