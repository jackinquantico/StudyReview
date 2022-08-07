package com.kh.acmicpc;

import java.util.Scanner;

public class N11720 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n1 = sc.nextInt();
		sc.nextLine();
		
		int sum = 0;
		String n2 = sc.next();
		
		for (int i=0; i<n1; i++) {
			sum += n2.charAt(i) - '0';
			
		}
		System.out.println(sum);
	}

}
