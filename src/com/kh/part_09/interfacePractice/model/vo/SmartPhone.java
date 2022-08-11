package com.kh.part_09.interfacePractice.model.vo;

public abstract class SmartPhone implements CellPhone, TouchDisplay {

	public SmartPhone() {
		
	}
	
	public abstract void printMaker();
	
	@Override
	public void makeacall() {
		System.out.println("번호를 누르고 통화버튼을 누름");		
	}
	
	@Override
	public void takeacall() {
		System.out.println("전화받기 버튼을 누름");		
	}
	
	@Override
	public void touch() {
		System.out.print("정전식");		
	}
	
	@Override
	public void charge() {
		System.out.print("고속 충전");
	}
}
