package com.kh.acmicpc;

import java.util.Scanner;

public class N2754 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String score = sc.nextLine();
		
		char ch = score.charAt(0);
		double result = 0;
		
		if (ch == 'A') {
			result = 4.0;
		} else if (ch =='B') {
			result = 3.0;
		} else if (ch == 'C') {
			result = 2.0;
		} else if (ch == 'D') {
			result = 1.0;
		} else if (ch == 'F') {
			System.out.println(0.0);
			return;
		}
		
		char opt = score.charAt(1);
		if (opt == '+') {
			result += 0.3;
		} else if (opt == '-') {
			result -= 0.3;
		}
		
		System.out.println(result);
	}

}
