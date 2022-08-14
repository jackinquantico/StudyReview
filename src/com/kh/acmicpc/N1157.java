package com.kh.acmicpc;

import java.util.Scanner;

// �� Ǯ����.
public class N1157 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// �Է¹ް� ���ĺ� �迭 ����
		String str = sc.nextLine();
		int[] abc = new int[26];
		
		// for�� �����鼭 abc �迭�� ���� Ƚ�� ������Ű��
		for (int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if (ch != -1) {
				abc[ch - 'a']++;
			}
		}
				
		// ���� ���� ����� ���� ã��
		int max = abc[0];
		for (int i=1; i<abc.length; i++) {
			int tmp = abc[i];
			if (max <= tmp) {
				// ���� ���� ���� ���ڰ� �ߺ��� ��� ? ���
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
