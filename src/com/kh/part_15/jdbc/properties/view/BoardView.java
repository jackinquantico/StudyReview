package com.kh.part_15.jdbc.properties.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_15.jdbc.properties.controller.BoardController;
import com.kh.part_15.jdbc.properties.model.vo.Board;

import oracle.net.aso.s;

public class BoardView {

	private Scanner sc = new Scanner(System.in);
	private BoardController bc = new BoardController();
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("======== ���� ���� �Խ��� ========");
			selectAll();
			System.out.println("============================");
			System.out.println("1. �Խñ� �ۼ��ϱ�");
			System.out.println("2. �Խñ� �������� �˻�");
			System.out.println("3. �Խñ� �ۼ��ڷ� �˻�");
			System.out.println("4. �Խñ� �������� �˻�");
			System.out.println("5. �Խñ� �ۼ��Ϸ� �˻�");
			System.out.println("6. ���� �Խñ� ����");
			System.out.println("7. �Խñ� ���뺸��");
			System.out.println("8. �Խñ� �����ϱ�");
			System.out.println("9. �Խñ� �����ϱ�");
			System.out.println("0. �Խ��� �����ϱ�");
			System.out.println("============================");
			System.out.print(">> �޴� ��ȣ �Է� : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				insertBoard();
				break;
			case 2:
				selectByTitle();
				break;
			case 3:
				selectByUserId();
				break;
			case 4:
				selectByContent();
				break;
			case 5:
				selectByCreateDate();
				break;
			case 6:
				selectRandom();
				break;
			case 7:
				selectBoard();
				break;
			case 8:
				updateBoard();
				break;
			case 9:
				deleteBoard();
				break;
			case 0:
				System.out.println("�Խ����� �����մϴ�.");
				return;
			default:
				System.out.println("�߸��� �޴��Դϴ�. �ٽ� �Է����ּ���.");
			}
			
			System.out.println();
		}
	}
	
	public void selectAll() {
		
		bc.selectAll();
	}
	
	public void insertBoard() {
		
		System.out.println("----- �Խñ� �ۼ��ϱ� -----");
		System.out.print("�Խñ� ���� : ");
		String title = sc.nextLine();
		
		System.out.print("�Խñ� ���� : ");
		String content = sc.nextLine();
		
		System.out.print("�ۼ��� : ");
		String writer = sc.nextLine();
		
		bc.insertBoard(title, content, writer);
	}

	public void selectByTitle() {
		
		System.out.println("----- �Խñ� ���� Ű���� �˻� -----");
		
		System.out.print("���� Ű���� �Է� : ");
		String keyword = sc.nextLine();
		
		bc.selectByTitle(keyword);
	}
	
	public void selectByUserId() {
		
		System.out.println("----- �ۼ��� �˻� -----");
		
		System.out.print("�ۼ��� ���̵� �Է� : ");
		String userId = sc.nextLine();
		
		bc.selectByUserId(userId);
	}
	
	public void selectByContent() {
		
		System.out.println("----- �Խñ� ���� �˻� -----");
		
		System.out.print("�Խñ� Ű���� �Է� : ");
		String keyword = sc.nextLine();
		
		bc.selectByContent(keyword);
	}
	
	public void selectByCreateDate() {
		
		System.out.println("----- �Խñ� ��¥�� �˻� -----");
		
		System.out.print("�ۼ��� �Է� (MMDD) : ");
		String createDate = sc.nextLine();
		
		bc.selectByCreateDate(createDate);
	}
	
	public void selectRandom() {
		
		System.out.println("----- ���� �Խñ� -----");
		
		bc.selectRandom();
	}
	
	public void selectBoard() {
		
		System.out.println("----- �Խñ� ���뺸�� -----");
		
		System.out.print("�Խñ� ��ȣ �Է� : ");
		int bNo = sc.nextInt();
		sc.nextLine();
		
		bc.selectBoard(bNo);
	}
	
	public void updateBoard() {
		
		System.out.println("----- �Խñ� ���� -----");
		
		System.out.print("������ �Խñ� ��ȣ : ");
		int bNo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("������ ���� : ");
		String title = sc.nextLine();
		System.out.print("������ ���� : ");
		String content = sc.nextLine();
		
		bc.updateBoard(bNo, title, content);
	}
	
	public void deleteBoard() {
		
		System.out.println("----- �Խñ� ���� -----");
		
		System.out.print("������ �Խñ� ��ȣ : ");
		int bNo = sc.nextInt();
		sc.nextLine();
		
		bc.deleteBoard(bNo);
	}
	
	//----------------------------
	
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
	
	public void displayList(ArrayList<Board> list) {
		
		System.out.println("��ȸ�� ��� : ");
		
		for (Board b : list) {
			System.out.println(b);
		}
	}
	
	public void displayOne(Board b) {
		
		System.out.println(b);
		System.out.println("�� ���� : "+b.getContent());
	}
	
	public void displaySuccess(String message) {
		
		System.out.println("���� ��û ���� : "+message);
	}
	
	public void displayFail(String message) {
		System.out.println("���� ��û ���� : "+message);
	}
	
	public void displayRandom(ArrayList<Board> list) {
		
		int r = (int)(Math.random() * list.size());
		
		for (int i=0; i<list.size(); i++) {
			if (i == r) {
				System.out.println(list.get(i));
			}
		}
	}
}
