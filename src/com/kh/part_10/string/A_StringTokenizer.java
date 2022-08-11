package com.kh.part_10.string;

import java.util.StringTokenizer;

public class A_StringTokenizer {


	public void method() {
		
		// ���ڿ��� �и���Ű�� ���
		
		String str = "Java,Oracle,JDBC,HTML,Server,Spring";
		
		// �����ڸ� �����ؼ� �ش� ���ڿ��� �и���Ű�� ���
		// ��� 1. �и��� ���ڿ����� String[] �迭�� ��Ƽ� �����ϰ��� �� ��
		// 		   ���ڿ�.split(String ������) : ��ȯ�� String[]
		String[] arr = str.split(",");
		
//		for (int i=0; i<arr.length; i++) {
//			System.out.println(arr[i]);
//		}
		
		// ���ڿ� �迭�� ��� for���� �ٸ��� ����� �� �ִ�.
		// ���� for��, foreach ��
		// for (���� �޾��� ���� ���� : ������ �迭 �Ǵ� �÷��Ǹ�)  {  }
		for (String s : arr) {
			System.out.println(s);
		}
		
		System.out.println("=============================");
		
		// ��� 2. �и��� ������ ���ڿ����� "��ū"(�ܾ�) ������ ����ϰ� ���� ��
		// 		 java.util.StringTokenizer Ŭ���� �̿�
		// 		 StringTokenizer stn = new StringTokenizer(�и���Ű�����ϴ¹��ڿ�, ������);
		
		StringTokenizer stn = new StringTokenizer(str, ",");
		
		// �и��� ���ڿ��� ������ �˰� ���� ��
		System.out.println("�и��� ���ڿ��� ���� : "+stn.countTokens());
		
		// ���
//		System.out.println(stn.nextToken()); // Java
//		System.out.println(stn.nextToken()); // Oracle
//		System.out.println(stn.nextToken()); // JDBC
//		System.out.println(stn.nextToken()); // HTML
//		System.out.println(stn.nextToken()); // Server
//		System.out.println(stn.nextToken()); // Spring
		
		// �� �������� �ٽ� �� �� nextToken() ȣ���ϸ�
		// NoSuchElementException �߻� : �� �̻� ã�� ��Ұ� ����.
		// => ���� �����ִ� stn ��ū ������ ������ ����� �� �߻��ϴ� ����
		
//		System.out.println("���� ����ִ� ���ڿ��� ���� : "+stn.countTokens());
		
//		for (int i=0; i<stn.countTokens(); i++) {
//			System.out.println(stn.nextToken());
//			// Java, Oracle, JDBC ���
//		}
		
		// i=0; token = 6, true; => Java ��� => i++, token = 5;
		// i=1; token = 5, true; => Oracle ��� => i++, token = 4;
		// i=2; token = 4, true; => JDBC ��� => i++, token = 3;
		// i=3; token = 3, false => �ݺ��� ����
		
		// �ذ��� 1. ���� Ȱ��
//		int count = stn.countTokens();
//		
//		for (int i=0; i<count; i++) {
//			System.out.println(stn.nextToken());
//		}
		
		// �ذ��� 2. while�� ���
		// stn.hasMoreTokens() : ��ȯ�� token�� ������ ���� true/false ��ȯ
		while (stn.hasMoreTokens()) { // stn ������ �����ִ� ��ū�� ���� ���ȸ� �ݺ�
			System.out.println(stn.nextToken());
		}
		
	}
}
