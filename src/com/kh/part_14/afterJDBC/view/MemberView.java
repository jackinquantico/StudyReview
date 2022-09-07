package com.kh.part_14.afterJDBC.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.afterJDBC.controller.MemberController;
import com.kh.part_14.afterJDBC.model.vo.Member;

/*
 * * View
 * ����ڰ� ���� �� �ð����� ���, ȭ��
 * 
 * - CLI : Command Line Interface (�ܼ��� Ű���常���� ��ǻ�Ϳ� ������ �� �ִ� ȯ��)
 * 		     ��¹� (println, print, ..), �Է¹� (Scanner)
 * 
 * - GUI : Graphic User Interface (���콺, Ű���带 ��� �̿��ؼ� ��ǻ�Ϳ� ������ �� �ִ� ȯ��)
 * 		   html �±�
 */

public class MemberView {
	
	// ���������� ��ĳ�� ��ü ����
	private Scanner sc = new Scanner(System.in);
	
	// ����������  MemberController ��ü ����
	private MemberController mc = new MemberController();
	
	// �޼ҵ� �ּ� ����Ű : alt + shift + j
	/**
	 * ����ڰ� ���� �� ù ȭ�� (����ȭ��)
	 */
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("***** ȸ�� ���� ���α׷� *****");
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
				insertMember(); // ȸ�� �߰��� ȭ�� ��� �޼ҵ�
				break;
			case 2:
				selectAll(); // ȸ�� ��ü��ȸ�� ȭ�� ��� �޼ҵ�
				break;
			case 3:
				selectByUserId(); // ȸ�� ���̵� �˻��� ȭ�� ��� �޼ҵ�
				break;
			case 4:
				selectByUserName(); // ȸ�� �̸� Ű���� �˻��� ȭ�� ��� �޼ҵ�
				break;
			case 5:
				updateMember(); // ȸ�� ���� ����� ȭ�� ��� �޼ҵ�
				break;
			case 6:
				deleteMember(); // ȸ�� ���� ������ ȭ�� ��� �޼ҵ�
				break;
			case 0:
				System.out.println("���α׷��� �����մϴ�.");
				return;
			default:
				System.out.println("��ȣ�� �߸� �Է��߽��ϴ�. �ٽ� �Է����ּ���.");
			}
		
			System.out.println();
		}
	} // mainMenu
	
	/**
	 * ȸ�� �߰��� ȭ��
	 * �߰��ϰ��� �ϴ� ȸ���� ������ �Է¹޾Ƽ� �߰� ��û�� �� �� �ִ� ȭ��
	 */
	public void insertMember() {
		
		System.out.println("----- ȸ�� �߰� -----");
		
		// ȸ�� �߰� --> MEMBER ���̺� INSERT �ϰڴ�
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
		
		// ȸ�� �߰� ��û => MemberController Ŭ������ �޼ҵ� ȣ��
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
	} // insertMember()
	
	/**
	 * ȸ�� ��ü ��ȸ�� ȭ��
	 */
	public void selectAll() {
		
		System.out.println("----- ȸ�� ��ü ��ȸ -----");
		
		// ȸ�� ��ü ��ȸ ��û => MemberController Ŭ������
		mc.selectAll();
		
	}
	
	/**
	 * ȸ�� ���̵� �˻��� ȭ��
	 * ����ڿ��� �˻��� ȸ���� ���̵� �Է¹��� �� ��ȸ�� ��û�ϴ� ȭ��
	 */
	public void selectByUserId() {
		
		System.out.println("----- ȸ�� ���̵�� �˻� -----");
		
		// ����ڷκ��� �˻��ϰ��� �ϴ� ȸ���� ���̵� �Է¹޾ƾ� ��
		System.out.print("�˻��� ȸ���� ���̵� : ");
		String userId = sc.nextLine();
		
		// �Է��� ���̵� ȸ�� ���̵� �˻� ��û�� �Ű������� �Ѱ��� => Controller Ŭ������ ��û
		mc.selectByUserId(userId);
				
	}
	
	/**
	 * ȸ�� �̸� Ű���� �˻��� ȭ��
	 * ����ڿ��� �˻��� ȸ���� �̸� Ű���带 �Է¹��� �� ��ȸ�� ��û�ϴ� ȭ��
	 */
	public void selectByUserName() {
		
		System.out.println("----- ȸ�� �̸� Ű���� �˻� -----");
		
		// ����ڷκ��� �˻��ϰ��� �ϴ� ȸ�� �̸� Ű���� �Է¹޾ƾ� �� (��ġ X, Ű���� ���� O)
		System.out.print("ȸ�� �̸� Ű���� �Է� : ");
		String keyword = sc.nextLine();
		
		// �Է¹��� Ű���带 ȸ�� �̸� �˻� ��û�� �Ű������� �Ѱ��� => Controller Ŭ������ ��û
		mc.selectByUserName(keyword);
		
	}
	
	/**
	 * ����ڿ��� ������ ȸ���� ���̵�, ������ ������(��й�ȣ, �̸���, ��ȭ��ȣ, �ּ�) �� �Է¹��� ��
	 * ������ ��û�ϴ� ȭ��
	 */
	public void updateMember() {
		
		System.out.println("----- ȸ������ ���� -----");
		
		// ��� �����͸� ��� ������ ������ ���
		// ��� ȸ���� => unique ���������� �ɸ� ȸ���� ���̵�� ����
		// ��� ������ ������ => ��й�ȣ, �̸���, ��ȭ��ȣ, �ּ�
		
		// ������ ȸ���� ���̵�
		System.out.print("������ ȸ���� ���̵� : ");
		String userId = sc.nextLine();
		
		// ������ �����
		System.out.print("������ ��й�ȣ : ");
		String newPwd = sc.nextLine();
		
		System.out.print("������ �̸��� : ");
		String newEmail = sc.nextLine();
		
		System.out.print("������ ��ȭ��ȣ (���ڸ�) : ");
		String newPhone = sc.nextLine();
		
		System.out.print("������ �ּ� : ");
		String newAddress = sc.nextLine();
		
		// MemberController �� �޼ҵ� ȣ���Ͽ� ȸ�� ���� ���� ��û
		mc.updateMember(userId, newPwd, newEmail, newPhone, newAddress);
	}
	
	/**
	 * ����ڿ��� Ż���� ȸ���� ���̵� �Է¹޾� ������ ��û�ϴ� ȭ��
	 */
	public void deleteMember() {
		
		System.out.println("----- ȸ�� Ż��  -----");
		
		// Ż���ϰ��� �ϴ� ȸ���� ���̵� �Է¹ޱ� => unique �������ǿ� ���� ȸ�� ������ �� �ִ� �ĺ���
		System.out.print("Ż���� ȸ�� ���̵� : ");
		String userId = sc.nextLine();
		
		mc.deleteMember(userId);
		
	}
	
	// ---------------------------------------------------
	// ���� ��û ó�� �� ����ڰ� �� ���� ȭ�鿡 ���� �޼ҵ��
	
	/**
	 * ���� ��û ���� �� ���� �� ȭ��
	 * @param message : ���� �޽���
	 */
	public void displaySuccess(String message) {
		
		System.out.println("���� ��û ���� : " + message);
	}
	
	/**
	 * ���� ��û ���� �� ���� �� ȭ��
	 * @param message : ���� �޽���
	 */
	public void displayFail(String message) {
		
		System.out.println("���� ��û ���� : " + message);
	}
	
	/**
	 * ��ȸ ���� ��û �� ��ȸ ����� ���� �� ���� �� ȭ��
	 * @param message : ����� ������ ��Ÿ���� �޽���
	 */
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
	
	/**
	 * ��ȸ ���� ��û �� ���� ���� ��ȸ�� ����� �޾Ƽ� ���� �� ȭ��
	 * @param list : ���� ���� ��ȸ�� �����
	 */
	public void displayList(ArrayList<Member> list) {
		
		System.out.println("��ȸ�� �����ʹ� ������ �����ϴ�.");
		
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	/**
	 * ��ȸ ���� ��û �� �� ���� ��ȸ�� ����� �޾Ƽ� ���� �� ȭ��
	 * @param m : �� ���� ��ȸ�� �����
	 */
	public void displayOne(Member m) {
		
		System.out.println("��ȸ�� �����ʹ� ������ �����ϴ�.");
		System.out.println(m);
	}
	
}