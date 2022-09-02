package com.kh.acmicpc;

import java.util.Scanner;

// ¸ø Ç°
public class N2839 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = n;
		
		int count = m / 5;
		m %= 5;
		
		if (m % 5 == 1 || (m % 5 == 4 && n != m)) {
			count--;
			n += 5;
		}
		
		count += n / 3;
		n %= 3;
		
		if (n > 0) {
			System.out.println(-1);
		} else {
			System.out.println(count);
		}
		

	}
	
	public void method1() {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		//		5		3
		// 1	1		1		
		// 2	2		2		
		// 3	3		0				1
		// 4	4		1		
		// 5	0		2		
		// 6			0				2		
		// 7	2		2		
		// 8	3		0		2						
		// 9			3				3
		// 10	2				2
		// 11	1		1		
		// 12			4				4
		// 13	3		0		3		
		// 14
		// 15
		// 16
		// 17
		// 18
		// 19
		// 20
		
		
		if (n % 5 > 3 && n % 5 == 0) {
			
			int five = n / 5;
			n %= 5;
			
			int three = n / 3;
			n %= 3;
			
			if (n == 0) {
				System.out.println(five + three);
			} else {
				System.out.println(-1);
			}
		} else {
			int three = n / 3;
			n %= 3;
			
			if (n == 0) {
				System.out.println(three);
			} else {
				System.out.println(-1);
			}
		}
	}

}
