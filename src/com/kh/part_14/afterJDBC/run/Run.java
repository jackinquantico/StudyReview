package com.kh.part_14.afterJDBC.run;

import com.kh.part_14.afterJDBC.view.MemberView;

public class Run {

	/*
	 * * MVC ����
	 * M : Model, ������ ó�� ��� (�����͵��� ������� VO, �����͵��� ������ ������ ���� �������ִ� DAO)
	 * V : View, ȭ���� ��� (����ڰ� ���� �ð����� ���, ��� �� �Է�)
	 * C : Controller, ������� ��û�� ��� (������� ��û�� ó�� �� �ش�Ǵ� ���� ȭ�� ����)
	 */
	
	public static void main(String[] args) {
		
		// ���α׷� ���ุ�� ���
		// ����ڰ� ���� �� ù ȭ���� ����ִ� ����
		// MemberView mv = new MemberView();
		// mv.mainMenu();
		
		// ������ ���� �ʰ� ������ ���ÿ� ȣ��
		new MemberView().mainMenu();

	}

}
