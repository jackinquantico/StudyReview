package com.kh.part_07.run;

import java.util.ArrayList;

import com.kh.part_07.model.vo.Phone;


public class PhoneArrayListRun {

	public static void main(String[] args) {
		
		// ArrayList����
		ArrayList<Phone> list = new ArrayList<>(3);
		
		list.add(new Phone());
		list.add(new Phone("������s", "10", "�Ｚ", 1000000));
		list.add(new Phone("������", "11����", "����", 800000));
		
		// ���� for������ ���
		for (Phone p : list) {
			System.out.println(p.information());
		}

	}

}
