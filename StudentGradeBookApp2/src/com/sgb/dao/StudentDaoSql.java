package com.sgb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sgb.connection.BetterConnectionManager;
import com.sgb.model.StudentClass;
import com.sgb.model.StudentUser;

public class StudentDaoSql implements StudentDao {
	private Connection connection = BetterConnectionManager.ConnManagerWithProperties.getConnection();

	@Override
	public StudentUser getStudentById(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("select * from students "
					+ "where student_id = ?");
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				String name = rs.getString("student_name");
				return new StudentUser(id, name);
			}

		} catch (SQLException e) {
			System.err.println("Could not retrieve teacher from database");
		}

		return null;
	}

	@Override
	public List<StudentClass> getStudentClasses(StudentUser student) {
		try {
			PreparedStatement ps = connection.prepareStatement("select * from student_classes sc "
					+ "join classes c on c.class_id = sc.class_id "
					+ "join teachers t on t.teacher_id = c.class_id "
					+ "where sc.student_id = ?");

			ps.setInt(1, student.getStudentId());
			ResultSet rs = ps.executeQuery();
			
			List<StudentClass> classes = new ArrayList<>();
			while(rs.next()) {
				// iterate through to get column info
				int classId = rs.getInt("class_id");
				String className = rs.getString("class_name");
				String teacherName = rs.getString("teacher_name");
				int studentGrade = rs.getInt("grade");

				classes.add(new StudentClass(classId, className, teacherName, studentGrade));
			}

			return classes;
		} catch (SQLException e) {
			System.err.println(e);
			System.err.println("Could not retrieve list of teachers from database");
		}
		
		return null;
	}
}
