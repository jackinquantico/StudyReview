package com.kh.acmicpc;

import java.util.Scanner;

public class N10039 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] score = new int[5];
		int sum = 0;
		
		for (int i=0; i<score.length; i++) {
			score[i] = sc.nextInt(); 
			
			if (score[i]>= 40) {
				sum += score[i];
			} else {
				sum += 40;
			}
		}
		
		System.out.println(sum/5);
	}

}
