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
		
		// INSERT �� => ����� int => Ʈ����� ó��
		
		// 0. ���� ����
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		// SQL �� (INSERT)
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
			// 1. JDBC Driver ���
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection ��ü ����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
						
			// 3. Statement ��ü ����
			stmt = conn.createStatement();
			
			// 4, 5. SQL �� ���� �� ��� �ޱ�
			result = stmt.executeUpdate(sql);
			
			// 6. Ʈ����� ó��
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
				// 7. �ڿ� �ݳ� (��������)
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8. ��� ����
		return result;
	}
	
	/**
	 * ��ü ȸ�� ��ȸ�� ���� SELECT ���� �����ϴ� �޼ҵ�
	 * @return : ���̺�κ��� ��ȸ�� �����͸� ��Ÿ���� ArrayList
	 */
	public ArrayList<Member> selectAll() {
		
		// 0. ���� ����
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// SQL �� ����
		String sql = "SELECT * FROM MEMBER";
		
		try {
			// 1. JDBC Driver ���
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection ��ü ����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3. Statement ��ü ����
			stmt = conn.createStatement();
			
			// 4, 5. SQL �� �����鼭 ���� �� ��� �ޱ�
			rset = stmt.executeQuery(sql);
			
			// 6. ��� ó��
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
				// 7. �ڿ� �ٳ�
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8. ��� ����
		return list;
	}
	
	public Member selectByUserId(String userId) {
		
		// 0. ���� ����
		Member m = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// SQL �� ����
		String sql = "SELECT * FROM MEMBER WHERE USERID='"+userId+"'";
		
		try {
			// 1. JDBC Driver ���
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection ��ü ����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");

			// 3. Statement ��ü ����
			stmt = conn.createStatement();
			
			// 4, 5. SQL �� �����ϰ� ��� �ޱ�
			rset = stmt.executeQuery(sql);
			
			// 6. rset���� ��� ����
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
				// 7. �ڿ� �ݳ�
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8. ��� ����
		return m;
	}
	
	public ArrayList<Member> selectByUserName(String keyword) {
		
		// 0. ���� ����
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// SQL ��
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%"+keyword+"%'";
		
		try {
			// 1. JDBC Driver ���
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection ��ü ����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			// 3. Statment ��ü ����
			stmt = conn.createStatement();
			
			// 4, 5. SQL �� ���� �� ��� �ޱ�
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
				
		// 8. ��� ����
		return list;
	}
	
	public int updateMember(Member m) {
		
		// 0. ���� ����
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
			// JDBC Driver ���
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Connection ��ü ����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// Statement ��ü ����
			stmt = conn.createStatement();
			
			// ����� ��� �ޱ�
			result = stmt.executeUpdate(sql);
			
			// Ʈ����� ó��
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
