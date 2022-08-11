package com.kh.part_10.string;

import java.util.Scanner;

public class B_FilterProgram {
	public void method2() {
		
		/*
		 * 욕설 필터링 프로그램
		 * 
		 * 사용자에게 문장을 하나 입력받아
		 * 다음 제시되는 단어에 해당할 경우 * 으로 대체해서 출력하도록 구현
		 * 
		 * - 신발끈, 개나리, 수박씨, 호루라기, 시베리아, 십장생, 조카, 주옥, 쌍쌍바, 십자수, 식빵
		 * 
		 * ex) 이런 신발끈같은 개나리 호루라기야
		 *   =>이런 ***같은 *** ****야
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문장을 하나 입력하세요 : ");
		String input = sc.nextLine();
		
		String[] filter = {"신발끈", "개나리", "수박씨", "호루라기", "시베리아", "십장생", "조카", "주옥", "쌍쌍바", "십자수", "식빵"};
		
		String result = "";
		
		for (int i=0; i<input.length(); i++) {			
			String star = "";
			for (int j=0; j<filter[i].length(); j++) {
				star += "*";
			}
			
			input = input.replace(filter[i], star);
		}
		
		System.out.println(input);
	
	}
}
