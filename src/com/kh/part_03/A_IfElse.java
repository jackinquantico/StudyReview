package com.kh.part_03;

import java.util.Scanner;

public class A_IfElse {
	public void method5() {
		
		/*
		 * ����ڷκ��� �̸��� Ű����� �Է� �޾� ���ΰ� ��ġ�� ��� "�����Դϴ�. �������."
		 * ��ġ���� ���� ��� "������ �ƴմϴ�. ���ư��ּ���." ���
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�̸��� �Է��ϼ��� : ");
		String name = sc.nextLine();
		
		if (name.equals("ȫ�浿")) {
			System.out.println("�����Դϴ�. �������.");
		} else {
			System.out.println("������ �ƴմϴ�. ���ư��ּ���.");
		}
		
	}
}
