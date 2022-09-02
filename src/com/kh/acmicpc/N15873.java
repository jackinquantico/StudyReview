package com.kh.acmicpc;

import java.util.Scanner;

public class N15873 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		sc.nextLine();
		
		if (num == 1010) {
			System.out.println(20);
		} else if (num % 10 == 0) {
			System.out.println(num/100 + 10);
		} else {
			System.out.println(num/10 + num%10);
		}

	}

}
