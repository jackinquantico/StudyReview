package com.kh.acmicpc;

import java.util.Scanner;

public class N4999 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String j = sc.nextLine();
		String d = sc.nextLine();
		
		int jindex = j.indexOf("h");
		int dindex = d.indexOf("h");
		
		if (jindex >= dindex) {
			System.out.println("go");
		} else {
			System.out.println("no");
		}

	}

}
