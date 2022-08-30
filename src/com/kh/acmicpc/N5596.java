package com.kh.acmicpc;

import java.util.Scanner;

public class N5596 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] s = new int[4];
		int sSum = 0;
		int[] t = new int[4];
		int tSum = 0;
		
		for (int i=0; i<4; i++) {
			s[i] = sc.nextInt();
			sSum += s[i];
		}
		
		for (int i=0; i<4; i++) {
			t[i] = sc.nextInt(); 
			tSum += t[i];
		}
		
		
		System.out.println(sSum == tSum ? sSum : (sSum > tSum ? sSum : tSum));

	}

}
