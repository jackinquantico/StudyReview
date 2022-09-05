package com.kh.acmicpc;

import java.util.Scanner;

public class N2443 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		for (int i=0; i<n; i++) {
			for (int k=0; k<i; k++) {
				System.out.print(" ");
			}
			
			for (int m=0; m<2*n-(2*i+1); m++) {
				
				System.out.print("*");
			}
			
			System.out.println();
		}

	}

}
