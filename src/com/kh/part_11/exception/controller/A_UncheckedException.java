package com.kh.part_11.exception.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

//����� ���� ������ ���� (RuntimeException Ŭ������ �ڼյ�)
public class A_UncheckedException {
	
	/*
	 * * RuntimeException
	 * - ���α׷� ���� �� �߻��ϴ� ���ܵ�
	 * 
	 * * RuntimeException �� �ڽ� Ŭ����
	 * - ArrayIndexOutOfBoundsException : �迭�� �������� �ε����� ������ �� �߻��ϴ� ����
	 * - NegativeArraySizeException : �迭�� ũ�⸦ ������ �������� ��� �߻��ϴ� ����
	 * - ClassCastException : ����� �� ���� ����ȯ�� ����� ��� �߻��ϴ� ����
	 * - NullPointerException : ������������ ���� null�ӿ��� �ұ��ϰ� �����Ϸ��� �� �� �߻��ϴ� ����
	 * - ArithmeticException : ������ ���� �� 0���� ���� �� �߻�
	 * - ...
	 * 
	 * => �̷��� RuntimeException�� ���õ� ���ܵ��� ����� ���� �����ϱ� ������
	 *    ���� ��ü�� ���ʿ� �߻��� �ȵǰԲ� ���ǹ����� �ذ� �����ϱ� ��. (��ó��) -> ����
	 *    ���� ����ó�� (���ܰ� �߻����� �� ������ ������ �����صδ� ��ó��) �� �ʿ䰡 ����.
	 */
	
	// ��ĳ�� ��ü�� ���� ������ ���� : Ŭ���� ���� �ٷ� ����
	// Ŭ���� ���� ��� �޼ҵ忡�� ������ �� �� �ְ�
	
	Scanner sc = new Scanner(System.in);
	
	public void method1() {
		
		// ArithmeticException : ������ ���� �� 0���� ���� �� �߻�
		
		// ����ڷκ��� �� ���� �������� �Է� �޾� ������ ������ ���
		System.out.print("ù��° ���� : ");
		int num1 = sc.nextInt();
		sc.nextLine();
		
		System.out.print("�ι�° ���� (0 ����) : ");
		int num2 = sc.nextInt();
		sc.nextLine();
		
		/*
		int beforeresult = num1 / num2;		// num2 ���� 0���� ������ ���� �߻�
		System.out.println(beforeresult);
		*/
		
		/* �ذ��� 1. ���ǹ����� ó�� (��ó��) : ���ܰ� �߻����� �ʰ� if������ ���� �˻��� ��� ����
		//			=> ����ó���� �ƴ�, ����
		if (num2 != 0) {
			int result = num1 / num2;
			System.out.println("������ ���� ��� : "+result);
		} else {
			System.out.println("0���� ���� �� �����ϴ�.");
		}
		
		System.out.println("���α׷� ����");
		*/
		
		// �ذ��� 2. ����ó�� �������� �ذ� : try - catch��
		//			=> ���ܰ� �߻����� ��쿡 ����ؼ� ������ ������ �����صδ� ��
		// 			=> ���������� �����ϴ� ������ ���ǹ� ����
		
		/*
		 * try - catch��
		 * 
		 * [ ǥ���� ]
		 * try {
		 * 		������ �ڵ� (���ܰ� �߻��� ���� ���� ����);
		 * } catch (�߻��ҿ���Ŭ������ ������) {
		 * 		�ش� ���ܰ� �߻��� ��� ������ ����;
		 * }
		 */
		
		try {
			int result = num1 / num2;
			System.out.println("������ ���� ��� : " + result);
			
		} catch (ArithmeticException e) {
//			System.out.println("0���� ���� �� �����ϴ�.");
			
			e.printStackTrace(); // �������̵� �� �޼ҵ�
			// ���� ���ܰ� ��𿡼� �߻��ߴ��� �˷���
		}
		
		System.out.println("���α׷� ����");
	}
	
	public void method2() {
		
		System.out.print("���� �Է� (0 ����) : ");
		try {
			int num = sc.nextInt(); // InputMismatchException �߻��ҹ��� ���� (���� �̿��� �� �Է��� ���)
			sc.nextLine();
			
			int result = 10 / num; // ArithmeticException �߻��ҹ��� ����
			
			System.out.println("������ ��� : "+result);
			
		} catch (ArithmeticException e) {
			System.out.println("0���� ���� �� �����ϴ�.");
			
		} catch (InputMismatchException e) {
			System.out.println("������ �Է��ϼ���.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("���α׷� ����");
			
		}
		
		// ���� catch �� : ���ܰ� ���� �� �߻��� ���ɼ��� ���� ���, ������ catch ���� �߰��Ͽ�
		// �� �������� ����ó���� ��������
		
	}
	
	public void method3() {
		
		// ArrayIndexOutOfBoundsException : �迭�� �������� �ε����� ������ �� �߻��ϴ� ����
		// NegativeArraySizeException : �迭�� ũ�⸦ ������ �������� ��� �߻��ϴ� ����
		
		System.out.print("�迭�� ũ�⸦ �Է��ϼ��� : ");
		int size = sc.nextInt();
		sc.nextLine();
		
		/* if �� Ȱ��
		if (size >= 101) {
			int[] arr = new int[size]; // NegativeArraySizeException �߻��ҹ��� ����
			
			System.out.println("100�� �ε��� �� : "+arr[100]); // ArrayIndexOutOfBoundsException �߻��ҹ��� ����
		}
		*/
		
		/*
		try {
			int[] arr = new int[size];
			
			System.out.println("100�� �ε��� �� : "+arr[100]);
		} catch (NegativeArraySizeException e) {
			System.out.println("�迭�� ũ��δ� ����� �Է����ּ���");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("�������� �ε����� �����߽��ϴ�.");
		}
		*/
		
		/*
		try {
			int[] arr = new int[size];
			
			System.out.println("100�� �ε��� �� : "+arr[100]);
		} catch (RuntimeException e) {
			// �������� �����Ͽ� �θ� Ÿ���� ���� Ŭ�����ε� ó�� ����
			// ���� catch ���� �پ������, ��Ȯ�� � ���ܰ� �߻��ߴ��� �ľ��ϱ� �������.
			System.out.println("���� �߻�. �迭�� ũ�⸦ ������ �Է��߰ų� �������� �ε����� ����.");
		}
		*/
		
		/*
		try {
			int[] arr = new int[size];
			System.out.println("100��° �ε��� �� : "+arr[100]);
			
		} catch (RuntimeException e) { 		
			// �������� �����ؼ� �θ� ����Ŭ������ �ڽ� ���� Ŭ������ catch���� ���� �ۼ��Ϸ���
			// => �ڽ� ���� Ŭ�������� �θ� ���� Ŭ���� ������ �ۼ��ؾ� �Ѵ�.
			// => ������ ���� �ڽ� Ÿ���� ���� Ŭ������ ���� ����ؾ� �Ѵ�.
			System.out.println("���� �߻�");
		} catch (NegativeArraySizeException e) { // unreachable catch ���� ��
			System.out.println("����� �Է��ϼ���");
		}
		*/
		
		try {
			int[] arr = new int[size];
			System.out.println("100��° �ε��� �� : "+arr[100]);
			
		} catch (NegativeArraySizeException e) {
			System.out.println("����� �Է��ϼ���");
			
		} catch (RuntimeException e) {
			System.out.println("���� �߻�");
		}
		
		
		/* instanceof ������
		try {
			int[] arr = new int[size];
			System.out.println("100��° �ε��� �� : "+arr[100]);
		} catch (RuntimeException e) {
			System.out.println("���� �߻�");
			if (e instanceof NegativeArraySizeException) {
				System.out.println("����� �Է��ϼ���");
			} else if (e instanceof ArrayIndexOutOfBoundsException) {
				System.out.println("�������� �ε����� �����߽��ϴ�.");
			} else {
				e.printStackTrace();
			}
		}
		*/
		
		System.out.println("���α׷� ����");
	}
	
	/*
	 * * RuntimeException ���� ���ܴ�
	 * - ���ǹ����� �ذ� ���� : ���� ��ü�� �߻� �� �ǰԲ� �����ڰ� �ҽ��ڵ�� �ڵ鸵 ���� (����ó���� �ƴ�)
	 * - ����ó�� �������� �ذ� ���� : ���ܰ� �߻����� ���� ����ؼ� �� �� ������ ������ �����صδ� �� => ������ ����
	 * 
	 * ������ ������ ��Ȳ�� ���ǹ����� �ذ��ϴ� ���� �� ����
	 * ������ �Ұ����� ��Ȳ�� ������ ����ó�� �������� �ذ��� �� �ۿ� ����.
	 * 
	 * => RuntimeException �迭�� ��� ����� ���� ������ ��Ȳ�̱� ������
	 *    ���ǹ����� �ذ��ϴ� ���� ����
	 *    ��, ����ó�� ������ �ۼ��ϴ� ���� �ʼ��� �ƴ�. => Unchecked Exception
	 */
}
