package com.kh.part_09.abstractPractice.run;

import com.kh.part_09.abstractPractice.model.vo.Animal;
import com.kh.part_09.abstractPractice.model.vo.Cat;
import com.kh.part_09.abstractPractice.model.vo.Dog;

public class AnimalManager {

	public static void main(String[] args) {
		
		Animal[] ani = new Animal[5];
		
		ani[0] = new Cat("������", "�ڼ�", "�б� ��ó", "�����");
		ani[1] = new Dog("�ٵ���", "������", 6);
		ani[2] = new Dog("�۸���", "�㽺Ű", 13);
		ani[3] = new Cat("�Ŀ���", "����", "��", "�Ͼ��");
		ani[4] = new Cat("����", "�", "��", "����");
		
		for (int i=0; i<ani.length; i++) {
			ani[i].speak();
		}
		
	}

}
