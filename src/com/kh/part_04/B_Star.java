package com.kh.part_04;

import java.util.Scanner;

public class B_Star {
	
	public void method1() {
		
		// * 	: 세로줄 수만큼 가로로 * 찍기
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
		
		// ____* 1트 5, 2트 45, 3트 345, 4트 2345, 5트 12345
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
		
		// scanner로 입력받은 개수만큼 오른쪽 정렬 별 찍기
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요");
		int num = sc.nextInt();
		
		if (num <= 0)
			return;
		
		for (int i=0; i<num; i++) {
			for (int j=num; j>i+1; j--) { // num개수만큼 공백찍기, 맨 밑줄은 공백 제거
				System.out.print(" ");
			}
			for (int k=0; k<=i; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}

}
