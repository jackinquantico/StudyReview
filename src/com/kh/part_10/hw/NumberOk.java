package com.kh.part_10.hw;

import java.util.Scanner;

public class NumberOk {
	
	private int ran;
	
	public NumberOk() {
		
	}
	
	public void numGame() {
		
		ran = (int)(Math.random() * 100) + 1;
		int count = 0;
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("1부터 100 사이의 정수를 하나 입력하세요 : ");
			int input = sc.nextInt();
			sc.nextLine();
			
			if (input < ran) {
				count++;
				System.out.println(input + "보다 큽니다. "+ count + "번째 실패!!");
			} else if (input > ran) {
				count++;
				System.out.println(input + "보다 작습니다."+ count+ "번째 실패!!");
			} else {
				System.out.println(count + "번 만에 맞췄습니다!");
				
				while (true) {
					System.out.print("계속 하시겠습니까? (Y/N) : ");
					char answer = sc.nextLine().toUpperCase().charAt(0);
					
					if (answer == 'Y') {
						System.out.println("\n새로운 게임을 시작합니다!!");
						break;
					} else if (answer == 'N') {
						System.out.println("게임을 종료합니다.");
						return;
					} else {
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
					}
				}
			}
		}
		
	}

}
