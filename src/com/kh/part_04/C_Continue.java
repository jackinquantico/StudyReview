package com.kh.part_04;

public class C_Continue {
	
	/*
	 * 2�� ~ 9�ܱ��� ��� ����ϵ�
	 * 4�� ��� ���� 4�ܰ� 8�� �����ϰ� ��� 
	 */
	
	public void method1() {
		
		// continue�� �ִ� ����
		for (int i=2; i<=9; i++) {
			// 4�� ����� ���� continue
			if (i % 4 == 0) {
				continue;
			}
			System.out.printf("---- %d�� -----\n", i);
			for (int j=1; j<=9; j++) {
				System.out.printf("%d X %d = %d\n", i, j, (i*j));
			}
			System.out.println();
		}
		
	} // method1
	
	public void method2() {
		
		// continue ���� ����
		for (int i=2; i<=9; i++) {
			// 4�� ����� �ƴ� �ܼ������� ����ϱ�
			if (i % 4 != 0) {
				System.out.printf("---- %d�� -----\n", i);
				for (int j=1; j<=9; j++) {
					System.out.printf("%d X %d = %d\n", i, j, (i*j));
				}
				System.out.println();
			}
		}
		
	} // method2
	
}
