package com.sgb.model;

public class StudentClass {
	private int classId;
	private String className;
	private String teacherName;
	private int grade;
	public StudentClass(int classId, String className, String teacherName, int grade) {
		this.classId = classId;
		this.className = className;
		this.teacherName = teacherName;
		this.grade = grade;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "StudentClass [classId=" + classId + ", className=" + className + ", teacherName=" + teacherName
				+ ", grade=" + grade + "]";
	}

}
