package com.kh.part_04;

import java.util.Scanner;

public class A_For {
	public void method17() {
		
		/*
		 * ����ڷκ��� �� ���� ������ �Է¹޾� �� ������ ��� ���� �������� ������� ����ϼ���
		 * �� �� ������ ��ġ�� ���� "���� 1�� ���� 2�� ���� �����ϴ�" ���
		 * 
		 * ���࿹��1
		 * ���� 1 �Է� :3
		 * ���� 2 �Է� :7
		 * 
		 * 3 4 5 6 7
		 * 
		 * ���࿹��2
		 * ����1 �Է� : 7
		 * ����2 �Է� : 3
		 * 
		 * 3 4 5 6 7
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("����1 �Է� : ");
		int num1 = sc.nextInt();
		sc.nextLine();
		
		System.out.print("����2 �Է� : ");
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
			System.out.println("����1�� ����2�� ���� �����ϴ�.");
		}
	}	
}
