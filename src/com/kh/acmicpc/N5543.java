package com.kh.acmicpc;

import java.util.Scanner;

public class N5543 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] burger = new int[3];
		int[] juice = new int[2];
		
		for (int i=0; i<3; i++) {
			burger[i] = sc.nextInt(); 
		}
		
		for (int i=0; i<2; i++) {
			juice[i] = sc.nextInt();
		}
		
		int cBurger = Math.min(burger[0], Math.min(burger[1], burger[2]));
		int cJuice = Math.min(juice[0],juice[1]);
		
		System.out.println(cBurger+cJuice-50);

	}

}
