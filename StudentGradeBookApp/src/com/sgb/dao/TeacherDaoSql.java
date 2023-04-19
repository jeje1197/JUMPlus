package com.sgb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sgb.connection.BetterConnectionManager;
import com.sgb.model.Teacher;

public class TeacherDaoSql implements TeacherDao {
	private Connection connection = BetterConnectionManager.ConnManagerWithProperties.getConnection();

	@Override
	public List<Teacher> getAllTeachers() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM student_gradebook");

			List<Teacher> teachers = new ArrayList<Teacher>();

			while(rs.next()) {
				// iterate through to get column info
				int id = rs.getInt("teacher_id");
				String email = rs.getString("teacher_email");
				String password = rs.getString("teacher_password");

				Teacher dept = new Teacher(id, email, password);
				teachers.add(dept);
			}

			return teachers;

		} catch (SQLException e) {
			System.err.println("Could not retrieve list of teachers from database");
		}

		return null;
	}

	@Override
	public boolean addTeacher(String email, String password) {
		try {
			PreparedStatement pstmt = connection.prepareStatement(
					"INSERT into teachers(teacher_email, teacher_password) values(?, ?)");

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Teacher getTeacherById(int id) {
		return null;
	}

	@Override
	public Teacher getTeacherByEmailAndPassword(String email, String password) {
		return null;
	}



}
