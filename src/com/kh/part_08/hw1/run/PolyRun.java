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
		
		pc.insert(new Student("ȫ�浿", 20, 178.2, 70, 1, "�����ý��۰��а�"), 0);
		pc.insert(new Student("�踻��", 21, 187.3, 80, 2, "�濵�а�"), 1);
		pc.insert(new Employee("������", 40, 182, 68, 10000000, "������"), 2);
		pc.insert(new Employee("�ں���", 28, 181.2, 70, 2000000, "��ȹ��"), 3);
		pc.insert(new Student("������", 23, 167, 45, 4, "������Ű��а�"), 4);
		
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
