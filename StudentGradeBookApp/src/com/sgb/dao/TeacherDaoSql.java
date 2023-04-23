package com.sgb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sgb.connection.BetterConnectionManager;
import com.sgb.model.SchoolClass;
import com.sgb.model.Student;
import com.sgb.model.Teacher;

public class TeacherDaoSql implements TeacherDao {
	private Connection connection = BetterConnectionManager.ConnManagerWithProperties.getConnection();

	public TeacherDaoSql() {
		
	}
	
	@Override
	public List<Teacher> getAllTeachers() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from teacher_login "
					+ "join teachers on teacher_login.login_id = teachers.teacher_id;");

			List<Teacher> teachers = new ArrayList<Teacher>();

			while(rs.next()) {
				// iterate through to get column info
				int id = rs.getInt("teacher_id");
				String name = rs.getString("teacher_name");
				String email = rs.getString("login_email");

				Teacher dept = new Teacher(id, name, email);
				teachers.add(dept);
			}

			return teachers;

		} catch (SQLException e) {
			System.err.println("Could not retrieve list of teachers from database");
		}

		return null;
	}

	@Override
	public boolean addTeacher(String name, String email, String password) {
		try {
			PreparedStatement pstmt = connection.prepareStatement(
					"insert into teachers (teacher_name) values (?)");
			
			pstmt.setString(1, name);
			
			PreparedStatement pstmt2 = connection.prepareStatement(
					"insert into teacher_login (login_email, login_password) values (?, ?)");

			pstmt2.setString(1, email);
			pstmt2.setString(2, password);
			

			int teacherRowsUpdated = pstmt.executeUpdate();
			int loginRowsUpdated = pstmt2.executeUpdate();
			if(teacherRowsUpdated > 0 && loginRowsUpdated > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.err.println("Could not add teacher to database.");
		}

		return false;
	}

	@Override
	public Teacher getTeacherByEmailAndPassword(String email, String password) {
		try {
			PreparedStatement ps = connection.prepareStatement("select * from teacher_login "
					+ "join teachers on teacher_login.login_id = teachers.teacher_id "
					+ "where teacher_login.login_email = ? and teacher_login.login_password = ?");

			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				int id = rs.getInt("teacher_id");
				String name = rs.getString("teacher_name");
				return new Teacher(id, name, email);
			}

		} catch (SQLException e) {
			System.err.println("Could not retrieve teacher from database");
		}

		return null;
	}

	@Override
	public List<SchoolClass> getTeacherClasses(Teacher teacher) {
		try {
			PreparedStatement ps = connection.prepareStatement("select * from classes where teacher_id = ?");

			ps.setInt(1, teacher.getId());
			ResultSet rs = ps.executeQuery();

			List<SchoolClass> classes = new ArrayList<>();

			while(rs.next()) {
				// iterate through to get column info
				int classId = rs.getInt("class_id");
				String className = rs.getString("class_name");

				classes.add(new SchoolClass(classId, className, teacher.getName()));
			}

			return classes;

		} catch (SQLException e) {
			System.err.println("Could not retrieve list of teachers from database");
		}

		return null;
	}

	@Override
	public boolean createClass(int teacherId, String className) {
		try {
			PreparedStatement pstmt = connection.prepareStatement(
					"insert into classes (class_name, teacher_id) values (?, ?)");
			
			pstmt.setString(1, className);
			pstmt.setInt(2, teacherId);
			

			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.err.println("Could not add class to database.");
		}

		return false;
	}

	@Override
	public List<Student> getAllStudents() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from students");

			List<Student> students = new ArrayList<>();

			while(rs.next()) {
				int id = rs.getInt("student_id");
				String name = rs.getString("student_name");

				Student student = new Student(id, name);
				students.add(student);
			}

			return students;

		} catch (SQLException e) {
			System.err.println("Could not retrieve list of students from database");
		}

		return null;
	}

	@Override
	public List<Student> getAllStudentsInClass() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from students ...");

			List<Student> students = new ArrayList<>();

			while(rs.next()) {
				int id = rs.getInt("student_id");
				String name = rs.getString("student_name");

				Student student = new Student(id, name);
				students.add(student);
			}

			return students;

		} catch (SQLException e) {
			System.err.println("Could not retrieve list of students from database");
		}

		return null;
	}

}
