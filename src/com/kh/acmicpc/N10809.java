package com.kh.acmicpc;

import java.util.Scanner;

import javax.security.auth.x500.X500Principal;

public class N10809 {
	
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		String input = sc.nextLine();
//		int[] at = new int[26];
//		
//		for (char i : input.toCharArray()) {
////			at[input.indexOf(input.charAt(i))] = input.charAt(i);
////			
////			for (int j='a'; j<='z'; j++) {
////				if (i == j) {
////					at[j-97] = input.indexOf(i);
////				}
////			}
//		}
//		
//		for (int i=0; i<at.length; i++) {
//			System.out.print(at[i] +" ");
//		}
//		
//		
//		for (int i=0; i<input.length(); i++) {
//			at[]
//		}
//			
//	}
	
	public void method1() {

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

	public void method2() {
		Scanner sc = new Scanner(System.in);
		
		String input = sc.nextLine();
		int[] at = new int[26];
		
		for (int i=0; i<input.length(); i++) {
			at[input.indexOf(input.charAt(i))] = input.charAt(i);
		}
		
		for (int i=0; i<at.length; i++) {
			System.out.print(at[i] +" ");
		}
	}
}

// indexOf 사용해보기


