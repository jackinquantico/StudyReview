package com.kh.part_03;

import java.util.Scanner;

public class B_Switch {
	
	public void method6() {
		
		/*
		 * ����ڿ��� 1��~12�� ������ �� �Է¹޾� �ش� �޿� �ش��ϴ� ���� ���
		 * 3,4,5 : "���Դϴ�."
		 * 6,7,8 : "�����Դϴ�."
		 * 9,10,11 : "�����Դϴ�."
		 * 12,1,2 : "�ܿ��Դϴ�.
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1��~12�� ������ ���� �Է��ϼ��� : ");
		int num = sc.nextInt();
		
		sc.nextLine();
		
		String season = "";
		
		switch (num) {
		case 3:
		case 4:
		case 5:	season = "��";
			break;
		case 6:
		case 7:
		case 8: season = "����";
			break;
		case 9:
		case 10:
		case 11: season = "����";
			break;
		case 12:
		case 1:
		case 2: season = "�ܿ�";
			break;
		default: System.out.println("�߸� �Է��߽��ϴ�.");
			return;
		}
		
		System.out.printf("%d���� %s�Դϴ�.", num, season);
	}
	
}
