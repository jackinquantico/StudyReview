package com.kh.part_05;

import java.util.Scanner;

public class A_Array {
	public void method7() {
		
		// 3���� ����ڿ��� �Ź� Ű ������ �Է¹޾� �迭�� ��Ƶΰ�
		// 3���� Ű ������ �ݺ����� �̿��� ���
		// 3���� Ű ������ �հ�, ��� (�� �հ� / ����)
		
		Scanner sc = new Scanner(System.in);
		
		double[] height = new double[3];
		
		for (int i=0; i<height.length; i++) {
			System.out.print((i+1) + "��° Ű�� �Է��ϼ��� (cm) : ");
			height[i] = sc.nextDouble();
			sc.nextLine();
		}
		
		System.out.println("\n===== Ű ���� =====");
		
		double sum = 0.0;
		for (int i=0; i<height.length; i++) {
			System.out.print(height[i] + "cm ");
			sum += height[i];
		}
		double average = sum / 3.0;
		
		System.out.println();
		System.out.println("\n3���� Ű ���� : " + sum+"cm");
		System.out.printf("3���� Ű ��� : %.2fcm", average);
		
	}
	
	public void method8() {
		
		// ����ڿ��� ���ڿ��� �ϳ� �Է¹��� ��
		// ������ ���ڵ��� char�迭�� �Űܴ��
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("���ڿ��� �ϳ� �Է��ϼ��� : ");
		String str = sc.nextLine();
		
		// �迭�� ũ�� = ���ڿ��� ����
		char[] ch = new char[str.length()];
		
		// �迭�� ����
		for (int i=0; i<ch.length; i++) {
			ch[i] = str.charAt(i);
		}
		
		// �迭 ���
		for (int i=0; i<ch.length; i++) {
			System.out.print(ch[i]+ " ");
		}
		
	}
}
