package com.kh.part_04;

import java.util.Scanner;

public class D_Practice {
public void practice12() {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("������(+, -, *, /, %) : ");
			String operator = sc.nextLine();
			
			if (operator.equals("exit")) {
				System.out.println("���α׷��� �����մϴ�.");
				return;
			} 
			
			System.out.print("����1 : ");
			int num1 = sc.nextInt();
			sc.nextLine();
			
			System.out.print("����2 : ");
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
					System.out.println("0���� ���� �� �����ϴ�. �ٽ� �Է����ּ���.");
					continue;
				}
				System.out.printf("%d %s %d = %d\n", num1, operator, num2, (num1/num2));
			} else if (operator.equals("%")) {
				if (num2 == 0) {
					System.out.println("0���� ���� �� �����ϴ�. �ٽ� �Է����ּ���.");
					continue;
				}
				System.out.printf("%d %s %d = %d\n", num1, operator, num2, (num1%num2));
			} else {
				System.out.println("���� �������Դϴ�. �ٽ� �Է����ּ���.");
				continue;
			}
			
		
		}
	}
}
