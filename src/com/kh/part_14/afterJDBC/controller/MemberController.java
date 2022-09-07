package com.kh.part_14.afterJDBC.controller;

import java.util.ArrayList;

import com.kh.part_14.afterJDBC.model.dao.MemberDao;
import com.kh.part_14.afterJDBC.model.vo.Member;
import com.kh.part_14.afterJDBC.view.MemberView;

/*
 * * Controller
 * View�� ���ؼ� ��û�� ����� ó���ϴ� ���
 * �ش� �޼ҵ�� ���޵� �����͸� VO ��ü�� ����ó���� �� Dao �޼ҵ� ȣ�� �� ���� 
 * Dao �κ��� ��ȯ���� ����� ���� ����ڰ� ���� �� View (����ȭ��) �� ���� (View �� �޼ҵ� ȣ��)
 */

public class MemberController {

	
	/**
	 * ������� ȸ�� �߰� ��û�� ó�����ִ� �޼ҵ�
	 * @param userId ~ hobby : ȸ������ ��û �� ����ڰ� �Է��� ��
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender
							, int age, String email, String phone, String address, String hobby) {
		
		// 1. ���޵� �����͵��� VO ��ü�� ����ֱ� (����)
		// => �⺻ �����ڷ� ���� �� setter �޼ҵ�� �ʵ� ä��� / �Ű����� �����ڷ� �����ϱ�
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		// 2. VO ��ü Dao �޼ҵ�� �Ѱ��ֱ� (== Dao ���� �޼ҵ� ȣ��)
		// 3. ��� �ޱ�
		int result = new MemberDao().insertMember(m);
		// => �����̸� 1, ���и� 0
		
		// 4. ����� ���� ����ȭ�� ���� (== View ���� �޼ҵ� ȣ��)
		if (result > 0) { // �������� ���
			new MemberView().displaySuccess("ȸ�����Կ� �����߽��ϴ�.");
		} else { // �������� ���
			new MemberView().displayFail("ȸ�����Կ� �����߽��ϴ�.");
		}
		
	}
		
	/**
	 * ������� ȸ�� ��ü ��ȸ ��û�� ó�����ִ� �޼ҵ�
	 */
	public void selectAll() {
		
		// 1. ���޹��� ������ VO ��ü�� ����
		// => View �ܿ��� ����ڷκ��� �Է¹��� ���� �����Ƿ� �н�
		
		// 2. VO ��ü�� Dao ���� �޼ҵ忡 ��û (ȣ��)
		// 3. ��� �ޱ�
		// => Dao �޼ҵ� ȣ�� �� �����غ����ϴ� �� : �Ű����� X, ����Ÿ�� 
		// => ȸ�� ��ü ��ȸ (SELECT��) : ��� ���� ���� ȸ�� ������ ��ȸ�� => ArrayList<Member> �� �޾��� ��
		ArrayList<Member> list = new MemberDao().selectAll();
		// => list �� ��ȸ�� ȸ������ ������ �� ������� ��
		// => ���� �ƹ��� ��ȸ���� �ʾҴٸ� list.size () == 0, list.isEmpty() == true
				
		// 4. ����� ���� ����ȭ�� ����
		if (list.isEmpty()) { // ��ȸ ����� ���� ���
			new MemberView().displayNodata("��ü ��ȸ�� ����� �����ϴ�.");
			
		} else { // ��ȸ ����� ���� ���
			new MemberView().displayList(list);
		}
		
	}
	
	/**
	 * ������� ���̵�� �˻� ��û�� ó���ϴ� �޼ҵ� 
	 * @param userId : �˻��ϰ��� �ϴ� ���̵� ��
	 */
	public void selectByUserId(String userId) {
		
		// 1. ���޹��� ������ VO ��ü�� ����
		// => ���޹��� ���� �ϳ� ���̱� ������ �н�
		
		// 2. ���ް��� DAO�� �ѱ�鼭 �޼ҵ� ȣ��
		// 3. ����� �ޱ�
		// => DAO �޼ҵ带 ȣ���� �� ����ؾ� �� �� : �Ű����� userId, ����Ÿ�� Member
		// => USERID �� ���� ���� : UNIQUE => ���ƺ��� �� �ϳ��� ������� ���� ���̱� ������
		Member m = new MemberDao().selectByUserId(userId);
		// ��ȸ�� ȸ���� �ִٸ� Member ��ü, ���ٸ� null ���� �������
		
		// 4. ����� ���� ����ȭ�� ����
		if (m == null) { // ��ȸ ����� ���� ���
			new MemberView().displayNodata(userId + " �� �ش��ϴ� �˻� ����� �����ϴ�.");
		
		} else { // ��ȸ ����� �ִ� ���
			new MemberView().displayOne(m);
		}
		
	}
	
	/**
	 * ������� ȸ���� (Ű����) �˻� ��û�� ó���ϴ� �޼ҵ�
	 * @param keyword : �˻��ϰ��� �ϴ� �̸� Ű����
	 */
	public void selectByUserName(String keyword) {
		
		// 1. ���޹��� ������ VO ��ü�� ����
		// => ���޹��� ���� �ϳ� ���̱� ������ �н�
		
		// 2. ���ް��� DAO �� �ѱ�鼭 �޼ҵ� ȣ��
		// 3. ����� �ޱ�
		// => �Ű����� : keyword, ���ϰ��� Ÿ�� : ArrayList<Member>
		// => Ű���� �˻� ����� ���� ���� �� ����
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		// �� �������� �˻� ����� ������ list.size() == 0 �Ǵ� list.isEmpty == true		
		
		// 4. ����� ���� ����ȭ�� ����
		if (list.isEmpty()) { // ��ȸ ����� ���� ���
			new MemberView().displayNodata(keyword+" �� �ش��ϴ� �˻� ����� �����ϴ�.");
			
		} else { // ��ȸ ����� �ִ� ���
			new MemberView().displayList(list);
		}
	}
	
	/**
	 * ������� ȸ�� ���� ���� ��û �� ó�����ִ� �޼ҵ�
	 * @param userId : �����ϰ��� �ϴ� ȸ���� ���̵� (���п�)
	 * @param newPwd ~ newAddress : ������ ������ (��й�ȣ, �̸���, ��ȭ��ȣ, �ּ�)
	 */
	public void updateMember(String userId, String newPwd, String newEmail, String newPhone, String newAddress) {
		
		// 1. ��û �� ���޹��� ������ VO ��ü�� �����ϱ�
		Member m = new Member();
		
		m.setUserId(userId);
		m.setUserPwd(newPwd);
		m.setEmail(newEmail);
		m.setPhone(newPhone);
		m.setAddress(newAddress);
		
		// 2. ���ް��� DAO �� �޼ҵ�� �ѱ��
		// 3. ��� �ޱ�
		int result = new MemberDao().updateMember(m);
		
		// 4. ����� ���� ���� ȭ�� ����
		if (result > 0) { // ���� ����
			new MemberView().displaySuccess("ȸ�� ���� ���� ����");
		} else { // ���� ����
			new MemberView().displayFail("ȸ�� ���� ���� ����");
		}
		
	}
	
	/**
	 * ������� ȸ�� Ż�� ��û �� ó�����ִ� �޼ҵ�
	 * @param userId : �����ϰ��� �ϴ� ȸ���� ���̵� (���п�)
	 */
	public void deleteMember(String userId) {
		
		// 1. ��û �� ���޹��� ������ VO ��ü�� �����ϱ�
		// => ���޹��� ���� 1�� �̹Ƿ� �н�
		
		// 2. ���ް��� DAO�� �޼ҵ�� �ѱ��
		// 3. ��� �ޱ�
		int result = new MemberDao().deleteMember(userId);
		
		// 4. ����� ���� ���� ȭ�� ����
		if (result > 0) { // ���� ����
			new MemberView().displaySuccess("ȸ�� ���� ����");
		} else { // ���� ����
			new MemberView().displayFail("ȸ�� ���� ����");
		}
	}
		
}
