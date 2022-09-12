package com.kh.part_15.jdbc.preparedStatement.controller;

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
}
