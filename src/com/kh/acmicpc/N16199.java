package com.kh.acmicpc;

import java.util.Scanner;

// ¸ø Ç°
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
		int se = ny-by+1;
		int yeon = ny-by;
		
		if (by == ny) {
			man = ny - by;
		} else {
			if (bm == nm) {
				if (bd <= nd) {
					man = ny - by;
				} else {
					man = ny - by - 1;
				}
			} else if (bm > nm) {
				man = ny - by - 1;
			} else {
				man = ny - by;
			}
		}
		
		System.out.println(man);
		System.out.println(se);
		System.out.println(yeon);
		
	}

}
