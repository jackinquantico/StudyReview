package com.kh.acmicpc;

import java.util.Scanner;

public class N10156 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int k = sc.nextInt();
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		if (k*n - m > 0)
			System.out.println(k*n - m);
		else 
			System.out.println(0);

	}

}
