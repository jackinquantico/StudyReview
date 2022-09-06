package com.kh.part_14.beforeJDBC.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.beforeJDBC.controller.MemberController;
import com.kh.part_14.beforeJDBC.model.vo.Member;


public class MemberView {

	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	public void mainMenu() {
		
		int count = 0;
		
		while (true) {
			
			System.out.print("������ ���̵� : ");
			String sysId = sc.nextLine();
			System.out.print("��й�ȣ : ");
			String sysPwd = sc.nextLine();
			
			if (sysId.equals("admin") && sysPwd.equals("P@s$W0rd!")) {
				break;
			} else {
				count++;
			}
			
			if (count == 5) {
				System.out.println("������ �α��� ���� 5ȸ");
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			}
			
			System.out.println("���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� �Է����ּ���.");
			
		}
		
		System.out.println();
		
		while (true) {

			System.out.println("*** �޴� ���� ***");
			System.out.println("1. ȸ�� �߰�");
			System.out.println("2. ȸ�� ��ü��ȸ");
			System.out.println("3. ȸ�� ���̵� �˻�");
			System.out.println("4. ȸ�� �̸� Ű���� �˻�");
			System.out.println("5. ȸ�� ��й�ȣ �ʱ�ȭ");
			System.out.println("6. ȸ�� ����");
			System.out.println("0. ���α׷� �α׾ƿ�");
			System.out.print("�޴� ��ȣ �Է� : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				insertMember();
				break;
			case 2:
				selectMemberList();
				break;
			case 3:
				selectMemberById();
				break;
			case 4:
				searchMemberByName();
				break;
			case 5:
				initMemberPwd();
				break;
			case 6:
				deleteMember();
				break;
			case 0:
				System.out.println("���α׷����� �α׾ƿ��մϴ�.");
				return;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
				break;
			}
			
			System.out.println();
		}
	}
	
	public void insertMember() {
		
		System.out.println("*** ȸ�� �߰� ***");
		System.out.print("���̵� �Է� : ");
		String userId = sc.nextLine();
		int result = mc.idCheck(userId);
		
		if (result > 0) {
			System.out.println("�̹� ������� ���̵��Դϴ�.");
			return;
		}
		
		System.out.print("��й�ȣ �Է� : ");
		String userPwd = sc.nextLine();
		System.out.print("�̸� �Է� : ");
		String userName = sc.nextLine();
		System.out.print("���� �Է� : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("���� �Է� : ");
		char gender = sc.nextLine().charAt(0);
		System.out.print("�̸��� �Է� : ");
		String email = sc.nextLine();
		System.out.print("�޴��� ��ȣ �Է� : ");
		String phone = sc.nextLine();
		
		int insertResult = mc.insertMember(userId, userPwd, userName, age, gender, email, phone);
		
		if (insertResult > 0) {
			System.out.println("ȸ�� �߰� ����");
		} else {
			System.out.println("ȸ�� �߰� ����");
		}
	}
	
	public void selectMemberList() {
		
		System.out.println("*** ȸ�� ��ü ��ȸ ***");
		
		ArrayList<Member> memberList = mc.selectMemberList();
		
		if (memberList.isEmpty()) {
			System.out.println("���� ��ȸ�� ȸ���� �������� �ʽ��ϴ�.");
		} else {
			for (Member m : memberList) {
				System.out.println(m);
			}
		}
		
	}

	public void selectMemberById() {
		
		System.out.println("*** ȸ�� ���̵� �˻� ***");
		System.out.print("���̵� �Է� : ");
		String userId = sc.nextLine();
		
		Member user = mc.searchMemberById(userId);
		
		if (user.equals(null)) {
			System.out.println("��ġ�ϴ� ȸ���� �������� �ʽ��ϴ�.");
		} else {
			System.out.println(user);			
		}
	}
	
	public void searchMemberByName() {
		
		System.out.println("*** ȸ�� �̸� Ű���� �˻� ***");
		System.out.print("���̵� �Է� : ");
		String keyword = sc.nextLine();
		
		ArrayList<Member> searched = mc.searchMemberbyName(keyword);
		
		if (searched.isEmpty()) {
			System.out.println("��ġ�ϴ� ȸ���� �����ϴ�.");
		} else {
			for (Member m : searched) {
				System.out.println(m);
			}
		}
	}
	
	public void initMemberPwd() {
		
		System.out.println("*** ȸ�� ��й�ȣ �ʱ�ȭ ***");
		System.out.print("���̵� �Է� : ");
		String userId = sc.nextLine();
		
		int result = mc.initMemberPwd(userId);
		
		if (result > 0) {
			System.out.println("��й�ȣ�� �ʱ�ȭ�Ǿ����ϴ�.");
		} else {
			System.out.println("�ش� ���̵� �������� �ʽ��ϴ�.");
		}
	}
	
	public void deleteMember() {
		
		System.out.println("*** ȸ�� ���� ***");
		System.out.print("���̵� �Է� : ");
		String userId = sc.nextLine();
		
		System.out.println("���� �� ������ �� �����ϴ�.");
		System.out.print("���� �����Ͻðڽ��ϱ�? : ");
		char answer = sc.nextLine().toLowerCase().charAt(0);
		
		if (answer == 'y') {
			int result = mc.deleteMember(userId);
			
			if (result > 0) {
				System.out.println("ȸ���� �����߽��ϴ�.");
			} else {
				System.out.println("�ش� ȸ���� ã�� �� �����ϴ�.");
			}
		} else {
			System.out.println("���� ȭ������ ���ư��ϴ�.");
		}
		
	}
}
