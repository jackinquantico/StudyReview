package com.kh.part_15.jdbc.statement.controller;

import java.util.ArrayList;

import com.kh.part_15.jdbc.statement.model.dao.MemberDao;
import com.kh.part_15.jdbc.statement.model.vo.Member;
import com.kh.part_15.jdbc.statement.view.MemberView;

public class MemberController {

	/**
	 * ���ο� ȸ�� �߰� ��û�� ó�����ִ� �޼ҵ�
	 * @param userId ~ hobby : ����ڷκ��� �Է¹��� ��
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender
							, int age, String email, String phone, String address, String hobby) {
		
		// 1. VO ��ü�� ����
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		// 2. VO ��ü�� DAO Ŭ������ �ѱ�� ��� �ޱ�
		int result = new MemberDao().insertMember(m);
		
		// 3. ����� ���� ����ȭ�� ����
		if (result > 0) {
			new MemberView().displaySuccess("ȸ�� �߰� ����");
		} else {
			new MemberView().displayFail("ȸ�� �߰� ����");
		}
	}
	
	/**
	 * ��ü ȸ�� ��ȸ ��û�� ó�����ִ� �޼ҵ�
	 */
	public void selectAll() {
		
		// 1. Vo ��ü�� ���� -> �Է°� �����Ƿ� �н�
		
		// 2. DAO Ŭ���� ȣ���ϰ� ��� �ޱ�
		// => Member ��ü���� ��� ArrayList
		ArrayList<Member> list = new MemberDao().selectAll();
		
		// 3. ����� ���� ����ȭ�� ����
		if (list.isEmpty()) {
			new MemberView().displayNodata("���� �����ϴ� ȸ���� �����ϴ�.");
		} else {
			new MemberView().displayList(list);
		}
	}

	/**
	 * ȸ�� ���̵�� �˻� ��û�� ó�����ִ� �޼ҵ�
	 * @param userId : ����ڷκ��� �Է¹��� ���̵�
	 */
	public void selectByUserId(String userId) {
		
		// 1. Vo ��ü�� ���� -> �Է°��� �ϳ��̹Ƿ� �н�
		
		// 2. DAO Ŭ������ �ѱ�� ����ޱ�
		Member m = new MemberDao().selectByUserId(userId);
		
		// 3. ����� ���� ����ȭ�� ����
		if (m != null) {
			new MemberView().displayOne(m);
		} else {
			new MemberView().displayFail(userId +" �� ��ġ�ϴ� ����� �����ϴ�.");
		}
		
	}

	/**
	 * ȸ�� �̸� Ű����� �˻� ��û�� ó�����ִ� �޼ҵ�
	 * @param keyword : ����ڷκ��� �Է¹��� �̸� Ű����
	 */
	public void selectByUserName(String keyword) {
		
		// 1. VO ��ü�� ���� -> �Է°��� �ϳ��̹Ƿ� �н�
		
		// 2. DAO Ŭ���� ȣ���ϰ� ��� �ޱ�
		// => ����� ���� ���� �� �����Ƿ� ArrayList
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		// 3. ����� ���� ����ȭ�� ó��
		if (list.isEmpty()) {
			new MemberView().displayNodata(keyword +" �� �ش��ϴ� ����� �����ϴ�.");
		} else {
			new MemberView().displayList(list);
		}
	}
	
	public void updateMember(String userId, String userPwd, String email, String address, String hobby) {
		
		// VO ��ü ����
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setAddress(address);
		m.setHobby(hobby);
		
		// DAO ������ �ѱ��
		int result = new MemberDao().updateMember(m);
		
		// ����� ���� ���� ȭ�� ó��
		if (result > 0) {
			new MemberView().displaySuccess("���� ���� ����");
		} else {
			new MemberView().displayFail("���� ���� ����");
		}
	}
	
}
