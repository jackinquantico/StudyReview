package com.kh.part_08.hw1.run;

import com.kh.part_08.hw1.model.vo.Student;

public class Run {

	public static void main(String[] args) {
		
		Student[] s = new Student[3];
		
		s[0] = new Student("홍길동", 20, 178.2, 70, 1, "정보시스템공학과");
		s[1] = new Student("김말똥", 21, 187.3, 80, 2, "경영학과");
		s[2] = new Student("강개순", 23, 167, 45, 4, "정보통신공학과");
		
		for (int i=0; i<s.length; i++) {
			System.out.println(s[i].toString());
		}

	}

}
