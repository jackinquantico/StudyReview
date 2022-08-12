package com.kh.acmicpc;

import java.util.Scanner;

public class N2798 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		sc.nextLine();
		int m = sc.nextInt();
		sc.nextLine();
		
		int[] card = new int[n];
		
		for (int i=0; i<n; i++) {
			card[i] = sc.nextInt(); 
		}
		
		int sum = 0;
		
		for (int i=0; i<n; i++) {
			
			if (card[i] > m) {
				continue;
			}
			
			for (int j=i+1; j<n; j++) {
				
				if (card[i]+card[j] > m) {
					continue;
				}
				
				for (int k=j+1; k<n; k++) {
					if (card[i]+card[j]+card[k] <= m) {
						
						int tmp = card[i]+card[j]+card[k];
						
						if (tmp == m) {
							sum = tmp;
							break;
						}
						
						if (tmp > sum && tmp < m) {
							sum = tmp;
						}
					}
				}
			}
			
		}

		System.out.println(sum);
	}

	// 처음 생각한 방법 : 순서대로 저장되버림.
	public void method1() {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		sc.nextLine();
		int m = sc.nextInt();
		sc.nextLine();
		
		int[] card = new int[n];
		
		for (int i=0; i<n; i++) {
			card[i] = sc.nextInt(); 
		}
		
		int sum = 0;
		
		for (int i=0; i<n; i++) {
			if (sum + card[i] <= m) {
				sum += card[i];
			}
		}

		System.out.println(sum);
	}

}
