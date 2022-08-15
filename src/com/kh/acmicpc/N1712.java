package com.kh.acmicpc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class N1712 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		if (b>=c) {
			System.out.println(-1);
		} else {
			System.out.println(a/(c-b)+1);
		}

	}

	// 시간 초과 발생	
	public void method1() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		
		StringTokenizer st = new StringTokenizer(input," ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		if (b>c) {
			System.out.println(-1);
		} else {
			int i = 1;
			while (true) {
				if ((a+b*i)-c*i < 0) {
					System.out.println(i);
					break;
				}
				i++;
			}
		}
		
		
	}

}
