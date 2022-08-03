package com.kh.part_05;

import java.util.Scanner;

public class A_Array {
	public void method7() {
		
		// 3명의 사용자에게 매번 키 정보를 입력받아 배열에 담아두고
		// 3명의 키 정보를 반복문을 이용해 출력
		// 3명의 키 정보를 합계, 평균 (총 합계 / 개수)
		
		Scanner sc = new Scanner(System.in);
		
		double[] height = new double[3];
		
		for (int i=0; i<height.length; i++) {
			System.out.print((i+1) + "번째 키를 입력하세요 (cm) : ");
			height[i] = sc.nextDouble();
			sc.nextLine();
		}
		
		System.out.println("\n===== 키 정보 =====");
		
		double sum = 0.0;
		for (int i=0; i<height.length; i++) {
			System.out.print(height[i] + "cm ");
			sum += height[i];
		}
		double average = sum / 3.0;
		
		System.out.println();
		System.out.println("\n3명의 키 총합 : " + sum+"cm");
		System.out.printf("3명의 키 평균 : %.2fcm", average);
		
	}
}
