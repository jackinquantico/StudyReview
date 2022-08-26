package com.kh.part_14.depositSystem.view;

import java.util.Scanner;

import com.kh.part_14.depositSystem.controller.BankController;

public class BankView {

	private Scanner sc = new Scanner(System.in);
	private BankController bc = new BankController();
	
	public void mainMenu() {
		
		while (true) {
			System.out.println("==================");
			System.out.println("===== 우리 은행 =====");
			System.out.println("1. 예금 입금");
			System.out.println("2. 예금 출금");
			System.out.println("3. 잔액 조회");
			System.out.println("0. 프로그램 종료");
			System.out.println("==================");
			System.out.print("메뉴 번호 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				depositMoney();
				break;
			case 2:
				withdrawMoney();
				break;
			case 3:
				System.out.println("===== 예금 조회 =====");
				showMoney();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
			System.out.println();
		}
	}
	
	public void depositMoney() {
		
		System.out.println("===== 예금 입금 =====");
		System.out.print("예금주 명 입력 : ");
		String owner = sc.nextLine();
		System.out.print("계좌번호 입력 : ");
		String accountNum = sc.nextLine();
		
		int money = bc.showMoney(owner, accountNum);
		
		if (money > -1) {
			System.out.println("현재 잔액은 "+money+"원 입니다.");
		} else {
			System.out.println("해당 계좌가 존재하지 않습니다.");
		}
		
		System.out.print("입금할 금액 입력 : ");
		int deposit = sc.nextInt();
		sc.nextLine();
		
		int result = bc.depositMoney(owner, accountNum, deposit);
		
		if (result > 0) {
			System.out.println("입금에 성공했습니다.");
		} else {
			System.out.println("입금에 실패했습니다.");
		}
		
	}
	
	public void withdrawMoney() {
		

		System.out.println("===== 예금 출금 =====");
		System.out.print("예금주 명 입력 : ");
		String owner = sc.nextLine();
		System.out.print("계좌번호 입력 : ");
		String accountNum = sc.nextLine();
		
		int money = bc.showMoney(owner, accountNum);
		
		if (money > -1) {
			System.out.println("현재 잔액은 "+money+"원 입니다.");
		} else {
			System.out.println("해당 계좌가 존재하지 않습니다.");
		}
		
		System.out.print("출금할 금액 입력 : ");
		int withdraw = sc.nextInt();
		sc.nextLine();
		
		int result = bc.withdrawMoney(owner, accountNum, withdraw);
		
		if (result > 0) {
			System.out.println("입금에 성공했습니다.");
		} else {
			System.out.println("입금에 실패했습니다.");
		}
		
	}

	public void showMoney() {
		
		System.out.print("예금주 명 입력 : ");
		String owner = sc.nextLine();
		System.out.print("계좌번호 입력 : ");
		String accountNum = sc.nextLine();
		
		int money = bc.showMoney(owner, accountNum);
		
		if (money > -1) {
			System.out.println("현재 잔액은 "+money+"원 입니다.");
		} else {
			System.out.println("해당 계좌가 존재하지 않습니다.");
		}
		
	}

}
