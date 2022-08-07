package com.kh.acmicpc;

import java.util.Scanner;

public class N1085 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int x = sc.nextInt();
		int y = sc.nextInt();
		int w = sc.nextInt();
		int h = sc.nextInt();
		
		int a = h-y;
		int b = w-x;
		int c = x;
		int d = y;
		
		int minX = Math.min(b, c);
		int minY = Math.min(a, d);
		
		System.out.println(Math.min(minX, minY));
		
//		// if문으로 풀기
//		if(a<b && a<c && a<d) {
//			System.out.println(a);
//		}
//		
//		if (b<a && b<c && b<d) {
//			System.out.println(b);
//		}
//		
//		if (c<a && c<b && c<d) {
//			System.out.println(c);
//		}
//		
//		if (d<a && d<b && d<c) {
//			System.out.println(d);
//		}
//		
//		if (a==b && a<c && a<d)
//			System.out.println(a);
//		
//		if (c==d && c<a && c<b) {
//			System.out.println(c);
//		}
	}

}
