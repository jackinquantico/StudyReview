package com.kh.part_08.hw1.controller;

import com.kh.part_08.hw1.model.vo.Person;

public class PersonController {
	
	// �ʵ��
	private Person[] p = new Person[5];
	
	// �޼ҵ��
	// insert
	// ������ �ְ� ��� ��ġ�� ������
	public void insert(Person any, int index) {
		p[index] = any;
	}
	
	// �ϳ��� select
	public Person select(int index) {
		return p[index];
	}
	
	// �� select
	public Person[] select() {
		return p;
	}
}
