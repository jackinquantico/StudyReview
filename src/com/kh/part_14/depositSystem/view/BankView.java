package com.kh.part_14.depositSystem.view;

import java.util.Scanner;

import com.kh.part_14.depositSystem.controller.BankController;

public class BankView {

	private Scanner sc = new Scanner(System.in);
	private BankController bc = new BankController();
	private String tmpOwner = "";
	private String tmpAccountNum = "";
	
	// LOGIN 메뉴 먼저 만들어서 owner, accountNum 입력받고
	// mainMenu 진입하도록하기
	public void loginMenu() {
		
		while (true) {
			System.out.println("==================");
			System.out.println("===== 우리 은행 =====");
			System.out.println("1. 예금주 로그인");
			System.out.println("0. 프로그램 종료");
			System.out.println("==================");
			System.out.print("메뉴 번호 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				loginAccount();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void loginAccount() {
		
		System.out.println("===== 로그인 메뉴 =====");
		System.out.print("예금주 명 입력 : ");
		String owner = sc.nextLine();
		System.out.print("계좌번호 입력 : ");
		String accountNum = sc.nextLine();
		
		int result = bc.loginAccount(owner, accountNum);
		
		if (result > 0) {
			tmpOwner = owner;
			tmpAccountNum = accountNum;
			System.out.println("로그인에 성공했습니다.");
			System.out.println("메인 메뉴로 이동합니다.");
			System.out.println();
			mainMenu();
		} else {
			System.out.println("로그인에 실패했습니다.");
			System.out.println("이전 메뉴로 이동합니다.");
			return;
		}
	}
	
	public void mainMenu() {
		
		while (true) {
			System.out.println("==================");
			System.out.println("===== 우리 은행 =====");
			System.out.println("1. 예금 입금");
			System.out.println("2. 예금 출금");
			System.out.println("3. 잔액 조회");
			System.out.println("4. 계좌 로그아웃");
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
				showMoney();
				break;
			case 4:
				logoutAccount();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
			System.out.println();
		}
	}
	
	public void depositMoney() {
		
		System.out.println("===== 예금 입금 =====");
		
		int money = bc.showMoney(tmpOwner, tmpAccountNum);
		System.out.println("현재 잔액은 "+money+"원 입니다.");
		
		System.out.print("입금할 금액 입력 : ");
		int deposit = sc.nextInt();
		sc.nextLine();
		
		int result = bc.depositMoney(tmpOwner, tmpAccountNum, deposit);
		
		if (result > 0) {
			System.out.println("입금에 성공했습니다.");
		} else {
			System.out.println("입금에 실패했습니다.");
		}
		
	}
	
	public void withdrawMoney() {
		

		System.out.println("===== 예금 출금 =====");
		
		int money = bc.showMoney(tmpOwner, tmpAccountNum);
		System.out.println("현재 잔액은 "+money+"원 입니다.");
		
		System.out.print("출금할 금액 입력 : ");
		int withdraw = sc.nextInt();
		sc.nextLine();
		
		int result = bc.withdrawMoney(tmpOwner, tmpAccountNum, withdraw);
		
		if (result > 0) {
			System.out.println("출금에 성공했습니다.");
		} else {
			System.out.println("출금에 실패했습니다.");
		}
		
	}

	public void showMoney() {
		
		System.out.println("===== 예금 조회 =====");
		
		int money = bc.showMoney(tmpOwner, tmpAccountNum);
		
		if (money > -1) {
			System.out.println("현재 잔액은 "+money+"원 입니다.");
		} else {
			System.out.println("해당 계좌가 존재하지 않습니다.");
		}
		
	}

	public void logoutAccount() {
		
		while (true) {
			System.out.print("\n정말 로그아웃하시겠습니까?(Y/N) : ");
			char answer = sc.nextLine().toUpperCase().charAt(0);
			
			if (answer == 'Y') {
				System.out.println("로그아웃합니다.");
				tmpOwner = "";
				tmpAccountNum = "";
				System.out.println("초기 메뉴로 돌아갑니다.");
				loginMenu();
			} else if (answer == 'N') {
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
			System.out.println();
		}
	}
}
