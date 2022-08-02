package com.kh.part_04;

public class C_Continue {
	
	/*
	 * 2단 ~ 9단까지 모두 출력하되
	 * 4의 배수 단인 4단과 8단 제외하고 출력 
	 */
	
	public void method1() {
		
		// continue문 있는 버전
		for (int i=2; i<=9; i++) {
			// 4의 배수인 단은 continue
			if (i % 4 == 0) {
				continue;
			}
			System.out.printf("---- %d단 -----\n", i);
			for (int j=1; j<=9; j++) {
				System.out.printf("%d X %d = %d\n", i, j, (i*j));
			}
			System.out.println();
		}
		
	} // method1
	
	public void method2() {
		
		// continue 없는 버전
		for (int i=2; i<=9; i++) {
			// 4의 배수가 아닌 단수에서만 출력하기
			if (i % 4 != 0) {
				System.out.printf("---- %d단 -----\n", i);
				for (int j=1; j<=9; j++) {
					System.out.printf("%d X %d = %d\n", i, j, (i*j));
				}
				System.out.println();
			}
		}
		
	} // method2
	
}
