package com.kh.acmicpc;

import java.util.Scanner;

public class N14489 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		long a = sc.nextLong();
		long b = sc.nextLong();
		long c = sc.nextLong();
		
		if (a + b >= c*2) {
			System.out.println(a+b - c*2);
		} else {
			System.out.println(a+b);
		}

	}

}
