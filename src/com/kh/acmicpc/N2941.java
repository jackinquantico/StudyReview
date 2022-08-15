package com.kh.acmicpc;

import java.util.Scanner;

public class N2941 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		int count = 0;
		
		for (int i=0; i<str.length(); i++) {
			
			char ch = str.charAt(i);
			
			if (ch == 'c') {
				if (str.charAt(i+1) == '=') {
					i++;
				} else if (str.charAt(i+1) == '-') {
					i++;
				}
			}
			
			if (ch == 'd') {
				if (str.charAt(i+1) == '-') {
					i++;
				} else if (str.charAt(i+2) == '=') {
					i += 2;
				}
			}
			
			if (ch == 'l') {
				if (str.charAt(i+1) == 'j') {
					i++;
				}
			}
			
			if (ch == 'n') {
				if (str.charAt(i+1) == 'j') {
					i++;
				}
			}
			
			if (ch == 's') {
				if (str.charAt(i+1) == '=') {
					i++;
				}
			}
			
			if (ch == 'z') {
				if (str.charAt(i+1) == '=') {
					i++;
				}
			}
			
			count++;
		}
		
		System.out.println(count);
	}

}
