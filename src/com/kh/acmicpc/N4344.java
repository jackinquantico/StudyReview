package com.kh.acmicpc;

import java.util.Scanner;

public class N4344 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		int c = sc.nextInt();
		sc.nextLine();
		
		for (int j=0; j<c; j++) {
			int n = sc.nextInt();
			int[] score = new int[n];
			
			double sum = 0;
			
			for (int i=0; i<n; i++) {
				int val = sc.nextInt();
				score[i] = val;
				sum += score[i];
			}
			
			double average = sum / n;
			int count = 0;
			
			
			for(int i=0; i<n; i++) {
				if (score[i] > average) {
					count++;
				}
			}
			
			double result = (count/(double)n*1000+0.0005) / 10.0;
						
			System.out.printf("%.3f%%\n", result);
		
		}
	}

}
