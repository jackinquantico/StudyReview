package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	/*
	 *  * JDBC 과정 중 반복적으로 쓰이는 구문들을 각각의 메소드로 정의해둘 것
	 *  "재사용할 목적" 으로 공통 템플릿 작업 진행
	 *
	 *
	 * * 이 클래스에서의 모든 메소드들을 모두 static 메소드로 만들 것임
	 * => 싱글톤 패턴: 메모리 영역에 한번만 올라간 것을 두고두고 공유하며 재사용하는 개념  
	 */
	
	// 1. DB와 접속된 Connection 객체를 생성해서 반환시켜주는 메소드
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 2. 전달받은 JDBC 용 객체를 반납시켜주는 메소드
	// 2_1. Connection 객체를 전달받아서 반납시켜주는 메소드
	public static void close(Connection conn) {
		
		try {
			
			if (conn != null && !conn.isClosed()) { // conn 객체가 null이 아니고 반납되지 않은 상태일 때만
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 2_2. Statement 객체를 전달받아서 반납시켜주는 메소드
	// PreparedStatement와 상속 관계에 있기 때문에 다형성으로 인해
	// 매개변수로 PreparedStatement 또한 매개변수로 전달 가능
	public static void close(Statement stmt) {
		
		try {
			
			if (stmt != null && !stmt.isClosed()) { // stmt 객체가 null이 아니고 반납되지 않은 상태일 때만
				stmt.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 2_3. ResultSet 객체를 전달받아서 반납시켜주는 메소드
	public static void close(ResultSet rset) {
		
		try {
			
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3. 전달받은 Connection 객체를 가지고 트랜잭션 처리해주는 메소드
	// 3_1. 전달받은 Connection 객체를 가지고 COMMIT 시켜주는 메소드
	public static void commit(Connection conn) {
		
		try {
			
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3_2. 전달받은 Connection 객체를 가지고 ROLLBACK 시켜주는 메소드
	public static void rollback(Connection conn) {
		
		try {
			
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
