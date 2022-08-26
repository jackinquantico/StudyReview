package com.kh.part_14.depositSystem.view;

import java.util.Scanner;

import com.kh.part_14.depositSystem.controller.BankController;

public class BankView {

	private Scanner sc = new Scanner(System.in);
	private BankController bc = new BankController();
	private String tmpOwner = "";
	private String tmpAccountNum = "";
	
	// LOGIN �޴� ���� ���� owner, accountNum �Է¹ް�
	// mainMenu �����ϵ����ϱ�
	public void loginMenu() {
		
		while (true) {
			System.out.println("==================");
			System.out.println("===== �츮 ���� =====");
			System.out.println("1. ������ �α���");
			System.out.println("0. ���α׷� ����");
			System.out.println("==================");
			System.out.print("�޴� ��ȣ �Է� : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				loginAccount();
				break;
			case 0:
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
		}
	}
	
	public void loginAccount() {
		
		System.out.println("===== �α��� �޴� =====");
		System.out.print("������ �� �Է� : ");
		String owner = sc.nextLine();
		System.out.print("���¹�ȣ �Է� : ");
		String accountNum = sc.nextLine();
		
		int result = bc.loginAccount(owner, accountNum);
		
		if (result > 0) {
			tmpOwner = owner;
			tmpAccountNum = accountNum;
			System.out.println("�α��ο� �����߽��ϴ�.");
			System.out.println("���� �޴��� �̵��մϴ�.");
			System.out.println();
			mainMenu();
		} else {
			System.out.println("�α��ο� �����߽��ϴ�.");
			System.out.println("���� �޴��� �̵��մϴ�.");
			return;
		}
	}
	
	public void mainMenu() {
		
		while (true) {
			System.out.println("==================");
			System.out.println("===== �츮 ���� =====");
			System.out.println("1. ���� �Ա�");
			System.out.println("2. ���� ���");
			System.out.println("3. �ܾ� ��ȸ");
			System.out.println("4. ���� �α׾ƿ�");
			System.out.println("0. ���α׷� ����");
			System.out.println("==================");
			System.out.print("�޴� ��ȣ �Է� : ");
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
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
			
			System.out.println();
		}
	}
	
	public void depositMoney() {
		
		System.out.println("===== ���� �Ա� =====");
		
		int money = bc.showMoney(tmpOwner, tmpAccountNum);
		System.out.println("���� �ܾ��� "+money+"�� �Դϴ�.");
		
		System.out.print("�Ա��� �ݾ� �Է� : ");
		int deposit = sc.nextInt();
		sc.nextLine();
		
		int result = bc.depositMoney(tmpOwner, tmpAccountNum, deposit);
		
		if (result > 0) {
			System.out.println("�Աݿ� �����߽��ϴ�.");
		} else {
			System.out.println("�Աݿ� �����߽��ϴ�.");
		}
		
	}
	
	public void withdrawMoney() {
		

		System.out.println("===== ���� ��� =====");
		
		int money = bc.showMoney(tmpOwner, tmpAccountNum);
		System.out.println("���� �ܾ��� "+money+"�� �Դϴ�.");
		
		System.out.print("����� �ݾ� �Է� : ");
		int withdraw = sc.nextInt();
		sc.nextLine();
		
		int result = bc.withdrawMoney(tmpOwner, tmpAccountNum, withdraw);
		
		if (result > 0) {
			System.out.println("��ݿ� �����߽��ϴ�.");
		} else {
			System.out.println("��ݿ� �����߽��ϴ�.");
		}
		
	}

	public void showMoney() {
		
		System.out.println("===== ���� ��ȸ =====");
		
		int money = bc.showMoney(tmpOwner, tmpAccountNum);
		
		if (money > -1) {
			System.out.println("���� �ܾ��� "+money+"�� �Դϴ�.");
		} else {
			System.out.println("�ش� ���°� �������� �ʽ��ϴ�.");
		}
		
	}

	public void logoutAccount() {
		
		while (true) {
			System.out.print("\n���� �α׾ƿ��Ͻðڽ��ϱ�?(Y/N) : ");
			char answer = sc.nextLine().toUpperCase().charAt(0);
			
			if (answer == 'Y') {
				System.out.println("�α׾ƿ��մϴ�.");
				tmpOwner = "";
				tmpAccountNum = "";
				System.out.println("�ʱ� �޴��� ���ư��ϴ�.");
				loginMenu();
			} else if (answer == 'N') {
				System.out.println("���� �޴��� ���ư��ϴ�.");
				return;
			} else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
			
			System.out.println();
		}
	}
}
