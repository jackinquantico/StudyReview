package com.kh.part_08.hw1.controller;

import com.kh.part_08.hw1.model.vo.Person;

public class PersonController {
	
	// 필드부
	private Person[] p = new Person[5];
	
	// 메소드부
	// insert
	// 무엇을 주고 어느 위치에 넣을지
	public void insert(Person any, int index) {
		p[index] = any;
	}
	
	// 하나만 select
	public Person select(int index) {
		return p[index];
	}
	
	// 다 select
	public Person[] select() {
		return p;
	}
}
