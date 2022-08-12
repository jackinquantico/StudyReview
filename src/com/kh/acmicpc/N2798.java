package com.kh.acmicpc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class N2798 {

	// 세번째 : Scanner 대신 Buffer 사용
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] card = new int[n];
		
		st = new StringTokenizer(br.readLine()," ");
		for (int i=0; i<n; i++) {
			card[i] = Integer.parseInt(st.nextToken());
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

	// 두번째 방법 : for문 3중으로 사용 + 조건문으로 m보다 큰 값 들어오면 바로 넘기기
	public void method2() {
		
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
}
