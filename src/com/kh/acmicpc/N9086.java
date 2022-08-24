package com.kh.acmicpc;

import java.util.Scanner;

public class N9086 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		sc.nextLine();
		
		for (int i=0; i<t; i++) {
			String str = sc.nextLine();
			String f = String.valueOf(str.charAt(0));
			String l = String.valueOf(str.charAt(str.length()-1));
			
			System.out.println(f + l);
		}

	}

}
