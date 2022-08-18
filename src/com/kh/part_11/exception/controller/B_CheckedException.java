package com.kh.part_11.exception.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//���� �Ұ����� ���ܵ� (IOException�� �ڼ�)
public class B_CheckedException {

	/*
	 * * CheckedException�� �ݵ�� ����ó���� �ؾ��ϴ� ���ܵ�
	 * 	 (��, ���� �Ұ��� ������ �߻��ϱ� ������ �̸� ����ó�� ������ �ۼ��ؾ� �Ѵ�.)
	 * => �ַ� ����� �ƴ� �ܺ� ��ü�� � ������� �߻��� �� ���ܰ� ��Ÿ�� 
	 */
	
	public void method1() throws IOException {
		
		/*
		try {
			method2(); // method2���� �߻��� ���� ��������, method2���� ó���� �ٷ� ���� �ʰ�
			
		} catch (IOException e) { // method1���� try-catch������ ó������.
			
			System.out.println("���� �߻���");
		}
		*/
		
		method2();
	}
	
	// throws
	public void method2() throws IOException {
		
		// Scanner�� ���� Ű����� ���� �Է¹��� �� �ִ� ��ü (��, ���ڿ��θ� ����)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("�ƹ� ���ڿ��̳� �Է��ϼ��� : ");
		
		/* 1. try-catch�� : ���ܰ� �߻��ҹ��� �ڵ尡 �ִ� �� �ڸ� �״�� �ٷ� ���⿡�� ���� ó��
		try {
			String str = br.readLine(); // �ݵ�� ����ó���ؾ���.
			// readLine() �޼ҵ带 ȣ��� IOException�� �߻��� ���� ������ ������ ������ �˷��� (���� ����)
			
			System.out.println("���ڿ��� ���� : "+str.length());
			
		} catch (IOException e) { // checked exception : ������ �� ����
			System.out.println("���� �߻�");
		}
		*/
		
		// 2. throws Ű���� : ���ѱ��, �����ϱ�
		// 					���� ���⼭ ���ܸ� �ٷ� ó������ �ʰ�, ���� �� �޼ҵ带 ȣ���� ������ ���ѱ�ڴ�.
		String str = br.readLine();
		// readLine() �޼ҵ��� ����ο��� throws IOException �� ���ǵǾ� �־
		// ȣ��� ������ IOException�� ó������� �Ѵ�.
		System.out.println("���ڿ��� ���� : "+str.length());
		
	}
}
