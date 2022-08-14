package com.kh.acmicpc;

import java.util.Scanner;

// 못 풀었다.
public class N1157 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 입력받고 알파벳 배열 세팅
		String str = sc.nextLine();
		int[] abc = new int[26];
		
		// for문 돌리면서 abc 배열에 사용된 횟수 증가시키기
		for (int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if (ch != -1) {
				abc[ch - 'a']++;
			}
		}
				
		// 가장 많이 사용한 문자 찾기
		int max = abc[0];
		for (int i=1; i<abc.length; i++) {
			int tmp = abc[i];
			if (max <= tmp) {
				// 가장 많이 사용된 문자가 중복인 경우 ? 출력
				if (max == tmp) {
					System.out.println("?");
					return;
				}
				max = tmp;
				System.out.println(i+'a');
			} 
		}
		
		System.out.println(max);

	}

}
