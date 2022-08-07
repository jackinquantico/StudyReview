package com.kh.part_07.hw2.run;

import java.util.Scanner;

import com.kh.part_07.hw2.model.vo.Student;

public class Run {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// 크기가 10인 객체 배열 할당
		Student[] s = new Student[10];
		
		// 학생 수를 나타낼 변수
		int count = 0;
		
		while(true) {
			System.out.print("학년 : ");
			int grade = sc.nextInt();
			sc.nextLine();
			
			System.out.print("반 : ");
			int classroom = sc.nextInt();
			sc.nextLine();
			
			System.out.print("이름 : ");
			String name = sc.nextLine();
			
			System.out.print("국어점수 : ");
			int kor = sc.nextInt();
			sc.nextLine();
			
			System.out.print("영어점수 : ");
			int eng = sc.nextInt();
			sc.nextLine();
			
			System.out.print("수학점수 : ");
			int math = sc.nextInt();
			sc.nextLine();
			
			s[count] = new Student(grade, classroom, name, kor, eng, math);
			
			count++;
			
			System.out.print("계속 추가하시겠습니까?");
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
