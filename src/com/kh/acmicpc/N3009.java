package com.kh.acmicpc;

import java.util.Arrays;
import java.util.Scanner;

public class N3009 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] x = new int[3];
		int[] y = new int[3];
		int d1 = 0;
		int d2 = 0;
		
		for (int i=0; i<3; i++) {
			x[i] = sc.nextInt();
			y[i] = sc.nextInt(); 
		}
		
		Arrays.sort(x);
		Arrays.sort(y);
		
		if (x[0] == x[1]) {
			d1 = x[2];
		} else {
			d1 = x[0];
		}
		
		if (y[0] == y[1]) {
			d2 = y[2];
		} else {
			d2 = y[0];
		}
		
		
		System.out.println(d1+" "+d2);

	}

}
