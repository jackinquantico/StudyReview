package com.kh.part_15.jdbc.statement.controller;

import com.kh.part_15.jdbc.statement.model.dao.MemberDao;
import com.kh.part_15.jdbc.statement.model.vo.Member;

public class MemberController {

	public void insertMember(String userId, String userPwd, String userName, String gender
							, int age, String email, String phone, String address, String hobby) {
		
		// 1. VO 객체로 가공
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		// 2. VO 객체를 DAO 클래스로 넘기고 결과 받기
		int result = new MemberDao().insertMember(m);
		
		// 3. 결과에 따른 응답화면 지정
		
	}
}
