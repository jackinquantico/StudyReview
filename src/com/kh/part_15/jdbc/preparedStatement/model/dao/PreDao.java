package com.kh.part_15.jdbc.preparedStatement.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.part_15.jdbc.preparedStatement.common.JDBCTemplate;
import com.kh.part_15.jdbc.preparedStatement.model.vo.PreMember;

public class PreDao {

	public int insertMember(Connection conn, PreMember m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO MEMBER "
				   + "VALUES (SEQ_USERNO.NEXTVAL"
				   + ", ?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;		
	}
	
	public ArrayList<PreMember> selectAll(Connection conn) {
		
		ArrayList<PreMember> list = new ArrayList<PreMember>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				
				PreMember m = new PreMember(rset.getInt("USERNO")
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return list;
	}
}
