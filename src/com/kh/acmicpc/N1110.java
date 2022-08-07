package com.kh.acmicpc;

import java.util.Scanner;

public class N1110 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int copy = n;
		int count = 0;
		
		
		while(true) { 
			//   10의 자리        	1의 자리
			n = (n%10*10) + ((n/10 + n%10) % 10);
			count++;
			
			if (copy == n) {
				break;
			}
		}
		
		System.out.println(count);
	}
}
