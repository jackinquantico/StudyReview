package com.kh.part_09.interfacePractice.model.vo;

public class GalaxyNote9 extends SmartPhone {

	public GalaxyNote9() {
		
	}


	@Override
	public void picture() {
		System.out.println("1300�� ���ī�޶�");
	}
	
	@Override
	public void charge() {
		super.charge();
		System.out.println(", ��� ���� ����");		
	}

	@Override
	public void touch() {
		super.touch();
		System.out.println(", ������ ����");		
	}

	@Override
	public void printMaker() {
		System.out.println("������ ��Ʈ 9�� �Ｚ ���� ����� ����.");
	}

	
}
