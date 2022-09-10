package com.kh.part_15.jdbc.statement.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_15.jdbc.statement.controller.MemberController;
import com.kh.part_15.jdbc.statement.model.vo.Member;


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
				System.out.println("���α׷� ����");
				return;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
			
			System.out.println();
		}
	}
	
	/**
	 * ������� ȸ�� �߰� ��û�� ���� �� ȭ��
	 */
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

	/**
	 * ������� ȸ�� ��ü ��ȸ ��û�� ���� �� ȭ��
	 */
	public void selectAll() {
		
		System.out.println("----- ȸ�� ��ü ��ȸ -----");
		
		mc.selectAll();
	}
	
	/**
	 * ������� ȸ�� ���̵�� �˻� ��û �� ���� �� ȭ��
	 * �˻��� ���̵� �Է¹޾ƾ� �Ѵ�.
	 */
	public void selectByUserId() {
		
		System.out.println("----- ȸ�� ���̵�� �˻� -----");
		
		System.out.print("�˻��� ���̵� �Է� : ");
		String userId = sc.nextLine();
		
		mc.selectByUserId(userId);
	}
	
	/**
	 * ������� �̸� Ű����� �˻� ��û �� ���� �� ȭ��
	 * �˻��� Ű���� �Է¹ޱ�
	 */
	public void selectByUserName() {
		
		System.out.println("----- �̸� Ű����� �˻� -----");
		
		System.out.print("�̸� Ű���� : ");
		String keyword = sc.nextLine();
		
		mc.selectByUserName(keyword);
	}
	
	public void updateMember() {
		
		System.out.println("----- ȸ�� ���� ���� -----");
		
		System.out.print("������ ȸ�� ���̵� : ");
		String userId = sc.nextLine();
		
		System.out.print("������ ��й�ȣ �Է� : ");
		String userPwd = sc.nextLine();
		
		System.out.print("������ �̸��� �Է� : ");
		String email = sc.nextLine();
		
		System.out.print("������ �ּ� �Է� : ");
		String address = sc.nextLine();
		
		System.out.print("������ ��� �Է� : ");
		String hobby = sc.nextLine();
		
		mc.updateMember(userId, userPwd, email, address, hobby);
		
	}
	
	public void deleteMember() {
		
		System.out.println("----- ȸ�� Ż�� -----");
		
		System.out.print("Ż���� ȸ�� ���̵� : ");
		String userId = sc.nextLine();
		
		mc.deleteMember(userId);
	}
	
	// --------------------------------------------------
	
	/**
	 * ���� ��û ���� �� ���� �� ȭ��
	 * @param message : ���� �޽���
	 */
	public void displaySuccess(String message) {
		
		System.out.println("���� ��û ���� : "+message);
	}
	
	/**
	 * ���� ��û ���� �� ���� �� ȭ��
	 * @param message : ���� �޽���
	 */
	public void displayFail(String message) {
		
		System.out.println("���� ��û ���� : "+message);
	}
	
	/**
	 * ��ȸ ���� ��û �� ���� ���� ��ȸ�� ����� ������ ȭ��
	 * @param list : ���޹��� ��ȸ ���
	 */
	public void displayList(ArrayList<Member> list) {
		
		System.out.println("��ȸ�� ����� ������ �����ϴ�.");
		
		for (Member m : list) {
			System.out.println(m);
		}
	}
	
	/**
	 * ��ȸ ���� ��û �� �� ���� ��ȸ�� ����� ������ ȭ��
	 * @param m : ��ȸ�� �� ���� ������
	 */
	public void displayOne(Member m) {
		
		System.out.println("��ȸ�� ����� ������ �����ϴ�.");
		System.out.println(m);
	}
	
	/**
	 * ��ȸ ���� ��û �� ��ȸ ����� ���� �� ������ ȭ��
	 * @param message : ����� ������ ��Ÿ���� �޽���
	 */
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
}
