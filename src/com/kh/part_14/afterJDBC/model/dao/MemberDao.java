package com.kh.part_14.afterJDBC.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.part_14.afterJDBC.model.vo.Member;

/*
 * * DAO (Data Access Object)
 * Controller �� ���ؼ� ȣ��� ����� �����ϱ� ����
 * DB�� ���������� ������ �� �ش� SQL �� ���� �� ����� �޴� �κ�
 * => JDBC �ڵ� �ۼ�
 */

public class MemberDao {

	/*
	 * * JDBC �� ��ü
	 * - Connection : DB �� ���� ������ ��� �ִ� ��ü
	 * - (Prepared)Statement : �ش� DB�� SQL ���� �����ϰ� ������ ����� �޾Ƴ��� ��ü
	 * - ResultSet : ���� ������ SQL ���� SELECT ���� ��� ��ȸ�� ������� ����ִ� ��ü
	 * 
	 * * JDBC ó�� ����
	 * 1) JDBC Driver ��� : �ش� DBMS �� �����ϴ� Ŭ���� ���
	 * 2) Connection ��ü ���� : �����ϰ��� �ϴ� DB ������ ����ؼ� DB�� ����
	 * 3) Statement ��ü ���� : Connection ��ü�� �̿��ؼ� ����
	 * 4) SQL �� �����ϸ鼭 ���� : Statement ��ü���� �����ϴ� �޼ҵ带 ȣ�������ν� ����
	 * 			> SELECT ���� ��� - executeQuery �޼ҵ带 �̿��Ͽ� ����
	 * 			> ������ DML ���� ��� - executeUpdate �޼ҵ带 �̿��Ͽ� ����
	 * 			  (INSERT, UPDATE, DELETE)
	 * 5) ��� �ޱ�
	 * 			> SELECT ���� ��� - ResultSet ��ü(��ȸ�� �����͵��� �������) �� ��ȯ => 6_1)
	 * 			> ������ DML �� �� ��� - int Ÿ��(ó���� ���� ����)���� ��ȯ			  => 6_2)
	 * 6_1) ResultSet �� ����ִ� �����͵��� �ϳ��� �̾Ƽ� VO ��ü�� ���
	 * 6_2) Ʈ����� ó�� (���� : COMMIT, ���� : ROLLBACK)
	 * 7) �� �� JDBC �� ��ü���� �ݵ�� �ڿ��ݳ� (close�޼ҵ�) => ������ ������ ��������
	 * 8) ��� ��ȯ (Controller Ŭ������)
	 *  		> SELECT ���� ��� - 6_1)���� ������� ��� ����
	 *  		> ������ DML ���� ��� - int (ó���� ���� ����) �� ����
	 * 			  (INSERT, UPDATE, DELETE)
	 * 
	 * ** Statement Ư¡ : �ϼ��� SQL ���� ������ �� �ִ� ��ü
	 */
	
	/**
	 * ����ڰ� ȸ�� �߰� ��û �� �Է��ߴ� ������ ������ INSERT ���� �����ϴ� �޼ҵ�
	 * @param m : ����ڰ� �Է��ߴ� ������ ��� ����ִ� Member ��ü
	 * @return : INSERT �� ���� �� ó���� ���� ����
	 */
	public int insertMember(Member m) {
		
		// INSERT �� => ó���� ���� ���� (int) => Ʈ����� ó��
		
		// 0) �ʿ��� ���� ���� �� null �ʱ�ȭ
		int result = 0; // ó���� ����� ���� �� �ִ� ����
		Connection conn = null;// ���ӵ� DB�� ���������� ���� �� �ִ� ����
		Statement stmt = null;// SQL �� ���� �� ����� �ޱ� ���� ����
		
		// ������ SQL �� (�ϼ��� ���·� ������ ��)
		// => ���� �����ݷ��� �� ���� (�����ݷ��� ������ �� ��)
		// INSERT INTO MEMBER
		// VALUES (SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X'
		// 		 , XX, 'XXXX', 'XXXXXX', 'XXXXXX', 'XXX', DEFAULT)
		String sql = "INSERT INTO MEMBER "
							   + "VALUES (SEQ_USERNO.NEXTVAL, "
						   			+"'"+ m.getUserId() + "', "
						   			+"'"+ m.getUserPwd() + "', "
						   			+"'"+ m.getUserName() + "', "
						   			+"'"+ m.getGender() + "', "
							   			+ m.getAge() + ", "
						   			+"'"+ m.getEmail() + "', "
						   			+"'"+ m.getPhone() + "', "
						   			+"'"+ m.getAddress() + "', "
						   			+"'"+ m.getHobby() + "', DEFAULT)";
		
		// System.out.println(sql);
		
		try {
			// 1) JDBC Driver ���
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ojdbc6.jar ���� ���� �ִ� oracle.jdbc.driver.OracleDriver Ŭ������ ���
			// => ojdbc6.jar ������ �����Ǿ��ų� �� �߰��Ǿ����� ��Ÿ�� �ִ� ���
			//	  ClassNotFoundException �߻�
			
			// 2) Connection ��ü ���� (DB�� ���� -> url : DB�� �ּ���, ������, ��й�ȣ)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			// => ��� �� �ܰ���� �Ϸ�Ǿ��ٸ� DB�� ������ �Ϸ�� ��
			
			// 3) Statement ��ü ���� (Connection ��ü�κ���)
			stmt = conn.createStatement();
			// => ��� �� �ܰ���� �Ϸ�Ǿ��ٸ� ���������� SQL �� �����ų �� ����
			
			// 4, 5) DB�� �ϼ��� SQL ���� �����ϸ鼭 ���� �� ��� (ó���� ���� ����) �ޱ�
			result = stmt.executeUpdate(sql);
			// => result ���� ó���� ���� ������ �������
			
			// 6_2) Ʈ����� ó��
			// => result ������ ��� �� �������� ���� ó��
			if (result > 0) { // 1���� ���� insert => insert �� ���� => COMMIT ó��
				// Connection ��ü�� ���� ó��
				conn.commit();
			} else { // 0���� ���� insert => insert �� ���� => ROLLBACK ó��
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 7) �� �� JDBC �� ��ü���� �ݵ�� �ڿ��ݳ�
				// => ������ ������ �������� close
				// ���� ���� : Connection -> Statement
				// �ݴ� ���� : Statement -> Connection
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8) Controller �� ��� ��ȯ (ó���� ���� ����)
		// 	  => �������� ��� 1, �������� ��� 0
		return result;
	}
	
	/**
	 * ����ڰ� ȸ�� ��ü ��ȸ ��û �� SELECT ���� �����ϴ� �޼ҵ�
	 * @return : ���̺�κ��� ��ȸ�� ��ü ȸ���� ������ ��Ÿ���� ArrayList
	 */
	public ArrayList<Member> selectAll() {
		
		// SELECT �� => ResultSet ��ü => ���� �� ��ȸ (Member 1���� ȸ�� �� ��) : ArrayList Ÿ������ ����
		
		// 0) �ʿ��� ���� ���� �� �ʱ�ȭ
		ArrayList<Member> list = new ArrayList<>(); // ��ȸ�� ����� �̾Ƽ� ����� ArrayList ���� (�� �� ����Ʈ)
		Connection conn = null; // ���ӵ� DB�� ���� ������ ��� Connection ����
		Statement stmt = null; // SQL �� ���� �� ����� �ޱ� ���� ����
		ResultSet rset = null; // SELECT ���� ����� ��ȸ������� ó�� ���������� ��ܿ� ��ü
		
		// ������ SQL ���� ���� ���� (�ϼ��� ����, �����ݷ� ���� X)
		// SELECT * FROM MEMBER
		String sql = "SELECT * FROM MEMBER";
		
		try {
			// 1) JDBC Driver ���
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection ��ü ���� (url, ������, ��й�ȣ)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement ��ü ���� (Connection ��ü�κ��� ���� �� ����)
			stmt = conn.createStatement();
			
			// 4, 5) SQL �� (SELECT) �����ؼ� ���� �� ��� �ޱ�
			rset = stmt.executeQuery(sql);
			// => rset ���� ��ȸ�� ��������� �� ������� ����.
			
			// 6_1) ���� ��ȸ ����� ��� ResultSet ���� �� �྿ �̾Ƽ� VO ��ü�� ���
			// => ResultSet ��ü�� Ŀ���� �� �پ� �Ʒ��� �Űܼ� ���� ���� ��ġ�� ��Ÿ���� ����
			// => �� ��, Ŀ���� rset.next() �޼ҵ�� ���� �ٷ� �Űܹ����� : �� ���� ���� ������ true, ������ false ��ȯ
			while (rset.next()) { // Ŀ���� �� �� �Ʒ��� �������ְ�, �ش� ���� �����ϸ� true, ������ false ��ȯ
				
				// ��� �� �߰�ȣ ���� ���ͼ� �ڵ尡 ����ȴ� == �� �̾Ƴ� ���� ���� ���
				
				// ���� rset �� Ŀ���� ����Ű�� �ִ� �ش� ���� �����͸� �ϳ��� �̾Ƽ� Member ��ü�� ���
				// �� ���� ������ == ȸ�� �� ���� ���� == Member VO ��ü �� ��
				Member m = new Member();
				
				// rset���κ��� � �÷��� �ش��ϴ� ���� ���� ���� ����
				// �� �÷��� ����ִ� ������ �� �ʵ�� �Ű��ֱ�
				// rset.getInt(�÷��� / �÷�����) : int �� ���� �̾Ƴ� �� 
				// rset.getString(�÷��� / �÷�����) : String �� ���� �̾Ƴ� ��
				// rset.getDate(�÷��� / �÷�����) : Date �� ���� �̾Ƴ� ��
				// => �÷����� ��ҹ��ڸ� ������ ����
				// => �÷����� �빮�ڷ� ���� ���� ���� (�������� ����)
				
				// m.userNo = rset.getInt("USERNO"); => private �� �Ұ�
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				// �� �࿡ ���� ��� ������ ������ �ϳ��� Member ��ü�� �Űܴ�� ��
				
				// Member VO ��ü�� list�� �߰� (��Ƶα�)
				list.add(m);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 7) �� �� JDBC �� ��ü �ݳ� (������ ����)
				// ���� ���� : Connection > Statement > ResultSet
				// �ݴ� ���� : ResultSet > Statement > Connection
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8) ��� ��ȯ (Controller Ŭ������)
		return list; // ȸ������ ������ �������
					 // ���� �ƹ��� ��ȸ���� �ʾҴٸ� list.size () == 0, list.isEmpty() == true
		
	}

	/**
	 * ������� ���̵� �˻� ��ȸ�� ���� SELECT ���� ������ �޼ҵ�
	 * @param userId : �˻��ϰ��� �ϴ� ���ǿ� �ش�Ǵ� ���̵�
	 * @return Member : �˻��� ȸ�� �� ���� ����
	 */
	public Member selectByUserId(String userId) {
		
		// SELECT �� => ResultSet ��ü => Member Ÿ�� �ϳ��� ��� �ޱ� (�� �� ��ȸ)
		
		// 0) �ʿ��� ���� ���� �� �ʱ�ȭ
		Member m = null; // ��ȸ�� �� ȸ���� ���� ������ ��� ����
		Connection conn = null; // ���ӵ� DB�� ���������� ��� ����
		Statement stmt = null; // SQL �� ���� �� ����� �ޱ� ���� ����
		ResultSet rset = null; // SELECT ���� ����� ��ȸ ��������� ó���� ���������� ��� ��ü
		
		// ������ SQL �� (�ϼ��� ����, �����ݷ� ����)
		// SELECT * FROM MEMBER WHERE USERID = 'userId';
		String sql = "SELECT * FROM MEMBER WHERE USERID = '" + userId + "'";
		
		try {
			// 1) JDBC Driver ���
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection ��ü ���� (url, ������, ��й�ȣ)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement ��ü ���� (Connection ��ü�κ���)
			stmt = conn.createStatement();
			
			// 4, 5) SQL �� (SELECT) �����ؼ� ���� �� ��� �ޱ�
			rset = stmt.executeQuery(sql);
			
			// 6_1) ���� ��ȸ ����� ��� ResultSet ���� �� �྿ �̾Ƽ� VO ��ü�� ���
			// ���� �� ��ȸ �� ��� : �� �̻� �̾Ƴ� �����Ͱ� ���� ������ ������ => while (rset.next())
			if (rset.next()) { // �� �� ��ȸ�� ��� (UNIQUE �������ǿ� ����) : if ������ ���� �˻�
				
				// ��� �� �߰�ȣ �� �� ���Դٴ� ���� ��ȸ�� ����� �ִٴ� ��
				// => �� ���� �࿡ ���ؼ� �� �÷����� ���� �Ź� �̾Ƽ� VO ��ü�� �ʵ�� ����
				
				// ��ȸ�� �� �࿡ ���� �����Ͱ����� �̾Ƽ� �ϳ��� Member ��ü�� ���
				// ��ȸ�� ���� ���� ��쿡�� Member ��ü ���� => �Ű� ���� �����ڷ�
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
			
			// �� ���� �������� ���� ��
			// ���� ��ȸ�� ȸ���� �ִٸ� m�̶�� ������ Member Ÿ�� ��ü�� �������
			// ���� ��ȸ�� ȸ���� ���ٸ� m�̶�� ������ null ���� �״�� �������
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 7) �� �� JDBC �� ��ü���� �ݵ�� �ڿ��ݳ� (������ ����)
				// ResultSet -> Statement -> Connection ������
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8) ��� ����
		return m; // ��ȸ�� ȸ���� �ִٸ� Member ��ü, ���ٸ� null ���� �������
	}
	
	/**
	 * ������� �̸� Ű���� �˻� ��û�� �����ϱ� ���� SELECT ���� ������ �޼ҵ�
	 * @param keyword : �̸� Ű���� �˻��� ���� �˻���
	 * @return ArrayList : ��ȸ�� ȸ������ ���
	 */
	public ArrayList<Member> selectByUserName(String keyword) {
		
		// SELECT �� => ResultSet ��ü => ���� �� ��ȸ (ArrayList<Member>)
		
		// ���� ���� �� �ʱ�ȭ
		ArrayList<Member> list = new ArrayList<>();
		Connection conn = null; // ���ӵ� DB�� ������ ��Ƶ� �� �ִ� ����
		Statement stmt = null; // SQL �� (SELECT) ���� �� ����� ���� �� �ִ� ����
		ResultSet rset = null; // SELECT �� ���� �� ������� ��� ����
		
		// ������ SQL �� (�ϼ��� ����, �����ݷ� ����)
		// SELECT * FROM MEMBER WHERE USERNAME LIKE '%XXX%'
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%" +keyword+ "%'";
		
		try {
			// 1) JDBC Driver ���
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection ��ü ���� (url, ������, ��й�ȣ)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement ��ü ���� (Connection ��ü�κ��� ���� �� ����)
			stmt = conn.createStatement();
			
			// 4, 5) SQL �� (SELECT) �����ؼ� ���� �� ��� �ޱ�
			// SELECT ���̹Ƿ� executeQuery() �޼ҵ� ����
			rset = stmt.executeQuery(sql);
			
			// 6_1) ���� ��ȸ ����� ��� ResultSet ���� �� �྿ �̾Ƽ� VO ��ü�� ���
			// rset.next()�� Ŀ���� �� �྿ �Ʒ��� �����鼭 �� �̻� ���� ���� ���� ������ �ݺ���
			while (rset.next()) {
				
				// �� �߰�ȣ ���� ���Դٸ� ���� ���� �ִ� ��츦 �ǹ�
				
				// �� �࿡ ��� �����͸� �� Member Ÿ���� ��ü�� �Űܴ��
				// Member ��ü�� �Ű����� �����ڷ� ������ ���ÿ� �� ���
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
				
				// �˻� ��� ���� ���� ArrayList�� ��ü ���
				list.add(m);
				
				// �Ű����� �����ڷ� ������ ���ÿ� list.add() �� �ֱ�
				// list.add(new Member(rset.getInt("USERNO")
				//					 , rset.getString("USERID")
				//					 , rset.getString("USERPWD")
				//					 , rset.getString("USERNAME")
				//					 , rset.getString("GENDER")
				//					 , rset.getInt("AGE")
				//					 , rset.getString("EMAIL")
				//					 , rset.getString("PHONE")
				//					 , rset.getString("ADDRESS")
				//					 , rset.getString("HOBBY")
				//					 , rset.getDate("ENROLLDATE"));
			}
			
			// �� ���� �������� ���� ��
			// ��ȸ�� ȸ���� ���ٸ� list.size() == 0 �Ǵ� list.isEmpty() == true
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 7. JDBC ��ü �ڿ� �ݳ� (���� ������ ����)
				// ���� ���� : Connection -> Statement -> ResultSet
				rset.close();
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8. ����� ����
		// �˻��� ������� ���ٸ� list.size() == 0 �Ǵ� list.isEmpty() == true
		return list;
	}
	
	/**
	 * ȸ�� ���� ������ ���� UPDATE ������ �����ϴ� �޼ҵ�
	 * @param m : ȸ������ ������ ���� ������
	 * @return int : ���̺� UPDATE �Ǵ� ���� ����
	 */
	public int updateMember(Member m) {
		
		// UPDATE �� => ó���� ���� ���� (int) => Ʈ����� ó��
		
		// 0) �ʿ��� ���� ���� �� �ʱ�ȭ
		int result = 0; // ó���� ���� ������ ���� �� �ִ� ����
		Connection conn = null; // ���ӵ� DB�� ������ ���� �� �ִ� ����
		Statement stmt = null; // SQL �� (UPDATE) ���� �� ����� ���� �� �ִ� ����
		
		// ������ SQL �� (�ϼ��� ����, �����ݷ� ����)
		/* UPDATE MEMBER
		   SET USERPWD = 'XXX'
			 , EMAIL = 'XXX'
			 , PHONE = 'XXX'
			 , ADDRESS = 'XXX'
		   WHERE USERID = 'XXX';
		*/
		String sql = "UPDATE MEMBER " + 
					 "SET USERPWD = '" + m.getUserPwd() + "'"
					 + ", EMAIL = '" + m.getEmail() + "'"
					 + ", PHONE = '" + m.getPhone() + "'"
					 + ", ADDRESS = '" + m.getAddress() + "' "
				   + "WHERE USERID = '"+ m.getUserId() + "'";
		
		try {
			// 1) JDBC Driver ���
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			// 2) Connection ��ü ����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement ��ü ����
			stmt = conn.createStatement();
			
			// 4, 5) SQL �� (UPDATE) ���� �� ��� �ޱ�
			result = stmt.executeUpdate(sql);
			
			// 6_2) Ʈ����� ó��
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
				// 7) JDBC �ڿ� �ݳ� (���� ��������)
				// Statement -> Connection
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8) ��� ��ȯ
		return result;
	}
	
	/**
	 * ȸ�� ���� ������ ���� DELETE ������ �����ϴ� �޼ҵ�
	 * @param userId : ȸ������ ������ ���� ������
	 * @returnint : ���̺��� DELETE �Ǵ� ���� ����
	 */
	public int deleteMember(String userId) {
		
		// DELETE �� => ó���� ���� ���� (int) => Ʈ����� ó��
		
		// 0) ���� ���� �� �ʱ�ȭ
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		// ������ SQL�� (DELETE)
		// DELETE FROM MEMBER WHERE USERID = 'XXX';
		String sql = "DELETE FROM MEMBER WHERE USERID = '"+userId+"'";
		
		try {
			// 1) JDBC Driver ���
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection ��ü ����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement ��ü ����
			stmt = conn.createStatement();
			
			// 4, 5) SQL �� ���� �� ��� �ޱ� (DELETE)
			result = stmt.executeUpdate(sql);
			
			// 6_2) Ʈ����� ó��
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
				// 7) �ڿ� �ݳ�
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 8) ��� ��ȯ
		return result;
	}
	
}
