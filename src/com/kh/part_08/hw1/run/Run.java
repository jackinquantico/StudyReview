package com.kh.part_08.hw1.run;

import com.kh.part_08.hw1.model.vo.Student;

public class Run {

	public static void main(String[] args) {
		
		Student[] s = new Student[3];
		
		s[0] = new Student("ȫ�浿", 20, 178.2, 70, 1, "�����ý��۰��а�");
		s[1] = new Student("�踻��", 21, 187.3, 80, 2, "�濵�а�");
		s[2] = new Student("������", 23, 167, 45, 4, "������Ű��а�");
		
		for (int i=0; i<s.length; i++) {
			System.out.println(s[i].toString());
		}

	}

}
