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
			// ���� ����Ʈ������ nextLine() ���� Ʋ�� ������ ó��������
			// ��Ŭ���������� nextLine() ������ ������ �̽� �߻�
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
