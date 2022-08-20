package com.kh.part_14.bookManagement.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.bookManagement.controller.BookManager;
import com.kh.part_14.bookManagement.model.vo.Book;

// view
public class BookMenu {
	
	private Scanner sc = new Scanner(System.in);
	private BookManager bm = new BookManager();
	
	public BookMenu() {
		
	}
	
	public void mainMenu() {
		
		while (true) {
			System.out.println("*** ���� ���� ���α׷� ***");
			System.out.println(" 1. �� ���� �߰�");
			System.out.println(" 2. ���� ����");
			System.out.println(" 3. ���� �˻� ���");
			System.out.println(" 4. ��ü ���");
			System.out.println(" 0. �����ϱ�");
			
			System.out.print("�޴� ��ȣ ���� : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			switch (num) {
			case 1:
				insertBook();
				break;
			case 2:
				deleteBook();
				break;
			case 3:
				searchBook();
				break;
			case 4:
				selectList();
				break;
			case 0:
				System.out.println("���α׷� ����");
				return;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
			
			System.out.println();
		} // while
		
	} // mainMenu
	
	public void insertBook() {
		
		System.out.print("���� ���� : ");
		String title = sc.nextLine();
		
		System.out.print("���� �帣 (1:�ι� /2:�ڿ����� /3:�Ƿ� /4:��Ÿ) : ");
		int category = sc.nextInt();
		sc.nextLine();
		
		System.out.print("���� ���� : ");
		String author = sc.nextLine();
		
		Book book = new Book(title, category, author);
		
		// �߰� ��û -> BookManager Controller Ŭ��������
		int result = bm.insertBook(book);
		
		if (result > 0) {
			System.out.println("���������� �߰��Ǿ����ϴ�.");
		} else {
			System.out.println("�߰� ����");
		}
	}
	
	public void deleteBook() {
		
		System.out.print("���� ��ȣ : ");
		int bNo = sc.nextInt();
		
		// ���� ��û
		int result = bm.deleteBook(bNo);
		
		if (result > 0) {
			System.out.println("���������� ����");
		} else {
			System.out.println("������ ������ �������� �ʽ��ϴ�.");
		}
		
	}
	
	public void searchBook() {
		
		System.out.print("���� ���� : ");
		String title = sc.nextLine();
		
		// �˻� ��û
		ArrayList<Book> bookList = bm.searchBook(title);
		
		if (bookList.isEmpty()) {
			System.out.println("�˻� ����� �������� �ʽ��ϴ�.");
		} else {
			for (Book b : bookList) {
				System.out.println(b);
			}
		}
	}

	public void selectList() {
		
		System.out.println("��ü ���� ��� ���");
		
		// ��ü ��� ��û
		ArrayList<Book> bookList = bm.selectList();
		
		if (bookList.isEmpty()) {
			System.out.println("���� ����� �������� �ʽ��ϴ�.");
		} else {
			for (Book b : bookList) {
				System.out.println(b);
			}
		}
	}
}
