package com.kh.acmicpc;

import java.util.Scanner;

public class N1577 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		sc.nextLine();
		
		int b = sc.nextInt();
		sc.nextLine();
		
		int c = sc.nextInt();
		sc.nextLine();
		
		int num = a*b*c;
		int[] result = new int[10];
		
		for (int i=0; num>0; i++) {
			result[num % 10]++;
			num /= 10;
		}
		
		for (int i=0; i<result.length; i++) {
			System.out.println(result[i]);
		}
		
	}

}
