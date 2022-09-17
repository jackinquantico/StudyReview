package com.kh.part_15.jdbc.properties.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.part_15.jdbc.preparedStatement.common.JDBCTemplate;
import com.kh.part_15.jdbc.properties.model.vo.Board;

public class BoardDao {
	
	Properties prop = new Properties();
	
	public BoardDao() {
		
		try {
			prop.loadFromXML(new FileInputStream("properties_resources/boardquery.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Board> selectAll(Connection conn) {
		
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Board b = new Board(rset.getInt("BNO")
								  , rset.getString("TITLE")
								  , rset.getString("CONTENT")
								  , rset.getDate("CREATE_DATE")
								  , rset.getString("USERID"));
				
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	public int insertBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, Integer.parseInt(b.getWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Board> selectByTitle(Connection conn, String keyword) {
	
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByTitle");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				Board b = new Board(rset.getInt("BNO")
						  , rset.getString("TITLE")
						  , rset.getString("CONTENT")
						  , rset.getDate("CREATE_DATE")
						  , rset.getString("USERID"));
		
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Board> selectByUserId(Connection conn, String userId) {
		
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByUserId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				Board b = new Board(rset.getInt("BNO")
						  , rset.getString("TITLE")
						  , rset.getString("CONTENT")
						  , rset.getDate("CREATE_DATE")
						  , rset.getString("USERID"));
		
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Board> selectByContent(Connection conn, String keyword) {
		
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByContent");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				Board b = new Board(rset.getInt("BNO")
						  , rset.getString("TITLE")
						  , rset.getString("CONTENT")
						  , rset.getDate("CREATE_DATE")
						  , rset.getString("USERID"));
		
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Board> selectByCreateDate(Connection conn, String createDate) {
		
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByCreateDate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, createDate);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				Board b = new Board(rset.getInt("BNO")
						  , rset.getString("TITLE")
						  , rset.getString("CONTENT")
						  , rset.getDate("CREATE_DATE")
						  , rset.getString("USERID"));
		
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	public Board selectBoard(Connection conn, int bNo) {
		
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bNo);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				
				b = new Board(rset.getInt("BNO")
						  , rset.getString("TITLE")
						  , rset.getString("CONTENT")
						  , rset.getDate("CREATE_DATE")
						  , rset.getString("USERID"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return b;
	}
	
	public int updateBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, b.getbNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int deleteBoard(Connection conn, int bNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
}
