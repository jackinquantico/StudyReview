package com.kh.part_09.polyPractice.controller;

import com.kh.part_09.polyPractice.model.vo.Electronic;

public class ElectronicController {
	
	// �ʵ��
	// ������ ���� 3ĭ¥�� �ڽ�
	Electronic[] elec = new Electronic[3];
	
	// �޼ҵ��
	// setter ���� : Electronic ��ü�� �ְ� ��� ���� ������
	public void insert(Electronic any, int index) {
		elec[index] = any;
	}
	
	// getter���� : Electonic ��ü�� ��� ������ ������
	public Electronic select(int index) {
		return elec[index];
	}
	
	// �迭° ��� �� ������ getter
	public Electronic[] select() {
		return elec;
	}
	
}
