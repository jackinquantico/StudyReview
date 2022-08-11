package com.kh.acmicpc;

import java.util.Scanner;

public class N1546 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] score = new int[n];
		
		for (int i=0; i<n; i++) {
			score[i] = sc.nextInt();
			sc.nextLine();
		}
		
		int max = score[0];
		
		for (int i=0; i<n; i++) {
			if (score[i] > max) {
				max = score[i];
			}
		}
		
		double[] result = new double[n];
		double sum = 0;
		
		for (int i=0; i<n; i++) {
			result[i] = score[i] / (double)max * 100;
			sum += result[i];
		}
		
		System.out.println(sum/n);

	}

}
