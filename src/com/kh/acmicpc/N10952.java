package com.kh.acmicpc;

import java.util.Scanner;

public class N10952 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int a = sc.nextInt();
			sc.nextLine();
			int b = sc.nextInt();
			sc.nextLine();
			
			if(a == 0 && b==0) {
				return;
			}
			System.out.println(a+b);
		}
	}

}
