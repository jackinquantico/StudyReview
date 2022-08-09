package com.kh.part_09.polyPractice.controller;

import com.kh.part_09.polyPractice.model.vo.Electronic;

public class ElectronicController {
	
	// 필드부
	// 물건을 넣을 3칸짜리 박스
	Electronic[] elec = new Electronic[3];
	
	// 메소드부
	// setter 역할 : Electronic 객체를 주고 어디에 넣을 것인지
	public void insert(Electronic any, int index) {
		elec[index] = any;
	}
	
	// getter역할 : Electonic 객체를 어디서 꺼내올 것인지
	public Electronic select(int index) {
		return elec[index];
	}
	
	// 배열째 모두 다 꺼내는 getter
	public Electronic[] select() {
		return elec;
	}
	
}
