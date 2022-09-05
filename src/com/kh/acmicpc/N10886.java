package com.kh.acmicpc;

import java.util.Scanner;

public class N10886 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		int zero = 0;
		
		for (int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
			if (arr[i] == 0) {
				zero++;
			}
		}
		
		if (n-zero < zero) {
			System.out.println("Junhee is not cute!");
		} else {
			System.out.println("Junhee is cute!");
		}

	}

}
