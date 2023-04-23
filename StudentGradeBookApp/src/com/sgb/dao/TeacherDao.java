package com.sgb.dao;

import java.util.List;

import com.sgb.model.SchoolClass;
import com.sgb.model.Student;
import com.sgb.model.Teacher;

public interface TeacherDao {

	/* Login */
	public List<Teacher> getAllTeachers();

	public boolean addTeacher(String name, String email, String password);

	public Teacher getTeacherByEmailAndPassword(String email, String password);

	/* Teacher Menu */
	public List<SchoolClass> getTeacherClasses(Teacher teacher);

	public boolean createClass(int teacherId, String className);


	/* Class Menu */

	public List<Student> getAllStudents();
	
	public List<Student> getAllStudentsInClass();

	
	
	public List<Integer> getAverageAndMedianForClass(int classId);
	
	public List<Student> getStudentsInClassSorted(int classId);
	
	public boolean addStudentToClass(int classId, int studentId);
	
	public boolean removeStudentFromClass(int classId, int studentId);
	
	public boolean updateStudentGradeInClass(int classId, int studentId, int grade);

}
