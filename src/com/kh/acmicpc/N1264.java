package com.kh.acmicpc;

import java.util.Scanner;

public class N1264 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			String str = sc.nextLine();
			int count = 0;
			
			for (int i=0; i<str.length(); i++) {
				char ch = str.toLowerCase().charAt(i);
				
				if (ch == 'a') {
					count++;
				}
				if (ch == 'e') {
					count++;
				}
				if (ch == 'i') {
					count++;
				}
				if (ch == 'o') {
					count++;
				}
				if (ch == 'u') {
					count++;
				} 
				if (ch == '#') {
					return;
				}
			}
				
			System.out.println(count);
		}
	}

}
