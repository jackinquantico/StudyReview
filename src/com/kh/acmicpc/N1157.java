package com.kh.acmicpc;

import java.util.Scanner;

// �� Ǯ����.
public class N1157 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// �Է¹ް� ���ĺ� �迭 ���� : ��ҹ��� ���� �����Ƿ� lowercase�� ����
		String str = sc.nextLine().toLowerCase();
		int[] abc = new int[26];
		
		// for�� �����鼭 abc �迭�� ���� Ƚ�� ������Ű��
		for (int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if (ch != -1) {
				abc[ch - 'a']++;
			}
		}
				
		// ���� ���� ����� ���� ã��
		int max = -1;
		char ch = ' ';
		for (int i=0; i<abc.length; i++) {
			if (max < abc[i]) {
				max = abc[i];
				ch = (char)(i + 65);
			} else if (max == abc[i]) {
				// ���� ���� ���� ���ڰ� �ߺ��� ��� ? ���
				ch = '?';
			}
		}
		
		System.out.println(ch);

	}

}
