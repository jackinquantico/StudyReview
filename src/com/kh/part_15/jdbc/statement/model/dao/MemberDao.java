package com.kh.part_15.jdbc.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import com.kh.part_15.jdbc.statement.model.vo.Member;

public class MemberDao {

	public int insertMember(Member m) {
		
		// INSERT 문 => 결과값 int => 트랜잭션 처리
		
		// 0. 변수 세팅
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		// SQL 문 (INSERT)
		String sql = "INSERT INTO MEMBER "
				   + "VALUES (SEQ_USERNO.NEXTVAL"
				   + ", '" + m.getUserId() + "'"
				   + ", '" + m.getUserPwd() + "'"
				   + ", '" + m.getUserName() + "'"
				   + ", '" + m.getGender() + "'"
				   + ", " + m.getAge()
				   + ", '" + m.getEmail() + "'"
				   + ", '" + m.getPhone() + "'"
				   + ", '" + m.getAddress() + "'"
				   + ", '" + m.getHobby() + "', DEFAULT)";
		
		try {
			// 1. JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
						
			// 3. Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5. SQL 문 실행 및 결과 받기
			result = stmt.executeUpdate(sql);
			
			// 6. 트랜잭션 처리
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 7. 자원 반납 (역순으로)
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8. 결과 리턴
		return result;
	}
	
	/**
	 * 전체 회원 조회를 위한 SELECT 문을 실행하는 메소드
	 * @return : 테이블로부터 조회된 데이터를 나타내는 ArrayList
	 */
	public ArrayList<Member> selectAll() {
		
		// 0. 변수 세팅
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// SQL 문 세팅
		String sql = "SELECT * FROM MEMBER";
		
		try {
			// 1. JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3. Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5. SQL 문 보내면서 실행 후 결과 받기
			rset = stmt.executeQuery(sql);
			
			// 6. 결과 처리
			while (rset.next()) {
				
				Member m = new Member(rset.getInt("USERNO")
									, rset.getString("USERID")
									, rset.getString("USERPWD")
									, rset.getString("USERNAME")
									, rset.getString("GENDER")
									, rset.getInt("AGE")
									, rset.getString("EMAIL")
									, rset.getString("PHONE")
									, rset.getString("ADDRESS")
									, rset.getString("HOBBY")
									, rset.getDate("ENROLLDATE"));
				
				list.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 7. 자원 바납
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8. 결과 리턴
		return list;
	}
	
	public Member selectByUserId(String userId) {
		
		// 0. 변수 세팅
		Member m = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// SQL 문 세팅
		String sql = "SELECT * FROM MEMBER WHERE USERID='"+userId+"'";
		
		try {
			// 1. JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// 3. Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5. SQL 문 실행하고 결과 받기
			rset = stmt.executeQuery(sql);
			
			// 6. rset에서 결과 가공
			if (rset.next()) {
				
				m = new Member(rset.getInt("USERNO")
						 , rset.getString("USERID")
						 , rset.getString("USERPWD")
						 , rset.getString("USERNAME")
						 , rset.getString("GENDER")
						 , rset.getInt("AGE")
						 , rset.getString("EMAIL")
						 , rset.getString("PHONE")
						 , rset.getString("ADDRESS")
						 , rset.getString("HOBBY")
						 , rset.getDate("ENROLLDATE"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 7. 자원 반납
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8. 결과 리턴
		return m;
	}
	
	public ArrayList<Member> selectByUserName(String keyword) {
		
		// 0. 변수 세팅
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// SQL 문
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%"+keyword+"%'";
		
		try {
			// 1. JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			// 3. Statment 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5. SQL 문 실행 후 결과 받기
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				
				Member m = new Member(rset.getInt("USERNO")
									, rset.getString("USERID")
									, rset.getString("USERPWD")
									, rset.getString("USERNAME")
									, rset.getString("GENDER")
									, rset.getInt("AGE")
									, rset.getString("EMAIL")
									, rset.getString("PHONE")
									, rset.getString("ADDRESS")
									, rset.getString("HOBBY")
									, rset.getDate("ENROLLDATE"));
			
				list.add(m);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		// 8. 결과 리턴
		return list;
	}
	
	public int updateMember(Member m) {
		
		// 0. 변수 세팅
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "UPDATE MEMBER "
				   + "SET USERPWD = '" +m.getUserPwd() + "'"
				   		+", EMAIL = '" +m.getEmail() +"'"
				   		+", ADDRESS = '" +m.getAddress() +"'"
				   		+", HOBBY = '" +m.getHobby() + "'"
				   + "WHERE USERID = '"+m.getUserId()+"'";
		
		try {
			// JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// Statement 객체 생성
			stmt = conn.createStatement();
			
			// 실행과 결과 받기
			result = stmt.executeUpdate(sql);
			
			// 트랜잭션 처리
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}
