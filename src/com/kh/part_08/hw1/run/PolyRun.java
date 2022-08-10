package com.kh.part_08.hw1.run;

import java.util.Scanner;

import com.kh.part_08.hw1.controller.PersonController;
import com.kh.part_08.hw1.model.vo.Employee;
import com.kh.part_08.hw1.model.vo.Person;
import com.kh.part_08.hw1.model.vo.Student;

public class PolyRun {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		PersonController pc = new PersonController();
		
		pc.insert(new Student("홍길동", 20, 178.2, 70, 1, "정보시스템공학과"), 0);
		pc.insert(new Student("김말똥", 21, 187.3, 80, 2, "경영학과"), 1);
		pc.insert(new Employee("강동원", 40, 182, 68, 10000000, "영업부"), 2);
		pc.insert(new Employee("박보검", 28, 181.2, 70, 2000000, "기획부"), 3);
		pc.insert(new Student("강개순", 23, 167, 45, 4, "정보통신공학과"), 4);
		
		Person[] p = pc.select();
		
		for (int i=0; i<p.length; i++) {
			if (p[i] instanceof Student) {
				System.out.println(p[i]);
			} else if (p[i] instanceof Employee) {
				System.out.println(p[i]);
			}
		}
		
		
	}

}
