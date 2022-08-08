package com.kh.acmicpc;

import java.util.Scanner;

public class N2446 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		for (int i=0; i<(n-1); i++) {
			for (int j=0; j<i; j++) {
				System.out.print(" ");
			}
			for (int k=(2*n-1)-i*2; k>0; k--) {
				System.out.print("*");
			}
			System.out.println();
		}

		for (int i=0; i<n; i++) {
			for (int j=(n-1); j>i; j--) {
				System.out.print(" ");
			}
			for (int k=(2*i)+1; k>0; k--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
