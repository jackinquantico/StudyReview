package com.kh.part_15.jdbc.statement.controller;

import com.kh.part_15.jdbc.statement.model.dao.MemberDao;
import com.kh.part_15.jdbc.statement.model.vo.Member;

public class MemberController {

	public void insertMember(String userId, String userPwd, String userName, String gender
							, int age, String email, String phone, String address, String hobby) {
		
		// 1. VO ��ü�� ����
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		// 2. VO ��ü�� DAO Ŭ������ �ѱ�� ��� �ޱ�
		int result = new MemberDao().insertMember(m);
		
		// 3. ����� ���� ����ȭ�� ����
		
	}
}
