package com.kh.part_10.string;

import java.util.Scanner;

public class B_FilterProgram {
	public void method2() {
		
		/*
		 * �弳 ���͸� ���α׷�
		 * 
		 * ����ڿ��� ������ �ϳ� �Է¹޾�
		 * ���� ���õǴ� �ܾ �ش��� ��� * ���� ��ü�ؼ� ����ϵ��� ����
		 * 
		 * - �Ź߲�, ������, ���ھ�, ȣ����, �ú�����, �����, ��ī, �ֿ�, �ֹֽ�, ���ڼ�, �Ļ�
		 * 
		 * ex) �̷� �Ź߲����� ������ ȣ�����
		 *   =>�̷� ***���� *** ****��
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("������ �ϳ� �Է��ϼ��� : ");
		String input = sc.nextLine();
		
		String[] filter = {"�Ź߲�", "������", "���ھ�", "ȣ����", "�ú�����", "�����", "��ī", "�ֿ�", "�ֹֽ�", "���ڼ�", "�Ļ�"};
		
		String result = "";
		
		for (int i=0; i<input.length(); i++) {			
			String star = "";
			for (int j=0; j<filter[i].length(); j++) {
				star += "*";
			}
			
			input = input.replace(filter[i], star);
		}
		
		System.out.println(input);
	
	}
}
