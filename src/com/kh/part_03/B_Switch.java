package com.kh.part_03;

import java.util.Scanner;

public class B_Switch {
	
	public void method6() {
		
		/*
		 * 사용자에게 1월~12월 사이의 월 입력받아 해당 달에 해당하는 계절 출력
		 * 3,4,5 : "봄입니다."
		 * 6,7,8 : "여름입니다."
		 * 9,10,11 : "가을입니다."
		 * 12,1,2 : "겨울입니다.
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1월~12월 사이의 월을 입력하세요 : ");
		int num = sc.nextInt();
		
		sc.nextLine();
		
		String season = "";
		
		switch (num) {
		case 3:
		case 4:
		case 5:	season = "봄";
			break;
		case 6:
		case 7:
		case 8: season = "여름";
			break;
		case 9:
		case 10:
		case 11: season = "가을";
			break;
		case 12:
		case 1:
		case 2: season = "겨울";
			break;
		default: System.out.println("잘못 입력했습니다.");
			return;
		}
		
		System.out.printf("%d월은 %s입니다.", num, season);
	}
	
}
