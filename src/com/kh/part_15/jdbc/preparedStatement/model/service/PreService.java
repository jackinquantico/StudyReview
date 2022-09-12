package com.kh.part_15.jdbc.preparedStatement.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.part_15.jdbc.preparedStatement.common.JDBCTemplate;
import com.kh.part_15.jdbc.preparedStatement.model.dao.PreDao;
import com.kh.part_15.jdbc.preparedStatement.model.vo.PreMember;

public class PreService {

	public int insertMember(PreMember m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new PreDao().insertMember(conn, m);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<PreMember> selectAll() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<PreMember> list = new PreDao().selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public PreMember selectByUserId(String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		PreMember m = new PreDao().selectByUserId(conn, userId);
		
		JDBCTemplate.close(conn);
		
		return m;
	}
	
	public ArrayList<PreMember> selectByUserName(String keyword) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<PreMember> list = new PreDao().selectByUserName(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int updateMember(PreMember m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new PreDao().updateMember(conn, m);
		
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
		
		int result = new PreDao().deleteMember(conn, userId);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
}
