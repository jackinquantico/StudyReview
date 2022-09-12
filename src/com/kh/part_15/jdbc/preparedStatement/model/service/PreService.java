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
}
