package com.kh.acmicpc;

import java.util.Scanner;

public class N10797 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int date = sc.nextInt();
		int[] car = new int[5];
		
		for (int i=0; i<5; i++) {
			car[i] = sc.nextInt(); 
		}
		
		int count = 0;
		
		for (int i=0; i<car.length; i++) {
			if (car[i] == date ) {
				count++;
			}
		}

		System.out.println(count);
	}

}
