package com.kh.acmicpc;

import java.util.Scanner;

public class N5532 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int l = sc.nextInt();
		int a = sc.nextInt(); // 국어
		int b = sc.nextInt(); // 수학
		
		int c = sc.nextInt(); // 국어
		int d = sc.nextInt(); // 수학
		int rA = (a % c > 0) ? (a / c + 1) : (a / c);
		int rB = (b % d > 0) ? (b / d + 1) : (b / d);
		
		int result = (rA == rB) ? rA : (rA > rB ? rA : rB);
		
		System.out.println(l - result);
		
	}

}
