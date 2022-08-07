package com.kh.part_07.hw2.run;

import java.util.Scanner;

import com.kh.part_07.hw2.model.vo.Student;

public class Run {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// ũ�Ⱑ 10�� ��ü �迭 �Ҵ�
		Student[] s = new Student[10];
		
		// �л� ���� ��Ÿ�� ����
		int count = 0;
		
		while(true) {
			System.out.print("�г� : ");
			int grade = sc.nextInt();
			sc.nextLine();
			
			System.out.print("�� : ");
			int classroom = sc.nextInt();
			sc.nextLine();
			
			System.out.print("�̸� : ");
			String name = sc.nextLine();
			
			System.out.print("�������� : ");
			int kor = sc.nextInt();
			sc.nextLine();
			
			System.out.print("�������� : ");
			int eng = sc.nextInt();
			sc.nextLine();
			
			System.out.print("�������� : ");
			int math = sc.nextInt();
			sc.nextLine();
			
			s[count] = new Student(grade, classroom, name, kor, eng, math);
			
			count++;
			
			System.out.print("��� �߰��Ͻðڽ��ϱ�?");
			String input = sc.nextLine();
			char answer = input.charAt(0);
			
			if(answer == 'n') {
				break;
			}
		}
		
		for (int i=0; i<count; i++) {
			System.out.println(s[i].information());
		}
	}

}
