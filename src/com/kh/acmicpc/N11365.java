package com.kh.acmicpc;

import java.util.Scanner;

public class N11365 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			String str = sc.nextLine();
			
			if (str.equals("END")) {
				break;
			}
					
			char[] ch = new char[str.length()];
			
			for (int i=0; i<ch.length; i++) {
				ch[i] = str.charAt(i);
			}
			
			for (int i=str.length()-1; i>=0; i--) {
				System.out.print(ch[i]);
			}
			
			System.out.println();
		}
	}

}
