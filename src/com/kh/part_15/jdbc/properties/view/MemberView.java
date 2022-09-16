package com.kh.part_15.jdbc.properties.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_15.jdbc.properties.controller.MemberController;
import com.kh.part_15.jdbc.properties.model.vo.Member;

public class MemberView {

	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	public void mainMenu() {
		
		while (true) {
		
			System.out.println("----- ȸ�� ���� ���α׷� -----");
			System.out.println("1. ȸ�� �߰�");
			System.out.println("2. ȸ�� ��ü ��ȸ");
			System.out.println("3. ȸ�� ���̵�� �˻�");
			System.out.println("4. ȸ�� �̸� Ű���� �˻�");
			System.out.println("5. ȸ�� ���� ����");
			System.out.println("6. ȸ�� Ż��");
			System.out.println("7. ȸ�������Ϸ� �˻�");
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
			case 7:
				selectByEnrollDate();
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
		
		System.out.print("ȸ�� ���̵� �Է� : ");
		String userId = sc.nextLine();
		System.out.print("ȸ�� ��й�ȣ �Է� : ");
		String userPwd = sc.nextLine();
		System.out.print("ȸ�� �̸� �Է� : ");
		String userName = sc.nextLine();
		System.out.print("ȸ�� ���� �Է� : ");
		String gender = sc.nextLine().toUpperCase();
		System.out.print("ȸ�� ���� �Է� : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("ȸ�� �̸��� �Է� : ");
		String email = sc.nextLine();
		System.out.print("ȸ�� �ڵ��� ��ȣ �Է� : ");
		String phone = sc.nextLine();
		System.out.print("ȸ�� �ּ� �Է� : ");
		String address = sc.nextLine();
		System.out.print("ȸ�� ��� �Է� : ");
		String hobby = sc.nextLine();
		
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);		
	}

	public void selectAll() {
		
		System.out.println("----- ȸ�� ��ü ��ȸ -----");
		
		mc.selectAll();
	}
	
	public void selectByUserId() {
		
		System.out.println("----- ȸ�� ���̵�� �˻� -----");
		
		System.out.print("ȸ�� ���̵� �Է� : ");
		String userId = sc.nextLine();
		
		mc.selectByUserId(userId);
	}
	
	public void selectByUserName() {
		
		System.out.println("----- �̸� Ű����� �˻� -----");
		
		System.out.print("�̸� Ű���� �Է� : ");
		String keyword = sc.nextLine();
		
		mc.selectByUserName(keyword);
	}
	
	public void updateMember() {
		
		System.out.println("----- ȸ�� ���� ���� -----");
		
		System.out.print("������ ȸ�� ���̵� �Է� : ");
		String userId = sc.nextLine();
		
		System.out.print("������ ��й�ȣ : ");
		String userPwd = sc.nextLine();
		
		System.out.print("������ �̸��� : ");
		String email = sc.nextLine();
		
		System.out.print("������ �ڵ��� ��ȣ : ");
		String phone = sc.nextLine();
		
		mc.updateMember(userId, userPwd, email, phone);
	}
	
	public void deleteMember() {
		
		System.out.println("----- ȸ�� Ż�� -----");
		
		System.out.print("������ ȸ�� ���̵� �Է� : ");
		String userId = sc.nextLine();
		
		System.out.print("������ �����Ͻðڽ��ϱ�? (Y/N) : ");
		char answer = sc.nextLine().toUpperCase().charAt(0);
		
		if (answer == 'Y') {
			mc.deleteMember(userId);
		} else {
			System.out.println("������ ����ϰ� ���� �޴��� ���ư��ϴ�.");
		}
	}
	
	public void selectByEnrollDate() {
		
		System.out.println("----- ȸ�������Ϸ� ��ȸ -----");
		System.out.print("ȸ���������� �Է��Ͻÿ� (MMDD) : ");
		String enrollDate = sc.nextLine();
		
		mc.selectByEnrollDate(enrollDate);
	}
	
	//---------------------------------------
	
	public void displaySuccess(String message) {
		
		System.out.println("���� ��û ���� : "+message);
	}
	
	public void displayFail(String message) {
		
		System.out.println("���� ��û ���� : "+message);
	}
	
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
	
	public void displayList(ArrayList<Member> list) {
	
		System.out.println("��ȸ�� ��� �Դϴ�.");
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public void displayOne(Member m) {
		
		System.out.println("��ȸ�� ��� �Դϴ�.");
		System.out.println(m);
	}
	
}
