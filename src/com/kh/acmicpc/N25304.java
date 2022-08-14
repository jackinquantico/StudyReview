package com.kh.acmicpc;

import java.util.Scanner;

public class N25304 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int x = sc.nextInt();
		int n = sc.nextInt();
		int a;
		int b;
		int sum = 0;
		
		for (int i=0; i<n; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			sum += a*b;
		}
		
		if (sum == x) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

	}

}
