package com.kh.acmicpc;

import java.util.Scanner;

// 못 풀었다.
public class N1157 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 입력받고 알파벳 배열 세팅 : 대소문자 구분 없으므로 lowercase로 통일
		String str = sc.nextLine().toLowerCase();
		int[] abc = new int[26];
		
		// for문 돌리면서 abc 배열에 사용된 횟수 증가시키기
		for (int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if (ch != -1) {
				abc[ch - 'a']++;
			}
		}
				
		// 가장 많이 사용한 문자 찾기
		int max = -1;
		char ch = ' ';
		for (int i=0; i<abc.length; i++) {
			if (max < abc[i]) {
				max = abc[i];
				ch = (char)(i + 65);
			} else if (max == abc[i]) {
				// 가장 많이 사용된 문자가 중복인 경우 ? 출력
				ch = '?';
			}
		}
		
		System.out.println(ch);

	}

}
