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

	public TeacherDaoSql() {}
	
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
			pstmt.addBatch();
			
			PreparedStatement pstmt2 = connection.prepareStatement(
					"insert into teacher_login (login_email, login_password) values (?, ?)");

			pstmt2.setString(1, email);
			pstmt2.setString(2, password);
			pstmt2.addBatch();
			
			

			int teacherRowsUpdated = pstmt.executeBatch()[0];
			int loginRowsUpdated = pstmt2.executeBatch()[0];
			if(teacherRowsUpdated > 0 && loginRowsUpdated > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.err.println(e);
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
				int studentId = rs.getInt("student_id");
				String studentName = rs.getString("student_name");
				int grade = rs.getInt("grade");

				students.add(new Student(studentId, studentName, grade));
			}

			return students;

		} catch (SQLException e) {
			System.err.println("Could not retrieve list of students from database");
		}

		return null;
	}

	@Override
	public List<Student> getAllStudentsInClass(int classId) {
		try {
			PreparedStatement ps = connection.prepareStatement("select * from student_classes "
					+ "join students on student_classes.student_id = students.student_id "
					+ "where class_id = ?");

			ps.setInt(1, classId);
			ResultSet rs = ps.executeQuery();

			List<Student> students = new ArrayList<>();

			while(rs.next()) {
				int studentId = rs.getInt("student_id");
				String studentName = rs.getString("student_name");
				int grade = rs.getInt("grade");

				students.add(new Student(studentId, studentName, grade));
			}

			return students;

		} catch (SQLException e) {
			System.err.println("Could not retrieve list of students from database");
		}

		return null;
	}
	
	@Override
	public List<Student> getAllStudentsNotInClass(int classId) {
		try {
			PreparedStatement ps = connection.prepareStatement("select * from students "
					+ "where student_id not in ("
					+ "select student_id from student_classes "
					+ "where class_id = ?)");

			ps.setInt(1, classId);
			ResultSet rs = ps.executeQuery();

			List<Student> students = new ArrayList<>();

			while(rs.next()) {
				int studentId = rs.getInt("student_id");
				String studentName = rs.getString("student_name");

				students.add(new Student(studentId, studentName, 0));
			}

			return students;

		} catch (SQLException e) {
			System.err.println("Could not retrieve list of students from database");
		}

		return null;
	}

	@Override
	public List<Integer> getAverageAndMedianForClass(int classId) {
		try {
			PreparedStatement ps = connection.prepareStatement("select grade from student_classes "
					+ "where class_id = ? order by grade asc");

			ps.setInt(1, classId);
			ResultSet rs = ps.executeQuery();
			List<Integer> grades = new ArrayList<>();

			while(rs.next()) {
				int grade = rs.getInt("grade");
				grades.add(grade);
			}
			
			if (grades.isEmpty()) {
				return grades;
			}
			
			int average = grades.stream().reduce(0, (a, b) -> a + b) / grades.size();
			int median = grades.size() % 2 == 1 ? 
					grades.get(grades.size() / 2) :
				(grades.get(grades.size()/2 - 1) + grades.get(grades.size()/2)) / 2;
			return List.of(average, median);
		} catch (SQLException e) {
			System.err.println("Could not retrieve average and median grade from database");
		}

		return null;
	}

	@Override
	public List<Student> getStudentsInClassSorted(int classId) {
		try {
			PreparedStatement ps = connection.prepareStatement("select * from student_classes "
					+ "join students on student_classes.student_id = students.student_id "
					+ "where class_id = ? order by student_name, grade");

			ps.setInt(1, classId);
			ResultSet rs = ps.executeQuery();

			List<Student> students = new ArrayList<>();

			while(rs.next()) {
				int studentId = rs.getInt("student_id");
				String studentName = rs.getString("student_name");
				int grade = rs.getInt("grade");

				students.add(new Student(studentId, studentName, grade));
			}

			return students;

		} catch (SQLException e) {
			System.err.println("Could not retrieve sorted list of students from database");
		}

		return null;
	}

	@Override
	public boolean addStudentToClass(int classId, int studentId) {
		try {
			PreparedStatement pstmt = connection.prepareStatement(
					"insert into student_classes (class_id, student_id, grade) values (?, ?, ?)");
			
			pstmt.setInt(1, classId);
			pstmt.setInt(2, studentId);
			pstmt.setInt(3, 0);

			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.err.println("Could not add student to class in database.");
		}

		return false;
	}

	@Override
	public boolean removeStudentFromClass(int classId, int studentId) {
		try {
			PreparedStatement pstmt = connection.prepareStatement(
					"delete from student_classes where class_id = ? and student_id = ?");
			
			pstmt.setInt(1, classId);
			pstmt.setInt(2, studentId);

			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.err.println("Could not remove student from class in database.");
		}

		return false;
	}

	@Override
	public boolean updateStudentGradeInClass(int classId, int studentId, int grade) {
		try {
			PreparedStatement pstmt = connection.prepareStatement(
					"update student_classes set grade = ? where class_id = ? and student_id = ?");
			
			pstmt.setInt(1, grade);
			pstmt.setInt(2, classId);
			pstmt.setInt(3, studentId);

			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.err.println("Could not update grade for student in database.");
		}

		return false;
	}

}
