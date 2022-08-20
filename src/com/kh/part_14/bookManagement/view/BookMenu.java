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
			System.out.println("*** 도서 관리 프로그램 ***");
			System.out.println(" 1. 새 도서 추가");
			System.out.println(" 2. 도서 삭제");
			System.out.println(" 3. 도서 검색 출력");
			System.out.println(" 4. 전체 출력");
			System.out.println(" 0. 종료하기");
			
			System.out.print("메뉴 번호 선택 : ");
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
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
			
			System.out.println();
		} // while
		
	} // mainMenu
	
	public void insertBook() {
		
		System.out.print("도서 제목 : ");
		String title = sc.nextLine();
		
		System.out.print("도서 장르 (1:인문 /2:자연과학 /3:의료 /4:기타) : ");
		int category = sc.nextInt();
		sc.nextLine();
		
		System.out.print("도서 저자 : ");
		String author = sc.nextLine();
		
		Book book = new Book(title, category, author);
		
		// 추가 요청 -> BookManager Controller 클래스에게
		int result = bm.insertBook(book);
		
		if (result > 0) {
			System.out.println("성공적으로 추가되었습니다.");
		} else {
			System.out.println("추가 실패");
		}
	}
	
	public void deleteBook() {
		
		System.out.print("도서 번호 : ");
		int bNo = sc.nextInt();
		
		// 삭제 요청
		int result = bm.deleteBook(bNo);
		
		if (result > 0) {
			System.out.println("성공적으로 삭제");
		} else {
			System.out.println("삭제할 도서가 존재하지 않습니다.");
		}
		
	}
	
	public void searchBook() {
		
		System.out.print("도서 제목 : ");
		String title = sc.nextLine();
		
		// 검색 요청
		ArrayList<Book> bookList = bm.searchBook(title);
		
		if (bookList.isEmpty()) {
			System.out.println("검색 결과가 존재하지 않습니다.");
		} else {
			for (Book b : bookList) {
				System.out.println(b);
			}
		}
	}

	public void selectList() {
		
		System.out.println("전체 도서 목록 출력");
		
		// 전체 목록 요청
		ArrayList<Book> bookList = bm.selectList();
		
		if (bookList.isEmpty()) {
			System.out.println("도서 목록이 존재하지 않습니다.");
		} else {
			for (Book b : bookList) {
				System.out.println(b);
			}
		}
	}
}
