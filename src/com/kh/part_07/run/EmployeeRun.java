package com.kh.part_07.run;

import com.kh.part_07.model.vo.Employee;

public class EmployeeRun {

	public static void main(String[] args) {
		
		// ũ�Ⱑ 3�� ��ü �迭
		Employee[] e = new Employee[3];
		
		// 0���� �⺻ ������, 1���� �Ű�����6�� ������, 2���� �Ű����� 10�� ������
		e[0] = new Employee();
		e[1] = new Employee(1, "ȫ�浿", 19, 'M', "01022223333", "���� ���");
		e[2] = new Employee(2, "������", "������", "����", 20, 'F', 1000000, 0.01, "01011112222","���� ����");
		
		// ���
		for (int i=0; i<e.length; i++) {
			System.out.println(e[i].information());
		}
		
		System.out.println("===========================================================");
		
		// ���� ���� �ʵ忡 �� �־ �ٽ� ���
		e[0].setEmpNo(0);
		e[0].setEmpName("�踻��");
		e[0].setDept("������");
		e[0].setJob("����");
		e[0].setAge(30);
		e[0].setGender('M');
		e[0].setSalary(3000000);
		e[0].setBonusPoint(0.2);
		e[0].setPhone("01055559999");
		e[0].setAddress("���� ����");
		
		e[1].setDept("��ȹ��");
		e[1].setJob("����");
		e[1].setSalary(4000000);
		e[1].setBonusPoint(0.3);
		
		System.out.println(e[0].information());
		System.out.println(e[1].information());
		
		// ���ʽ��� ����� 1�� ���� ����ؼ� ���
		// ���ʽ� ���� = (�޿� + (�޿� * ���ʽ�����Ʈ)) * 12
		// ��� ������ ���ϱ� ���� sum ���� ����
		
		int sum = 0;
		
		for (int i=0; i<e.length; i++) {
			int year = (int)(e[i].getSalary() + (e[i].getSalary() * e[i].getBonusPoint())) * 12;
			System.out.println(e[i].getEmpName()+"�� ���� : "+year+"��");
			sum += year;
		}
		
		System.out.println("===========================================================");
		
		System.out.println("�������� ������ ��� : "+(sum/e.length)+"��");
		
	}

}
