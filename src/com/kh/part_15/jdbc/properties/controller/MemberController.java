package com.kh.part_15.jdbc.properties.controller;

import java.util.ArrayList;

import com.kh.part_15.jdbc.properties.model.service.MemberService;
import com.kh.part_15.jdbc.properties.model.vo.Member;
import com.kh.part_15.jdbc.properties.view.MemberView;

public class MemberController {

	public void insertMember(String userId, String userPwd, String userName, String gender, int age, String email, String phone, String address, String hobby) {
		
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		int result = new MemberService().insertMember(m);
		
		if (result > 0) {
			new MemberView().displaySuccess("ȸ�� �߰� ����");
		} else {
			new MemberView().displayFail("ȸ�� �߰� ����");
		}
	}
	
	public void selectAll() {
		
		ArrayList<Member> list = new MemberService().selectAll();
		
		if (list.isEmpty()) {
			new MemberView().displayNodata("��ȸ�� �����Ͱ� �����ϴ�.");
		} else {
			new MemberView().displayList(list);
		}
	}
	
	public void selectByUserId(String userId) {
		
		Member m = new MemberService().selectByUserId(userId);
		
		if (m != null) {
			new MemberView().displayOne(m);
		} else {
			new MemberView().displayNodata(userId+" �� �ش��ϴ� ȸ���� �����ϴ�.");		
		}
	}
	
	public void selectByUserName(String keyword) {
		
		ArrayList<Member> list = new MemberService().selectByUserName(keyword);
		
		if (list.isEmpty()) {
			new MemberView().displayNodata(keyword+" �� �ش��ϴ� ȸ���� �����ϴ�.");
		} else {
			new MemberView().displayList(list);
		}
	}
	
	public void updateMember(String userId, String userPwd, String email, String phone) {
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		
		int result = new MemberService().updateMember(m);
		
		if (result > 0) {
			new MemberView().displaySuccess("���� ���� ����");
		} else {
			new MemberView().displayFail("���� ���� ����");
		}
	}
	
	public void deleteMember(String userId) {
		
		int result = new MemberService().deleteMember(userId);
		
		if (result > 0) {
			new MemberView().displaySuccess("ȸ�� Ż�� ����");
		} else {
			new MemberView().displayFail("ȸ�� Ż�� ����");
		}
	}
	
	public void selectByEnrollDate(String enrollDate) {
		
		ArrayList<Member> list = new MemberService().selectByEnrollDate(enrollDate);
		
		if (list.isEmpty()) {
			new MemberView().displayNodata(enrollDate + " �� ������ ȸ���� �����ϴ�.");
		} else {
			new MemberView().displayList(list);
		}
	}
}
