package com.kh.part_09.polyPractice.run;

import com.kh.part_09.polyPractice.controller.ElectronicController;
import com.kh.part_09.polyPractice.model.vo.Desktop;
import com.kh.part_09.polyPractice.model.vo.Electronic;
import com.kh.part_09.polyPractice.model.vo.NoteBook;
import com.kh.part_09.polyPractice.model.vo.Tablet;

public class ElectronicRun {

	public static void main(String[] args) {
		
		// ��Ʈ�ѷ� ��ü ����
		ElectronicController ec = new ElectronicController();
		
		// �������� ec ���ؼ� �迭 �ε������� ��ü �����ϱ�
		ec.insert(new Desktop("a", "b", 1, "c"), 0);
		ec.insert(new NoteBook("��", "��", 1, 2), 1);
		ec.insert(new Tablet("1", "2", 1, false), 2);
		
		// ���
		Desktop d = (Desktop)(ec.select(0)); // �ٿ�ĳ����
		System.out.println(d);
		
		// ���2
		System.out.println(ec.select(1));
		
		// �ݺ������� ���� ������ 1
		Electronic[] elec = ec.select();
		// select() �� Electronic ��ü �迭�� ��ȯ�ϹǷ�
		// ���� Ÿ���� Electronic ��ü �迭�� �޾��� �� ����.
		
		for (int i=0; i<elec.length; i++) {
			System.out.println(elec[i]);
		}
		
		// �ݺ������� ���� ������ 2
		for (int i=0; i<3; i++) {
			System.out.println(ec.select()[i]);
		}

	}

}
