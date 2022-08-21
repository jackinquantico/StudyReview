package com.kh.part_14.studentMVC.controller;

import java.util.ArrayList;

import com.kh.part_14.studentMVC.model.vo.Student;

public class StudentController {

	ArrayList<Student> list = new ArrayList<Student>();
	
	public int insertStudent(String name, int age, int kor, int eng, int math) {
		
		int result = list.size();
		
		list.add(new Student(name, age, kor, eng, math));
		
		return list.size() - result;
	}
	
	public ArrayList<Student> selectStudentList() {
		
		return list;
	}
	
	public int updateStudent(String name, String upName, int upAge, int upKor, int upEng, int upMath) {
		
		int result = 0;
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getName().equals(name)) {
				list.get(i).setName(upName);
				list.get(i).setAge(upAge);
				list.get(i).setKor(upKor);
				list.get(i).setEng(upEng);
				list.get(i).setMath(upMath);
				result++;
			}
		}
		
		return result;
	}
	
	public int deleteStudent(String name) {
		
		int result = 0;
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getName().equals(name)) {
				list.remove(i);
				result++;
			}
		}
		
		return result;
	}
	
	public ArrayList<Student> searchStudent(String keyword) {
		
		ArrayList<Student> searched = new ArrayList<Student>();
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getName().contains(keyword)) {
				searched.add(list.get(i));
			}
		}
		
		return searched;
	}
	
	public double averageKor() {
		
		int sum = 0;
		
		for (int i=0; i<list.size(); i++) {
			sum += list.get(i).getKor();
		}
		
		
		return (double)sum / list.size();
	}
	
	public double averageEng() {
		
		int sum = 0;
		
		for (int i=0; i<list.size(); i++) {
			sum += list.get(i).getEng();
		}
		
		return (double)sum / list.size();		
	}
	
	public double averageMath() {
		
		int sum = 0;
		
		for (int i=0; i<list.size(); i++) {
			sum += list.get(i).getMath();
		}
		
		return (double)sum / list.size();
	}

	public double averageAll() {
		
		int sum = 0;
		
		for (int i=0; i<list.size(); i++) {
			sum += list.get(i).getKor() + list.get(i).getEng() + list.get(i).getMath();
		}
		
		return (double)sum / ( 3 * list.size());
	}
	
}
