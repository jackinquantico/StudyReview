package com.kh.part_04;

import java.util.Scanner;

public class B_Star {
	
	public void method1() {
		
		// * 	: ������ ����ŭ ���η� * ���
		// **
		// ***
		// ****
		// *****
		
		for (int i=0; i<5; i++) {
			for(int j=0; j<=i; j++) { 
				System.out.print("*");
			}
			
			System.out.println();
		}
		
		System.out.println();
		
		// ____* 1Ʈ 5, 2Ʈ 45, 3Ʈ 345, 4Ʈ 2345, 5Ʈ 12345
		// ___**
		// __***
		// _****
		// *****
		
		
		for (int i=1; i<=5; i++) {
			for (int j=5; j>=i; j--) {
				// i 1, j 54321
				// i 2, j 5432
				// i 3, j 543
				// i 4, j 54
				// i 5, j 5
				System.out.print(" ");
			}
			
			for (int k=1; k<=i; k++) {
				System.out.print("*");
			}
			
			System.out.println();
		}
		
		System.out.println();
		
		// scanner�� �Է¹��� ������ŭ ������ ���� �� ���
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("������ �Է��ϼ���");
		int num = sc.nextInt();
		
		if (num <= 0)
			return;
		
		for (int i=0; i<num; i++) {
			for (int j=num; j>i+1; j--) { // num������ŭ �������, �� ������ ���� ����
				System.out.print(" ");
			}
			for (int k=0; k<=i; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}

}
