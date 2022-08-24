package com.kh.acmicpc;

import java.util.Scanner;

public class N10808 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String s = sc.nextLine();
		int[] abc = new int[26];
		
		for (int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			
			abc[ch - 'a']++;
		}
		
		for (int i=0; i<abc.length; i++) {
			System.out.print(abc[i] + " ");
		}
		
	}

}
