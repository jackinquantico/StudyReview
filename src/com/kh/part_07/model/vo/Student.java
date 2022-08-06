package com.kh.part_07.model.vo;

public class Student {
	private int grade;
	private int classroom;
	private int num;
	private String name;
	
	public Student() {
		
	}
	
	public Student(int grade, int classroom, int num, String name) {
		this.grade = grade;
		this.classroom = classroom;
		this.num = num;
		this.name = name;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public void setClassroom(int classroom) {
		this.classroom = classroom;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public int getClassroom() {
		return classroom;
	}
	
	public int getNum() {
		return num;
	}
	
	public String getName() {
		return name;
	}
	
	public String information() {
		return grade+"학년 "+classroom+"반 "+num+"번 "+name;
	}
	
}
