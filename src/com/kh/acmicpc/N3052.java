package com.kh.acmicpc;

import java.util.HashSet;
import java.util.Scanner;

public class N3052 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] n = new int[10];
		
		for (int i=0; i<10; i++) {
			n[i] = sc.nextInt();
			sc.nextLine();
		}
		
		// HashSet은 중복 불허, 순서 없음
		HashSet<Integer> h = new HashSet<Integer>();
		
		for (int i=0; i<n.length; i++) {
			h.add(n[i] % 42);
		}
		
		System.out.println(h.size());
		
	}

}
