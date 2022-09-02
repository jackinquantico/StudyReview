package com.kh.acmicpc;

import java.util.Scanner;

// 못 품
public class N16199 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int by = sc.nextInt();
		int bm = sc.nextInt();
		int bd = sc.nextInt();
		
		int ny = sc.nextInt();
		int nm = sc.nextInt();
		int nd = sc.nextInt();
		
		int man = 0;
		int se = 0;
		int yeon = 0;
		
		if (bm == nm) { 
			if (bd == nd) { // 생일인 경우
				man = ny-by;
				se = ny-by+1;
				yeon = ny-by;
			} else if (bd > nm) { // 안 지난 경우
				man = ny-by-1;
				se = ny-by+1;
				yeon = ny-by;
			} else { // 지난 경우
				man = ny-by; // 1
				se = ny-by+1; // 2
				yeon = ny-by; // 1
			}
			
		} else if (bm > nm) { // 안 지난 경우
			man = ny-by-1; // 0
			se = ny-by+1; // 2
			yeon = ny-by; // 1
		} else if (bm < nm) { // 지난 경우
			man = ny-by; // 1
			se = ny-by+1; // 2
			yeon = ny-by; // 1
		} 
		
		System.out.println(man);
		System.out.println(se);
		System.out.println(yeon);
		
	}

}
