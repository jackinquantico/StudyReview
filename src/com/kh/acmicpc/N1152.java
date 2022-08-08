package com.kh.acmicpc;

import java.util.Scanner;

public class N1152 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		char[] ch = new char[str.length()];
		
		for (int i=0; i<ch.length; i++) {
			ch[i] = str.charAt(i); 
		}
		
		int count = 0;
		for (int i=0; i<ch.length; i++) {
			if (ch[i] == ' ') {
				count++;
			}
		}
		
		if (ch[0] == ' ' && ch[str.length()-1] == ' ') {
			count--;
		}
		
		if (ch[0] != ' ' && ch[str.length()-1] != ' ') {
			count++;
		}
		
		System.out.println(count);

	}

}
