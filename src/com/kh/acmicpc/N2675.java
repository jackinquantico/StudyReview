package com.kh.acmicpc;

import java.util.Scanner;

public class N2675 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int r = 0;
		String s = "";
		
		for (int k=0; k<t; k++) {
			r = sc.nextInt();
			// sc.nextLine()
			// 백준 사이트에서는 nextLine() 들어가면 틀린 것으로 처리하지만
			// 이클립스에서는 nextLine() 없으면 씹히는 이슈 발생
			s = sc.next();
			
			
			char[] ch = s.toCharArray();
			
			String p = "";
			for (int i=0; i<ch.length; i++) {
				for (int j=0; j<r; j++) {
					p += ch[i];
				}
			}
			
			System.out.println(p);
		}
		

	}

}
