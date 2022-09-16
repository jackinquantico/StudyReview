package com.kh.part_15.jdbc.properties.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.part_15.jdbc.properties.common.JDBCTemplate;
import com.kh.part_15.jdbc.properties.model.dao.MemberDao;
import com.kh.part_15.jdbc.properties.model.vo.Member;

public class MemberService {

	public int insertMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Member> selectAll() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Member> list = new MemberDao().selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public Member selectByUserId(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().selectByUserId(conn, userId);
		
		JDBCTemplate.close(conn);
		
		return m;
	}
	
	public ArrayList<Member> selectByUserName(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Member> list = new MemberDao().selectByUserName(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int updateMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int deleteMember(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(conn,userId);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Member> selectByEnrollDate(String enrollDate) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Member> list = new MemberDao().selectByEnrollDate(conn, enrollDate);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
}
