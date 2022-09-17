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
			new BoardView().displayNodata("아직 작성된 글이 존재하지 않습니다.");
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
			new BoardView().displaySuccess("게시글 작성 성공");
		} else {
			new BoardView().displayFail("게시글 작성 실패");
		}
	}
	
	public void selectByTitle(String keyword) {
		
		ArrayList<Board> list = new BoardService().selectByTitle(keyword);
		
		if (list.isEmpty()) {
			new BoardView().displayNodata(keyword+" 가 포함된 글 제목이 없습니다.");
		} else {
			new BoardView().displayList(list);
		}
	}
	
	public void selectByUserId(String userId) {
		
		ArrayList<Board> list = new BoardService().selectByUserId(userId);
		
		if (list.isEmpty()) {
			new BoardView().displayNodata(userId+" 가 작성한 게시글이 없습니다.");
		} else {
			new BoardView().displayList(list);
		}
	}
	
	public void selectByContent(String keyword) {

		ArrayList<Board> list = new BoardService().selectByContent(keyword);
		
		if (list.isEmpty()) {
			new BoardView().displayNodata(keyword+" 이 포함된 게시글이 없습니다.");
		} else {
			new BoardView().displayList(list);
		}
	}
	
	public void selectRandom() {
		
		ArrayList<Board> list = new BoardService().selectAll();
		
		if (list.isEmpty()) {
			new BoardView().displayNodata("아직 작성된 게시글이 없습니다.");
		} else {
			new BoardView().displayRandom(list);
		}
	}
	
	public void selectByCreateDate(String createDate) {
		
		ArrayList<Board> list = new BoardService().selectByCreateDate(createDate);
		
		if (list.isEmpty()) {
			new BoardView().displayNodata(createDate+" 에 작성된 글이 없습니다.");
		} else {
			new BoardView().displayList(list);
		}
	}
	
	public void selectBoard(int bNo) {
		
		Board b = new BoardService().selectBoard(bNo);
		
		if (b != null) {
			new BoardView().displayOne(b);
		} else {
			new BoardView().displayNodata("해당 게시글이 존재하지 않습니다.");
		}
	}
	
	public void updateBoard(int bNo, String title, String content) {
		
		Board b = new Board();
		b.setbNo(bNo);
		b.setTitle(title);
		b.setContent(content);
		
		int result = new BoardService().updateBoard(b);
		
		if (result > 0) {
			new BoardView().displaySuccess("게시글 수정 성공");
		} else {
			new BoardView().displayFail("게시글 수정 실패");
		}
	}
	
	public void deleteBoard(int bNo) {
		
		int result = new BoardService().deleteBoard(bNo);
		
		if (result > 0) {
			new BoardView().displaySuccess("게시글 삭제 성공");
		} else {
			new BoardView().displayFail("게시글 삭제 실패");
		}
	}
}
