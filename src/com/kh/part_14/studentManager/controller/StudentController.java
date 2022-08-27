package com.kh.part_14.studentManager.controller;

import java.util.ArrayList;

import com.kh.part_14.studentManager.model.vo.Student;

public class StudentController {

	private ArrayList<Student> list = new ArrayList<Student>();
	{
		list.add(new Student(1234, "홍길동", 11, "서울시", 3.4));
	}
	
	public ArrayList<Student> selectAll() {
	
		return list;
	}
	
	public Student selectOne(int classNumber) {
		
		Student tmp = null;
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getClassNumber() == classNumber) {
				tmp = list.get(i);
			}
		}
		
		return tmp;
	}
	
	public void insertStudent(Student tmp) {
		
		list.add(tmp);
	}
	
	public int updateStudent() {
		
		int result = 0;
		
		return result;
	}
	
	public int deleteStudent(int classNumber) {
		
		int result = 0;
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getClassNumber() == classNumber) {
				list.remove(i);
				result++;
			}
		}
		
		return result;
	}
	
}
