package com.kh.acmicpc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class N10807 {
	
	// BufferedReader 사용 : 148ms
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int v = Integer.parseInt(br.readLine());
		int count = 0;
		
		for (int i=0; i<n; i++) {
			if (arr[i] == v) {
				count++;
			}
		}
		
		bw.write(count);
		bw.flush();
		br.close();
		bw.close();
		
		
	}
	
	// Scanner 사용 : 220ms
	public void method1() {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for (int i=0; i<n; i++) {
			arr[i] = sc.nextInt(); 
		}
		
		int v = sc.nextInt();
		int count = 0;
		
		for (int i=0; i<n; i++) {
			if (arr[i] == v) {
				count++;
			}
		}
		
		System.out.println(count);

	}

}
