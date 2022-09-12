package com.kh.part_15.jdbc.preparedStatement.controller;

import java.awt.List;
import java.util.ArrayList;

import com.kh.part_15.jdbc.preparedStatement.model.service.PreService;
import com.kh.part_15.jdbc.preparedStatement.model.vo.PreMember;
import com.kh.part_15.jdbc.preparedStatement.view.PreView;

public class PreController {
	
	public void insertMember(String userId, String userPwd, String userName, String gender, int age, String email, String phone, String address, String hobby) {
		
		PreMember m = new PreMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		int result = new PreService().insertMember(m);
		
		if (result > 0) {
			new PreView().displaySuccess("ȸ�� �߰� ����");
		} else {
			new PreView().displayFail("ȸ�� �߰� ����");
		}
	}
	
	public void selectAll() {
		
		ArrayList<PreMember> list = new PreService().selectAll();
		
		if (list.isEmpty()) {
			new PreView().displayNodata("��ȸ�� ����� �����ϴ�.");
		} else {
			new PreView().displayList(list);
		}
	}
	
	public void selectByUserId(String userId) {
		
		PreMember m = new PreService().selectByUserId(userId);
		
		if (m != null) {
			new PreView().displayOne(m);
		} else {
			new PreView().displayFail(userId +" �� �ش��ϴ� ����� �����ϴ�.");
		}
	}
	
	public void selectByUserName(String keyword) {
		
		ArrayList<PreMember> list = new PreService().selectByUserName(keyword);
		
		if (list.isEmpty()) {
			new PreView().displayNodata(keyword + " �� �ش��ϴ� ����� �����ϴ�.");
		} else {
			new PreView().displayList(list);
		}
	}
	
	public void updateMember(String userId, String userPwd, String email, String phone, String address, String hobby) {
		
		PreMember m = new PreMember();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setHobby(hobby);
		
		int result = new PreService().updateMember(m);
		
		if (result > 0) {
			new PreView().displaySuccess("���� ���� ����");
		} else {
			new PreView().displayFail("���� ���� ����");
		}
	}
	
	public void deleteMember(String userId) {
		
		int result = new PreService().deleteMember(userId);
		
		if (result > 0) {
			new PreView().displaySuccess("ȸ�� Ż�� ����");
		} else {
			new PreView().displayFail("ȸ�� Ż�� ����");
		}
	}
}
