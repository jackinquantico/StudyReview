package com.kh.acmicpc;

import java.util.Scanner;

public class N4153 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			
			if (a==0 && b==0 && c==0) {
				break;
			}
			
			int max = Math.max(a, Math.max(b, c));
			
			if (max == a) {
				if (a*a == b*b+c*c) {
					System.out.println("right");
				} else {
					System.out.println("wrong");
				}				
			} else if (max == b) {
				if (b*b == a*a+c*c) {
					System.out.println("right");
				} else {
					System.out.println("wrong");
				}
			} else if (max == c) {
				if (c*c == a*a+b*b) {	
					System.out.println("right");
				} else {
					System.out.println("wrong");
				}
			}
			
			
		} //while
		

	}

}
