package com.kh.part_04;

import java.util.Scanner;

public class D_Practice {
public void practice12() {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("연산자(+, -, *, /, %) : ");
			String operator = sc.nextLine();
			
			if (operator.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				return;
			} 
			
			System.out.print("정수1 : ");
			int num1 = sc.nextInt();
			sc.nextLine();
			
			System.out.print("정수2 : ");
			int num2 = sc.nextInt();
			sc.nextLine();
			
			if (operator.equals("+")) {
				System.out.printf("%d %s %d = %d\n", num1, operator, num2, (num1+num2));
			} else if (operator.equals("-")) {
				System.out.printf("%d %s %d = %d\n", num1, operator, num2, (num1-num2));
			} else if (operator.equals("*")) {
				System.out.printf("%d %s %d = %d\n", num1, operator, num2, (num1*num2));
			} else if (operator.equals("/")) {
				if (num2 == 0) {
					System.out.println("0으로 나눌 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				System.out.printf("%d %s %d = %d\n", num1, operator, num2, (num1/num2));
			} else if (operator.equals("%")) {
				if (num2 == 0) {
					System.out.println("0으로 나눌 수 없습니다. 다시 입력해주세요.");
					continue;
				}
				System.out.printf("%d %s %d = %d\n", num1, operator, num2, (num1%num2));
			} else {
				System.out.println("없는 연산자입니다. 다시 입력해주세요.");
				continue;
			}
			
		
		}
	}
}
