package com.kh.acmicpc;

import java.util.Scanner;

public class N10809 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String input = sc.nextLine();
		int[] at = new int[26];
		
		for (int i=0; i<at.length; i++) {
			at[i] = -1; 
		}
		
		for (int i=0; i<input.length(); i++) {
			char ch = input.charAt(i);
			
			if (at[ch-'a'] == -1) {
				at[ch-'a'] = i;
			}
		}
		
		for (int i=0; i<at.length; i++) {
			System.out.print(at[i] +" ");
		}
	}
}
