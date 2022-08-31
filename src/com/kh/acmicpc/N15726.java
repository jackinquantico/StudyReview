package com.kh.acmicpc;

import java.util.Scanner;

public class N15726 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		double c = sc.nextDouble();
		
		System.out.println(Math.max((int)(a * b / c),(int)(a / b * c)));

	}

}
