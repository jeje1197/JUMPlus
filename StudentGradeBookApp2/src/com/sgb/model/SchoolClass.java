package com.sgb.model;

public class SchoolClass {
	int id;
	String name;
	String teacherName;

	public SchoolClass(int id, String name, String teacherName) {
		this.id = id;
		this.name = name;
		this.teacherName = teacherName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Override
	public String toString() {
		return "SchoolClass [id=" + id + ", name=" + name + ", teacherName=" + teacherName + "]";
	}
	
}
