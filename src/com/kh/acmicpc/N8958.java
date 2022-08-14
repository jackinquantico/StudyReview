package com.kh.acmicpc;

import java.util.Scanner;

public class N8958 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		sc.nextLine();
		
		for (int j=0; j<n; j++) {
			String str = sc.nextLine();
			char[] arr = new char[str.length()];
			
			for (int i=0; i<arr.length; i++) {
				arr[i] = str.charAt(i); 
			}
			
			int sum = 0;
			int count = 0;
			
			for (int i=0; i<arr.length; i++) {
				if (arr[i] == 'O') {
					count++;
					sum += count;
				} else {
					count = 0;
				}
			}
			
			System.out.println(sum);
		}

	}

}
