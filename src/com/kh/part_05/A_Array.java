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
	
	public void method8() {
		
		// 사용자에게 문자열을 하나 입력받은 후
		// 각각의 문자들을 char배열에 옮겨담기
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문자열을 하나 입력하세요 : ");
		String str = sc.nextLine();
		
		// 배열의 크기 = 문자열의 길이
		char[] ch = new char[str.length()];
		
		// 배열에 대입
		for (int i=0; i<ch.length; i++) {
			ch[i] = str.charAt(i);
		}
		
		// 배열 출력
		for (int i=0; i<ch.length; i++) {
			System.out.print(ch[i]+ " ");
		}
		
	}
	
	public void method10() {
		
		/*
		 * 좋아하는 과일의 개수를 입력받아 
		 * 그만큼 반복을 돌리면서 과일명을 입력받기 -> 입력받을 때마다 문자열 배열에 담아서 출력
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("좋아하는 과일의 개수 입력 : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		String[] fruit = new String[num];
		
		for (int i=0; i<num; i++) {
			System.out.print("과일 이름 입력 : ");
			fruit[i] = sc.nextLine();
		}
		
		System.out.println("== 입력한 과일 ==");
		for (int i=0; i<num; i++) {
			System.out.print(fruit[i] + " ");
		}
	}
}
