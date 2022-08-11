package com.kh.part_09.interfacePractice.model.vo;

public class V40 extends SmartPhone {
	
	public V40() {
		
	}

	@Override
	public void picture() {
		System.out.println("1200, 1600만 화소 트리플 카메라");
	}
	
	@Override
	public void printMaker() {
		System.out.println("V40는 LG에서 만들어 졌다");
	}
	
	@Override
	public void charge() {
		super.charge();
		System.out.println();
	}
	
	@Override
	public void touch() {
		super.touch();
		System.out.println();
	}
}
