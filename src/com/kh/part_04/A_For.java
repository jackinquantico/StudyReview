package com.kh.part_04;

import java.util.Scanner;

public class A_For {
	public void method17() {
		
		/*
		 * 사용자로부터 두 개의 정수를 입력받아 그 사이의 모든 수를 오름차순 순서대로 출력하세요
		 * 단 두 정수가 일치할 경우는 "정수 1과 정수 2의 값이 같습니다" 출력
		 * 
		 * 실행예시1
		 * 정수 1 입력 :3
		 * 정수 2 입력 :7
		 * 
		 * 3 4 5 6 7
		 * 
		 * 실행예시2
		 * 정수1 입력 : 7
		 * 정수2 입력 : 3
		 * 
		 * 3 4 5 6 7
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수1 입력 : ");
		int num1 = sc.nextInt();
		sc.nextLine();
		
		System.out.print("정수2 입력 : ");
		int num2 = sc.nextInt();
		sc.nextLine();
		
		if (num1 > num2) {
			for (int i=num2; i <= num1; i++) {
				System.out.print(i + " ");
			}
		} else if (num2 > num1) {
			for (int i=num1; i <= num2; i++) {
				System.out.print(i + " ");
			}
		} else {
			System.out.println("정수1과 정수2의 값이 같습니다.");
		}
	}	
}
