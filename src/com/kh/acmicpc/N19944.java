package com.kh.acmicpc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class N19944 {

	public static void main(String[] args) throws IOException {
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		
		StringTokenizer st = new StringTokenizer(str, " ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		if (m > n) {
			bw.write("TLE!");
		} else {
			if (m == 1 || m == 2) {
				bw.write("NEWBIE!");
			} else {
				bw.write("OLDBIE!");
			}
		}
		
		bw.flush();
		bw.close();

	}
	
	public void scannerMethod() {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		if (m > n) {
			System.out.println("TLE!");
		} else {
			if (m == 1 || m == 2) {
				System.out.println("NEWBIE!");
			} else {
				System.out.println("OLDBIE!");
			}
		}
	}

}
