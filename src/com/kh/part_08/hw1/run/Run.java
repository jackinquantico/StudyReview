package com.kh.part_08.hw1.run;

import java.util.Scanner;

import com.kh.part_08.hw1.model.vo.Employee;
import com.kh.part_08.hw1.model.vo.Student;

public class Run {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		Student[] s = new Student[3];

		s[0] = new Student("ȫ�浿", 20, 178.2, 70, 1, "�����ý��۰��а�");
		s[1] = new Student("�踻��", 21, 187.3, 80, 2, "�濵�а�");
		s[2] = new Student("������", 23, 167, 45, 4, "������Ű��а�");

		for (int i=0; i<s.length; i++) {
			System.out.println(s[i].toString());
		}

		Employee[] e = new Employee[10];
		int count = 0;

		while (true) {
			System.out.println("\n��� ���� �Է�");
			System.out.print("�̸� : ");
			String name = sc.nextLine();

			System.out.print("���� : ");
			int age = sc.nextInt();
			sc.nextLine();

			System.out.print("���� : ");
			double height = sc.nextDouble();
			sc.nextLine();

			System.out.print("������ : ");
			double weight = sc.nextDouble();
			sc.nextLine();

			System.out.print("�޿� : ");
			int salary = sc.nextInt();
			sc.nextLine();

			System.out.print("�μ� : ");
			String dept = sc.nextLine();

			e[count] = new Employee(name, age, height, weight, salary, dept);

			count++;

			System.out.print("\n��� �Է��Ͻðڽ��ϱ�? : ");
			String answer = sc.nextLine();

			if (answer.equals("y") || answer.equals("Y")) {
				continue;
			} else if (answer.equals("n") || answer.equals("N")) {
				break;
			} 
		}

		System.out.println("\n��� ���� ���");
		for (int i=0; i<count; i++) {
			System.out.println(e[i].toString());
		}

	}

}
