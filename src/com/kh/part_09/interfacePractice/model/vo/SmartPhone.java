package com.kh.part_09.interfacePractice.model.vo;

public abstract class SmartPhone implements CellPhone, TouchDisplay {

	public SmartPhone() {
		
	}
	
	public abstract void printMaker();
	
	@Override
	public void makeacall() {
		System.out.println("��ȣ�� ������ ��ȭ��ư�� ����");		
	}
	
	@Override
	public void takeacall() {
		System.out.println("��ȭ�ޱ� ��ư�� ����");		
	}
	
	@Override
	public void touch() {
		System.out.print("������");		
	}
	
	@Override
	public void charge() {
		System.out.print("��� ����");
	}
}
