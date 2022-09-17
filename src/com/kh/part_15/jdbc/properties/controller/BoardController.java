package com.kh.part_15.jdbc.properties.controller;

import java.util.ArrayList;

import com.kh.part_15.jdbc.properties.model.service.BoardService;
import com.kh.part_15.jdbc.properties.model.vo.Board;
import com.kh.part_15.jdbc.properties.view.BoardView;
import com.kh.part_15.jdbc.statement.model.vo.Member;

public class BoardController {

	public void selectAll() {
		
		ArrayList<Board> list = new BoardService().selectAll();
		
		if (list.isEmpty()) {
			new BoardView().displayNodata("���� �ۼ��� ���� �������� �ʽ��ϴ�.");
		} else {
			new BoardView().displayList(list);
		}
	}
	
	public void insertBoard(String title, String content, String writer) {
		
		Board b = new Board();
		b.setTitle(title);
		b.setContent(content);
		b.setWriter(writer);
		
		int result = new BoardService().insertBoard(b);
		
		if (result > 0) {
			new BoardView().displaySuccess("�Խñ� �ۼ� ����");
		} else {
			new BoardView().displayFail("�Խñ� �ۼ� ����");
		}
	}
	
	public void selectByTitle(String keyword) {
		
		ArrayList<Board> list = new BoardService().selectByTitle(keyword);
		
		if (list.isEmpty()) {
			new BoardView().displayNodata(keyword+" �� ���Ե� �� ������ �����ϴ�.");
		} else {
			new BoardView().displayList(list);
		}
	}
	
	public void selectByUserId(String userId) {
		
		ArrayList<Board> list = new BoardService().selectByUserId(userId);
		
		if (list.isEmpty()) {
			new BoardView().displayNodata(userId+" �� �ۼ��� �Խñ��� �����ϴ�.");
		} else {
			new BoardView().displayList(list);
		}
	}
	
	public void selectByContent(String keyword) {

		ArrayList<Board> list = new BoardService().selectByContent(keyword);
		
		if (list.isEmpty()) {
			new BoardView().displayNodata(keyword+" �� ���Ե� �Խñ��� �����ϴ�.");
		} else {
			new BoardView().displayList(list);
		}
	}
	
	public void selectRandom() {
		
		ArrayList<Board> list = new BoardService().selectAll();
		
		if (list.isEmpty()) {
			new BoardView().displayNodata("���� �ۼ��� �Խñ��� �����ϴ�.");
		} else {
			new BoardView().displayRandom(list);
		}
	}
	
	public void selectByCreateDate(String createDate) {
		
		ArrayList<Board> list = new BoardService().selectByCreateDate(createDate);
		
		if (list.isEmpty()) {
			new BoardView().displayNodata(createDate+" �� �ۼ��� ���� �����ϴ�.");
		} else {
			new BoardView().displayList(list);
		}
	}
	
	public void selectBoard(int bNo) {
		
		Board b = new BoardService().selectBoard(bNo);
		
		if (b != null) {
			new BoardView().displayOne(b);
		} else {
			new BoardView().displayNodata("�ش� �Խñ��� �������� �ʽ��ϴ�.");
		}
	}
	
	public void updateBoard(int bNo, String title, String content) {
		
		Board b = new Board();
		b.setbNo(bNo);
		b.setTitle(title);
		b.setContent(content);
		
		int result = new BoardService().updateBoard(b);
		
		if (result > 0) {
			new BoardView().displaySuccess("�Խñ� ���� ����");
		} else {
			new BoardView().displayFail("�Խñ� ���� ����");
		}
	}
	
	public void deleteBoard(int bNo) {
		
		int result = new BoardService().deleteBoard(bNo);
		
		if (result > 0) {
			new BoardView().displaySuccess("�Խñ� ���� ����");
		} else {
			new BoardView().displayFail("�Խñ� ���� ����");
		}
	}
}
