package com.kh.part_12.stream.assist.object.model.vo;

import java.io.Serializable;

//����ȭ ������ ���� ������ ��ü�� ���Ϸ� ������ �� NotSerializableException �߻�
//=> 1byte ��η� ū ��ü�� ������ �� ���� ������ ���η� �迭���ִ� ���� : ����ȭ
public class Phone implements Serializable { // ����ȭ ����

	// �ʵ��
	private String name;
	private int price;
	
	// �����ں�
	public Phone() {
		
	}
	
	public Phone(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	// �޼ҵ��
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return "name : "+name+", price : "+price;
	}
	
}
