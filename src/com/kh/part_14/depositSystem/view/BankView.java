package com.kh.part_14.depositSystem.view;

import java.util.Scanner;

import com.kh.part_14.depositSystem.controller.BankController;

public class BankView {

	private Scanner sc = new Scanner(System.in);
	private BankController bc = new BankController();
	
	public void mainMenu() {
		
		while (true) {
			System.out.println("==================");
			System.out.println("===== �츮 ���� =====");
			System.out.println("1. ���� �Ա�");
			System.out.println("2. ���� ���");
			System.out.println("3. �ܾ� ��ȸ");
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
				System.out.println("===== ���� ��ȸ =====");
				showMoney();
				break;
			case 0:
				System.out.println("���α׷��� �����մϴ�.");
				return;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
			
			System.out.println();
		}
	}
	
	public void depositMoney() {
		
		System.out.println("===== ���� �Ա� =====");
		System.out.print("������ �� �Է� : ");
		String owner = sc.nextLine();
		System.out.print("���¹�ȣ �Է� : ");
		String accountNum = sc.nextLine();
		
		int money = bc.showMoney(owner, accountNum);
		
		if (money > -1) {
			System.out.println("���� �ܾ��� "+money+"�� �Դϴ�.");
		} else {
			System.out.println("�ش� ���°� �������� �ʽ��ϴ�.");
		}
		
		System.out.print("�Ա��� �ݾ� �Է� : ");
		int deposit = sc.nextInt();
		sc.nextLine();
		
		int result = bc.depositMoney(owner, accountNum, deposit);
		
		if (result > 0) {
			System.out.println("�Աݿ� �����߽��ϴ�.");
		} else {
			System.out.println("�Աݿ� �����߽��ϴ�.");
		}
		
	}
	
	public void withdrawMoney() {
		

		System.out.println("===== ���� ��� =====");
		System.out.print("������ �� �Է� : ");
		String owner = sc.nextLine();
		System.out.print("���¹�ȣ �Է� : ");
		String accountNum = sc.nextLine();
		
		int money = bc.showMoney(owner, accountNum);
		
		if (money > -1) {
			System.out.println("���� �ܾ��� "+money+"�� �Դϴ�.");
		} else {
			System.out.println("�ش� ���°� �������� �ʽ��ϴ�.");
		}
		
		System.out.print("����� �ݾ� �Է� : ");
		int withdraw = sc.nextInt();
		sc.nextLine();
		
		int result = bc.withdrawMoney(owner, accountNum, withdraw);
		
		if (result > 0) {
			System.out.println("�Աݿ� �����߽��ϴ�.");
		} else {
			System.out.println("�Աݿ� �����߽��ϴ�.");
		}
		
	}

	public void showMoney() {
		
		System.out.print("������ �� �Է� : ");
		String owner = sc.nextLine();
		System.out.print("���¹�ȣ �Է� : ");
		String accountNum = sc.nextLine();
		
		int money = bc.showMoney(owner, accountNum);
		
		if (money > -1) {
			System.out.println("���� �ܾ��� "+money+"�� �Դϴ�.");
		} else {
			System.out.println("�ش� ���°� �������� �ʽ��ϴ�.");
		}
		
	}

}
