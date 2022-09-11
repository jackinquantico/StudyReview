package com.kh.part_15.jdbc.preparedStatement.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_15.jdbc.preparedStatement.controller.PreController;
import com.kh.part_15.jdbc.preparedStatement.model.vo.PreMember;

public class PreView {
	
	private Scanner sc = new Scanner(System.in);
	private PreController pc = new PreController();
	
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
			case 2:
				selectAll();
				break;
			case 3:
				selectByUserId();
				break;
			case 4:
				selectByUserName();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
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
	
	public void insertMember() {
		
		System.out.println("----- ȸ�� �߰� -----");
		
		System.out.print("���̵� �Է� : ");
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
		
		pc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
	}

	public void selectAll() {
		
		System.out.println("----- ��ü ��ȸ -----");
		
		pc.selectAll();
	}
	
	public void selectByUserId() {
		
		System.out.println("----- ���̵�� �˻� -----");
		
		System.out.print("���̵� �Է� : ");
		String userId = sc.nextLine();
		
		pc.selectByUserId(userId);
	}
	
	public void selectByUserName() {
		
		System.out.println("----- �̸� Ű���� �˻� -----");
		
		System.out.print("Ű���� �Է� : ");
		String keyword = sc.nextLine();
		
		pc.selectByUserName(keyword);
	}
	
	public void updateMember() {
		
		System.out.println("----- ȸ�� ���� ���� -----");
		
		System.out.print("������ ȸ�� ���̵� �Է� : ");
		String userId = sc.nextLine();
		
		System.out.print("������ ��й�ȣ �Է� : ");
		String userPwd = sc.nextLine();
		
		System.out.print("������ �̸��� �Է� : ");
		String email = sc.nextLine();
		
		System.out.print("������ �ڵ��� ��ȣ �Է� : ");
		String phone = sc.nextLine();
		
		System.out.print("������ �ּ� �Է� : ");
		String address = sc.nextLine();
		
		System.out.print("������ ��� �Է� : ");
		String hobby = sc.nextLine();
		
		pc.updateMember(userId, userPwd, email, phone, address, hobby);
	}
	
	public void deleteMember() {
		
		System.out.println("----- ȸ�� Ż�� -----");
		
		System.out.print("Ż���� ȸ�� ���̵� �Է� : ");
		String userId = sc.nextLine();
		
		pc.deleteMember(userId);
	}
	
	// --------------------------------------
	
	public void displaySuccess(String message) {
		
		System.out.println("���� ��û ��� : "+message);
	}
	
	public void displayFail(String message) {
		
		System.out.println("���� ��û ��� : "+message);
	}
	
	public void displayList(ArrayList<PreMember> list) {
		
		for (PreMember p : list) {
			System.out.println(p);
		}
	}
	
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
	
	public void displayOne(PreMember m) {
		
		System.out.println("��ȸ�� ����Դϴ�.");
		System.out.println(m);
	}
}
