package com.kh.part_06.run;

import com.kh.part_06.model.vo.Circle;

public class CircleRun {

	public static void main(String[] args) {
		
		// �⺻ �����ڷ� ��ü ����
		Circle c = new Circle();
		
		// ���� �ѷ�, ���̰� ���
		c.getSizeOfCircle();
		c.getAreaOfCircle();
		
		// ������ 1 ���� ��
		c.incrementRadius();
		
		// ���� �ѷ�, ���̰� �����
		c.getSizeOfCircle();
		c.getAreaOfCircle();

	}

}
