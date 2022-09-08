package com.kh.part_15.jdbc.statement.view;

import java.util.Scanner;

import com.kh.part_15.jdbc.statement.controller.MemberController;


public class MemberView {

	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	/**
	 * ����ڰ� ���� �� ù ȭ�� (����)
	 */
	public void mainMenu() {
		
		while (true) {
			System.out.println("===== ȸ�� ���� ���α׷� =====");
			System.out.println("1. ȸ�� �߰�");
			System.out.println("2. ȸ�� ��ü ��ȸ");
			System.out.println("3. ȸ�� ���̵�� �˻�");
			System.out.println("4. ȸ�� �̸� Ű���� �˻�");
			System.out.println("5. ȸ�� ���� ����");
			System.out.println("6. ȸ�� Ż��");
			System.out.println("0. ���α׷� ����");
			System.out.println("-------------------------");
			System.out.print(">> �̿��� �޴� ���� : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				insertMember();
				break;
			case 0:
				System.out.println("���α׷� ����");
				return;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
		}
	}
	
	public void insertMember() {
		
		System.out.println("----- ȸ�� �߰� -----");
		
		// ȸ�� �߰��� �ʿ��� �����͵�
		// ���̵�, ��й�ȣ, �̸�, ����, ����, �̸���, �޴���, �ּ�, ��� (�� 9��)
		System.out.print("���̵� : ");
		String userId = sc.nextLine();
		
		System.out.print("��й�ȣ : ");
		String userPwd = sc.nextLine();
		
		System.out.print("�̸� : ");
		String userName = sc.nextLine();
		
		System.out.print("���� (M/F) : ");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("���� : ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("�̸��� : ");
		String email = sc.nextLine();
		
		System.out.print("�޴��� (���ڸ�) : ");
		String phone = sc.nextLine();
		
		System.out.print("�ּ� : ");
		String address = sc.nextLine();
		
		System.out.print("��� (, �� ���� ���� ����) : ");
		String hobby = sc.nextLine();
		
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
	}
}
