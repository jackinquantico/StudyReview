package com.kh.part_06.run;

import com.kh.part_06.model.vo.Product;

public class ObjectRun {

	public static void main(String[] args) {
		
		Product p1 = new Product("ssgnote9", "�����ó�Ʈ9", "��⵵ ����", 960000,10.0);
		Product p2 = new Product("lgxnote5", "LG����Ʈ��5", "��⵵ ����", 780000, 0.7);
		Product p3 = new Product("ktsnote3", "KT����Ʈ��3", "����� ����", 250000, 0.3);
		
		System.out.println(p1.information());
		System.out.println(p2.information());
		System.out.println(p3.information());
		
		System.out.println("=============================");
		
		p1.setPrice(1200000);
		p1.setTax(0.05);
		p2.setPrice(1200000);
		p2.setTax(0.05);
		p3.setPrice(1200000);
		p3.setTax(0.05);
		
		System.out.println(p1.information());
		System.out.println(p2.information());
		System.out.println(p3.information());
		
		System.out.println("=============================");
		
		System.out.println("��ǰ�� : "+p1.getProductName());
		System.out.println("�ΰ��� ���� ���� : "+(p1.getPrice()+(int)(p1.getPrice()*p1.getTax()))+"��");
		
		System.out.println("��ǰ�� : "+p2.getProductName());
		System.out.println("�ΰ��� ���� ���� : "+(p2.getPrice()+(int)(p2.getPrice()*p2.getTax()))+"��");
		
		System.out.println("��ǰ�� : "+p3.getProductName());
		System.out.println("�ΰ��� ���� ���� : "+(p3.getPrice()+(int)(p3.getPrice()*p3.getTax()))+"��");
		
	}
}
