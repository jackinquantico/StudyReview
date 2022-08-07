package com.kh.acmicpc;

import java.util.Scanner;

public class N11721 {
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		char[] ch = new char[str.length()];
		
		for (int i=0; i<ch.length; i++) {
			ch[i] = str.charAt(i); 
		}
		
		for (int i=0; i<ch.length; i++) {
			System.out.print(ch[i]);
			if ((i != 0) && (i % 10 == 9)) {
				System.out.println();
			}
		}
		
		
	}
}
