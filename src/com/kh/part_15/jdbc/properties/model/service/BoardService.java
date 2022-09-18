package com.kh.part_15.jdbc.properties.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.part_15.jdbc.preparedStatement.common.JDBCTemplate;
import com.kh.part_15.jdbc.properties.model.dao.BoardDao;
import com.kh.part_15.jdbc.properties.model.vo.Board;

public class BoardService {
	
	public ArrayList<Board> selectAll() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int insertBoard(Board b) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().insertBoard(conn, b);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}

	public ArrayList<Board> selectByTitle(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().selectByTitle(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public ArrayList<Board> selectByUserId(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().selectByUserId(conn, userId);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public ArrayList<Board> selectByContent(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().selectByContent(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public ArrayList<Board> selectByCreateDate(String createDate) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().selectByCreateDate(conn, createDate);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public Board selectBoard(int bNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Board b = new BoardDao().selectBoard(conn, bNo);
		
		JDBCTemplate.close(conn);
		
		return b;
	}
	
	public int updateBoard(Board b) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().updateBoard(conn, b);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int deleteBoard(int bNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().deleteBoard(conn, bNo);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
}
