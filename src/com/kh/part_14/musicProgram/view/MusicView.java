package com.kh.part_14.musicProgram.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.musicProgram.controller.MusicController;
import com.kh.part_14.musicProgram.model.vo.Music;

public class MusicView {
	
	private Scanner sc = new Scanner(System.in);
	private MusicController mc = new MusicController();
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("=== ������ ���� ===");
			System.out.println("* 1. �α���          *");
			System.out.println("* 2. ȸ������       *");
			System.out.println("* 0. ���α׷� ���� *");
			System.out.println("================");
			System.out.print("* �޴� ��ȣ �Է� : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				loginMenu();
				break;
			case 2:
				signupMenu();
				break;
			case 0:
				System.out.println("������ ������ �����մϴ�.");
				return;
			default:
				System.out.println("�߸��� �޴� ��ȣ�Դϴ�.");
				break;
			}
			
			System.out.println();
		}
		
	}

	public void loginMenu() {
		
		System.out.print("* ���̵� �Է� : ");
		String userId = sc.nextLine();
		System.out.print("* ��й�ȣ �Է� : ");
		String userPwd = sc.nextLine();
		System.out.println("================");
		
		int result = mc.loginMenu(userId, userPwd);
		
		if (result > 0) {
			System.out.println(userId + "�� �α��� ����");
			System.out.println("���� ȭ������ �̵��մϴ�.\n");
			selectMenu();
		} else {
			System.out.println("�����ϴ� ���̵� �����ϴ�.");
		}
	}
	
	public void signupMenu() {
		
		System.out.println("* ������ ȸ��������");
		System.out.print("* ���̵� �Է� : ");
		String userId = sc.nextLine();
		System.out.print("* ��й�ȣ �Է� : ");
		String userPwd = sc.nextLine();
		
		mc.signupMenu(userId, userPwd);
		
		System.out.println("* * * * * * * *");
		System.out.println("������ ������ �� ���� �����մϴ�.");
		System.out.println("�α��� ȭ������ �̵��մϴ�.");
		System.out.println("* * * * * * * *");
		loginMenu();
	}
	
	public void selectMenu() {
		
		while (true) {
			
			System.out.println("=== ������ ���� ===");
			System.out.println("* * ������� * *");
			System.out.println("* 1. �뷡 �߰��ϱ�");
			System.out.println("* 2. �뷡 �����ϱ�");
			System.out.println("* 3. �뷡 �����ϱ�");
			System.out.println("* 4. �뷡 ��Ϻ���");
			System.out.println("* 0. ���α׷� ����");
			System.out.println("================");
			System.out.print("* �޴� ��ȣ �Է� : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			System.out.println();
			
			switch (menu) {
			case 1:
				insertMusic();
				break;
			case 2:
				deleteMusic();
				break;
			case 3:
				updateMusic();
				break;
			case 4:
				selectAll();
				break;
			case 0:
				System.out.println("������ ������ �����մϴ�.");
				System.out.println("������ �� ���ּ��� ����");
				System.exit(0);
			default:
				System.out.println("�߸��� �޴� ��ȣ�Դϴ�.");
				break;
			}
			
			System.out.println();
		}
	}
	
	public void insertMusic() {
		
		System.out.println("================");
		System.out.println("* * �뷡 �߰� * *");
		System.out.print("* �뷡 ���� �Է� : ");
		String title = sc.nextLine();
		System.out.print("* ���� �̸� �Է� : ");
		String singer = sc.nextLine();
		System.out.print("* �뷡 �帣 �Է� : ");
		String genre = sc.nextLine();
		
		int result = mc.insertMusic(title, singer, genre);
		
		if (result > 0) {
			System.out.println("* �뷡 �߰� ����!");
		} else {
			System.out.println("* �뷡 �߰� ����..");
		}
	}

	public void deleteMusic() {
		
		System.out.println("================");
		System.out.println("* * �뷡 ���� * *");
		System.out.print("* �뷡 ���� �Է� : ");
		String title = sc.nextLine();
		System.out.print("* ���� �̸� �Է� : ");
		String singer = sc.nextLine();
		
		int result = mc.deleteMusic(title, singer);
		
		if (result > 0) {
			System.out.println("* �뷡 ���� ����!");
		} else {
			System.out.println("* �뷡 ���� ����..");
		}
	}
	
	public void updateMusic() {
		
		System.out.println("================");
		System.out.println("* * �뷡 �߰� * *");
		System.out.print("* �ٲ� �뷡 ���� : ");
		String title = sc.nextLine();
		System.out.print("* �ٲ� �뷡 ���� : ");
		String singer = sc.nextLine();
		System.out.print("* �뷡 ���� �Է� : ");
		String uptitle = sc.nextLine();
		System.out.print("* ���� �̸� �Է� : ");
		String upsinger = sc.nextLine();
		System.out.print("* �뷡 �帣 �Է� : ");
		String upgenre = sc.nextLine();
		
		int result = mc.updateMusic(title, singer, uptitle, upsinger, upgenre);
		
		if (result > 0) {
			System.out.println("* �뷡 ���� ����!");
		} else {
			System.out.println("* �뷡 ���� ����..");
		}
	}
	
	public void selectAll() {
		
		System.out.println("================");
		System.out.println("* * ��� ���� * *");
		
		ArrayList<Music> list = mc.selectAll();
		
		if (list.isEmpty()) {
			System.out.println("����� ����ִ���");
		} else {
			for (Music m : list) {
				System.out.println(m);
			}
		}
		
	}
}
