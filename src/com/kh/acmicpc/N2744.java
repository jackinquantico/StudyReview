package com.kh.acmicpc;

import java.util.Scanner;

public class N2744 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		char[] arr = new char[str.length()];
		
		for (int i=0; i<str.length(); i++) {
			arr[i] = str.charAt(i);
			
			if ('a' <= arr[i] && arr[i] <= 'z') {
				arr[i] = str.toUpperCase().charAt(i);
			} else if('A' <= arr[i] && arr[i] <= 'Z') {
				arr[i] = str.toLowerCase().charAt(i);
			}
		}
		
		for (int i=0; i<arr.length; i++) {
			System.out.print(arr[i]);
		}

	}

}
