package com.kh.acmicpc;

import java.util.Scanner;

public class N2908 {
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		String a1 = sc.nextInt() + "";
		String b1 = sc.nextInt() + "";
		
		char[] a2 = new char[3];
		char[] b2 = new char[3];
		
		for (int i=0; i<3; i++) {
			a2[i] = a1.charAt(i);
			b2[i] = b1.charAt(i); 
		}
		
		char tmp = a2[0];
		a2[0] = a2[2];
		a2[2] = tmp;
		
		tmp = b2[0];
		b2[0] = b2[2];
		b2[2] = tmp;
		
		String ar = String.valueOf(a2);
		int i = Integer.parseInt(ar);
		String br = String.valueOf(b2);
		int j = Integer.parseInt(br);
		
		if(i > j) {
			System.out.println(i);
		} else if (j > i) {
			System.out.println(j);
		}
		// parseInt 와 toString 사용하기
	}
}
