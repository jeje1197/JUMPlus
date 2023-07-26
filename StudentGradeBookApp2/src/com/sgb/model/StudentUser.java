package com.sgb.model;

public class StudentUser {
	private int studentId;
	private String name;
	
	public StudentUser(int studentId, String name) {
		this.studentId = studentId;
		this.name = name;
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
}
