package com.kh.part_08.hw1.run;

import java.util.Scanner;

import com.kh.part_08.hw1.model.vo.Employee;
import com.kh.part_08.hw1.model.vo.Student;

public class Run {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		Student[] s = new Student[3];

		s[0] = new Student("홍길동", 20, 178.2, 70, 1, "정보시스템공학과");
		s[1] = new Student("김말똥", 21, 187.3, 80, 2, "경영학과");
		s[2] = new Student("강개순", 23, 167, 45, 4, "정보통신공학과");

		for (int i=0; i<s.length; i++) {
			System.out.println(s[i].toString());
		}

		Employee[] e = new Employee[10];
		int count = 0;

		while (true) {
			System.out.println("\n사원 정보 입력");
			System.out.print("이름 : ");
			String name = sc.nextLine();

			System.out.print("나이 : ");
			int age = sc.nextInt();
			sc.nextLine();

			System.out.print("신장 : ");
			double height = sc.nextDouble();
			sc.nextLine();

			System.out.print("몸무게 : ");
			double weight = sc.nextDouble();
			sc.nextLine();

			System.out.print("급여 : ");
			int salary = sc.nextInt();
			sc.nextLine();

			System.out.print("부서 : ");
			String dept = sc.nextLine();

			e[count] = new Employee(name, age, height, weight, salary, dept);

			count++;

			System.out.print("\n계속 입력하시겠습니까? : ");
			String answer = sc.nextLine();

			if (answer.equals("y") || answer.equals("Y")) {
				continue;
			} else if (answer.equals("n") || answer.equals("N")) {
				break;
			} 
		}

		System.out.println("\n사원 정보 출력");
		for (int i=0; i<count; i++) {
			System.out.println(e[i].toString());
		}

	}

}
