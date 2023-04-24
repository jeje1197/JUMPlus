package com.sgb.model;

public class Student {
	private int studentId;
	private String name;
	private int grade;
	
	public Student(int studentId, String name, int grade) {
		this.studentId = studentId;
		this.name = name;
		this.grade = grade;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
