package com.kh.part_07.run;

import com.kh.part_07.model.vo.Student;

public class StudentRun {
	
	public static void main(String[] args) {
		
		Student[] s = new Student[3];
		
		s[0] = new Student(3, 1, 1, "È«±æµ¿");
		s[1] = new Student(4, 3, 2, "À¯°ü¼ø");
		s[2] = new Student(2, 7, 5, "À±ºÀ±æ");
		
		for (int i=0; i<s.length; i++) {
			System.out.println(s[i].information());
		}
	}

}
