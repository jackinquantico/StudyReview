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
			
			System.out.println("======== 정보 공유 게시판 ========");
			selectAll();
			System.out.println("============================");
			System.out.println("1. 게시글 작성하기");
			System.out.println("2. 게시글 제목으로 검색");
			System.out.println("3. 게시글 작성자로 검색");
			System.out.println("4. 게시글 내용으로 검색");
			System.out.println("5. 게시글 작성일로 검색");
			System.out.println("6. 랜덤 게시글 보기");
			System.out.println("7. 게시글 내용보기");
			System.out.println("8. 게시글 수정하기");
			System.out.println("9. 게시글 삭제하기");
			System.out.println("0. 게시판 종료하기");
			System.out.println("============================");
			System.out.print(">> 메뉴 번호 입력 : ");
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
				System.out.println("게시판을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 메뉴입니다. 다시 입력해주세요.");
			}
			
			System.out.println();
		}
	}
	
	public void selectAll() {
		
		bc.selectAll();
	}
	
	public void insertBoard() {
		
		System.out.println("----- 게시글 작성하기 -----");
		System.out.print("게시글 제목 : ");
		String title = sc.nextLine();
		
		System.out.print("게시글 내용 : ");
		String content = sc.nextLine();
		
		System.out.print("작성자 : ");
		String writer = sc.nextLine();
		
		bc.insertBoard(title, content, writer);
	}

	public void selectByTitle() {
		
		System.out.println("----- 게시글 제목 키워드 검색 -----");
		
		System.out.print("제목 키워드 입력 : ");
		String keyword = sc.nextLine();
		
		bc.selectByTitle(keyword);
	}
	
	public void selectByUserId() {
		
		System.out.println("----- 작성자 검색 -----");
		
		System.out.print("작성자 아이디 입력 : ");
		String userId = sc.nextLine();
		
		bc.selectByUserId(userId);
	}
	
	public void selectByContent() {
		
		System.out.println("----- 게시글 내용 검색 -----");
		
		System.out.print("게시글 키워드 입력 : ");
		String keyword = sc.nextLine();
		
		bc.selectByContent(keyword);
	}
	
	public void selectByCreateDate() {
		
		System.out.println("----- 게시글 날짜로 검색 -----");
		
		System.out.print("작성일 입력 (MMDD) : ");
		String createDate = sc.nextLine();
		
		bc.selectByCreateDate(createDate);
	}
	
	public void selectRandom() {
		
		System.out.println("----- 랜덤 게시글 -----");
		
		bc.selectRandom();
	}
	
	public void selectBoard() {
		
		System.out.println("----- 게시글 내용보기 -----");
		
		System.out.print("게시글 번호 입력 : ");
		int bNo = sc.nextInt();
		sc.nextLine();
		
		bc.selectBoard(bNo);
	}
	
	public void updateBoard() {
		
		System.out.println("----- 게시글 수정 -----");
		
		System.out.print("수정할 게시글 번호 : ");
		int bNo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.print("수정할 내용 : ");
		String content = sc.nextLine();
		
		bc.updateBoard(bNo, title, content);
	}
	
	public void deleteBoard() {
		
		System.out.println("----- 게시글 삭제 -----");
		
		System.out.print("삭제할 게시글 번호 : ");
		int bNo = sc.nextInt();
		sc.nextLine();
		
		bc.deleteBoard(bNo);
	}
	
	//----------------------------
	
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
	
	public void displayList(ArrayList<Board> list) {
		
		System.out.println("조회된 결과 : ");
		
		for (Board b : list) {
			System.out.println(b);
		}
	}
	
	public void displayOne(Board b) {
		
		System.out.println(b);
		System.out.println("글 내용 : "+b.getContent());
	}
	
	public void displaySuccess(String message) {
		
		System.out.println("서비스 요청 성공 : "+message);
	}
	
	public void displayFail(String message) {
		System.out.println("서비스 요청 실패 : "+message);
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
