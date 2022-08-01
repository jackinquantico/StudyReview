package com.kh.part_03;

import java.util.Scanner;

public class A_IfElse {
	public void method5() {
		
		/*
		 * 사용자로부터 이름을 키보드로 입력 받아 본인과 일치할 경우 "본인입니다. 어서오세요."
		 * 일치하지 않을 경우 "본인이 아닙니다. 돌아가주세요." 출력
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름을 입력하세요 : ");
		String name = sc.nextLine();
		
		if (name.equals("홍길동")) {
			System.out.println("본인입니다. 어서오세요.");
		} else {
			System.out.println("본인이 아닙니다. 돌아가주세요.");
		}
		
	}
}
