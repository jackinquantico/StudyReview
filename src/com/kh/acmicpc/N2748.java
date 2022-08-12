package com.kh.acmicpc;

import java.util.Scanner;

// n 범위가 90까지 -> long 타입으로 받기
public class N2748 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		long[] arr = new long[n+1];
		arr[0] = 0;
		arr[1] = 1;
		
		for (int i=2; i<=n; i++) {
			arr[i] = arr[i-2] + arr[i-1]; 
		}
		
		System.out.println(arr[n]);

	}

}
