package com.kh.acmicpc;

import java.util.Scanner;

public class N17388 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int s = sc.nextInt();
		int k = sc.nextInt();
		int h = sc.nextInt();

		if (s + k + h >= 100) {
			System.out.println("OK");
		} else {
			int n = Math.min(s, Math.min(k, h));
			System.out.println(n == s ? "Soongsil" : (n == k ? "Korea" : "Hanyang"));
		}
		
	}

}
