package com.kh.part_05;

import java.util.Arrays;

public class B_ArrayCopy {

	// ���� ���� 1��° ���
	public void method2() {
		
		// 1. for���� �̿��ϴ� ���
		// ���ο� �迭�� ���� �� �Ҵ���� ���� ������
		// ���� �迭�κ��� ������ for���� �̿��� �Űܼ� �����ϴ� ���
		
		// ���� �迭
		int[] origin = {1,2,3,4,5};
		
		// ���纻 �迭 ���� �� �Ҵ� - �⺻������ �ʱ�ȭ�� ����
		int[] copy = new int[origin.length];
		
		for (int i=0; i<origin.length; i++) {
			copy[i] = origin[i]; 
		}
		
		System.out.println("== ���纻 �迭 ��� ==");
		for (int i=0; i<copy.length; i++) {
			System.out.print(copy[i]+" ");
		}
		
		copy[2] = 99;
		
		System.out.println("\n== ���纻 �迭 ���� ��");
		for (int i=0; i<copy.length; i++) {
			System.out.print(copy[i]+" ");
		}
		
		System.out.println("\n== ���纻 �迭 ���� �� ���� ==");
		for (int i=0; i<origin.length; i++) {
			System.out.print(origin[i]+" ");
		}
		
		System.out.println("\n���� �迭 �ؽ��ڵ� : " + origin.hashCode());
		System.out.println("���纻 �迭 �ؽ��ڵ� : " + copy.hashCode());
		// ������ �Ѽյ��� ���� : ���� ����
		
	} // method2

	// ���� ���� 2��° ���
	public void method3() {
		
		// 2. ���ο� �迭�� ����(���� �� �Ҵ�) ��
		// 	  System Ŭ�������� �����ϴ� arraycopy �޼ҵ� ȣ���Ͽ� ����
		
		// ���� �迭
		int[] origin = {1,2,3,4,5};
		
		// ���纻 �迭
		int[] copy = new int[10]; // �⺻������ �ʱ�ȭ�� ����
		
		// System Ŭ������ arraycopy �޼ҵ� ����
		// [ ǥ����  ]
		// System.arraycopy(�����迭��, ���縦�������ε���, ���纻�迭��, ���縦�������ε���, �����Ұ���);
		
		System.arraycopy(origin, 0, copy, 0, 5);
		// copy = { 1,2,3,4,5,0,0,0,0,0 } �� ��µ�.

		// System.arraycopy(origin, 0, copy, 2, 5);
		// copy = { 0,0,1,2,3,4,5,0,0,0 }
		
		// System.arraycopy(origin, 0, copy, 1, 3);
		// copy = { 0,1,2,3,0,0,0,0,0,0 }
		
		// System.arraycopy(origin, 2, copy, 1, 3);
		// copy = { 0,3,4,5,0,0,0,0,0,0 }
		
		// System.arraycopy(origin, 2, copy, 9, 2);
		// copy = { 0,0,0,0,0,0,0,0,0,3 ... 4 } 
		// => ArrayIndexOutOfBoundsException : ���� �������� �ε��� ������ ���
		
		System.out.println("== ���纻 �迭 ��� ==");
		for (int i=0; i<copy.length; i++) {
			System.out.print(copy[i]+ " ");
		}
		
		System.out.println("\n���� �ؽ��ڵ� : " + origin.hashCode());
		System.out.println("���纻 �ؽ��ڵ� : " + copy.hashCode());
		
		// �ٸ� �ּҰ��� ������ ���� == �ٸ� ���� �����ϰ� �ִ�.
		// => ���� ���簡 �̷���� �迭�� ������ �� ���� ������ ���� ����.
	}
	
	// ���� ���� 3��° ���
	public void method4() {
		
		// 3. Arrays Ŭ������ copyOf() �޼ҵ� ȣ��
		
		// ���� �迭
		int[] origin = {1,2,3,4,5};
		
		// ���纻 �迭
		// Arrays.copyOf() �޼ҵ� �����
		// [ ǥ���� ] ���纻 �迭 = Arrays.copyOf(�����迭��, �����Ұ���);
		
		int[] copy = Arrays.copyOf(origin, 5);
		// ���纻 �迭�� ũ��� copyOf���� �ۼ��� �����Ұ����� �ȴ�.
		
		System.out.println("== ���纻 �迭 ��� ==");
		for (int i=0; i<copy.length; i++) {
			System.out.print(copy[i]+" ");
		}
		
		System.out.println("\n���� �ؽ��ڵ� : " + origin.hashCode());
		System.out.println("���纻 �ؽ��ڵ� : " + copy.hashCode());
		// �ٸ� �ּҰ��� ������ ���� == �ٸ� ���� �����ϰ� �ִ�.
		
	} // method4
	
	// ���� ���� 4��° ���
	public void method5() {
		
		// 4. clone �޼ҵ� ȣ���Ͽ� ����
		
		// ���� �迭
		int[] origin = {1,2,3,4,5};
		
		// ���纻 �迭
		// clone �޼ҵ�
		// [ ǥ����  ] ���纻�迭�� = �����迭��.clone(); -> �� �ڷ����� �����ؾ� ��
		// ������ �ɼ� �������� ����. ���� �迭�� �״��.
		int[] copy = origin.clone();
		
		System.out.println("== ���纻 �迭 ��� ==");
		// [1, 2, 3, 4, 5] ����
		/*
		System.out.print("[");
		for (int i=0; i<copy.length; i++) {
			if (i != copy.length-1) {		// ������ �ε��� ������
				System.out.print(copy[i]+", ");
			} else {						// ������ �ε�����
				System.out.print(copy[i]);
			}
		}
		System.out.print("]");
		*/
		
		// Arrays Ŭ������ toString �޼ҵ� ȣ��
		// Arrays.toString(����ҹ迭��)
		System.out.println(Arrays.toString(copy));
		
		System.out.println("���� �迭�� �ؽ��ڵ� : " + origin.hashCode());
		System.out.println("���纻 �迭�� �ؽ��ڵ� : " + copy.hashCode());
		// �ٸ� �ּҰ��� ������ ���� == �ٸ� ���� �����ϰ� �ִ�.
		
	}

}
