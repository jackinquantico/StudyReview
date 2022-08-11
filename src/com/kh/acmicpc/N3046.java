package com.kh.acmicpc;

import java.util.Scanner;

public class N3046 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int r1 = sc.nextInt();
		sc.nextLine();
		int s = sc.nextInt();
		sc.nextLine();
		
		int r2 = s*2 - r1;
		
		System.out.println(r2);
	}

}
