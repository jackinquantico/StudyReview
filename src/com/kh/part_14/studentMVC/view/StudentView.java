package com.kh.part_14.studentMVC.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.studentMVC.controller.StudentController;
import com.kh.part_14.studentMVC.model.vo.Student;

public class StudentView {

	Scanner sc = new Scanner(System.in);
	StudentController scontrol = new StudentController();
	
	public void mainMenu() {
		
		while (true) {
			System.out.println("=== �л� ���� �޴� ===");
			System.out.println(" 1. �л� ���� �߰��ϱ�");
			System.out.println(" 2. �л� ���� ��ȸ�ϱ�");
			System.out.println(" 3. �л� ���� �����ϱ�");
			System.out.println(" 4. �л� ���� �����ϱ�");
			System.out.println(" 5. �л� ���� �˻��ϱ�");
			System.out.println(" 6. ���� ���� ��� ��ȸ");
			System.out.println(" 7. ���� ���� ��� ��ȸ");
			System.out.println(" 8. ���� ���� ��� ��ȸ");
			System.out.println(" 9. ��ü ���� ��� ��ȸ");
			System.out.println(" 0. ���α׷� �����ϱ�");
			
			System.out.println("=================");
			System.out.print("�޴� ��ȣ �Է� : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				insertStudent();
				break;
			case 2:
				selectStudentList();
				break;
			case 3:
				updateStudent();
				break;
			case 4:
				deleteStudent();
				break;
			case 5:
				searchStudent();
				break;
			case 6:
				averageKor();
				break;
			case 7:
				averageEng();
				break;
			case 8:
				averageMath();
				break;
			case 9:
				averageAll();
				break;
			case 0:
				System.out.println("���α׷��� �����մϴ�.");
				return;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
			
			System.out.println();
		}
	}
	
	public void insertStudent() {
		
		System.out.println("=== �л� ���� �߰� ===");
		
		// �л� �̸�, ����, ����, ����, ���� ���� �Է�
		System.out.print("�л� �̸� �Է� : ");
		String name = sc.nextLine();
		System.out.print("�л� ���� �Է� : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("���� ���� �Է� : ");
		int kor = sc.nextInt();
		sc.nextLine();
		System.out.print("���� ���� �Է� : ");
		int eng = sc.nextInt();
		sc.nextLine();
		System.out.print("���� ���� �Է� : ");
		int math = sc.nextInt();
		sc.nextLine();
		
		// insertStudent() �Ű������� ����
		// ����� result ��ȯ
		int result = scontrol.insertStudent(name, age, kor, eng, math);
		
		// ����� ����, 0�̸� ���� ���
		if (result > 0) {
			System.out.println("���������� �߰��Ǿ����ϴ�.");
		} else {
			System.out.println("�߰��� �����߽��ϴ�.");
		}
		
	}
	
	public void selectStudentList() {
		
		System.out.println("=== �л� ���� ��ȸ ===");
		
		// selectStudentList() ȣ��
		// ����� list ��ȯ
		ArrayList<Student> list = scontrol.selectStudentList();
		
		// list�� ��������� ����, list�� ������� ������ for������ ���
		if (list.isEmpty()) {
			System.out.println("���� �л� ����� ����ֽ��ϴ�.");
		} else {
			for (Student s : list) {
				System.out.println(s);
			}
		}
		
	}
	
	public void updateStudent() {
		
		System.out.println("=== �л� ���� ���� ===");
		
		// ������ �л� �̸� �Է�
		// �������� �Է¹ޱ�
		System.out.print("������ �л� �̸� �Է� : ");
		String name = sc.nextLine();
		System.out.print("�������� �Է�(�̸�) : ");
		String upName = sc.nextLine();
		System.out.print("�������� �Է�(����) : ");
		int upAge = sc.nextInt();
		sc.nextLine();
		System.out.print("�������� �Է�(���� ����) : ");
		int upKor = sc.nextInt();
		sc.nextLine();
		System.out.print("�������� �Է�(���� ����) : ");
		int upEng = sc.nextInt();
		sc.nextLine();
		System.out.print("�������� �Է�(���� ����) : ");
		int upMath = sc.nextInt();
		sc.nextLine();		
		
		// updateStudnt() ȣ��
		// ����� result ��ȯ
		int result = scontrol.updateStudent(name, upName, upAge, upKor, upEng, upMath);
		
		// result�� 0�̸� ����, ����� ���� ���
		if (result > 0) {
			System.out.println("���� ������ �����߽��ϴ�.");
		} else {
			System.out.println("������ �л� ������ �������� �ʽ��ϴ�.");
		}
		
	}
	
	public void deleteStudent() {
		
		System.out.println("=== �л� ���� ���� ===");
		
		// ������ �л� �̸� �Է�
		System.out.print("������ �л� �̸� �Է� : ");
		String name = sc.nextLine();
		
		// deleteStudent() ȣ��
		// ����� result ��ȯ
		int result = scontrol.deleteStudent(name);
		
		// result �� 0�̸� ����, ����� ���� ���
		if (result > 0) {
			System.out.println("���� ������ �����߽��ϴ�.");
		} else {
			System.out.println("������ �л� ������ �������� �ʽ��ϴ�.");
		}
				
	}
	
	public void searchStudent() {
		
		System.out.println("=== �л� ���� �˻� ===");
		
		// �˻��� Ű���� �Է�
		System.out.print("�˻��� �л� �̸� Ű���� �Է� : ");
		String keyword = sc.nextLine();
		
		// searchStudent() ȣ��
		// ����� searched ��ȯ
		ArrayList<Student> searched = scontrol.searchStudent(keyword);
		
		// list�� ��������� ����, ������� ������ for������ ���
		if (searched.isEmpty()) {
			System.out.println("�ش� �л��� �������� �ʽ��ϴ�.");
		} else {
			for (Student s : searched) {
				System.out.println(s);
			}
		}
		
	}
	
	public void averageKor() {
		
		System.out.println("=== ���� ���� ��� ===");
		
		// averageKor() ȣ��
		// ����� average ��ȯ
		double average = scontrol.averageKor();
		
		// average ���
		System.out.println("���� ���� �� : "+(Math.round(average*100)/100.0));
	}
	
	public void averageEng() {
		
		System.out.println("=== ���� ���� ��� ===");
		
		// averageEng() ȣ��
		// ����� average ��ȯ
		double average = scontrol.averageEng();
		
		// average ���
		System.out.println("���� ���� ��� : "+(Math.round(average*100)/100.0));
	}
	
	public void averageMath() {
		
		System.out.println("=== ���� ���� ��� ===");
		
		// averageMath() ȣ��
		// ����� average ��ȯ
		double average = scontrol.averageMath();
		
		// average ���
		System.out.println("���� ���� ��� : "+(Math.round(average*100)/100.0));		
	}
	
	public void averageAll() {
		
		System.out.println("=== ��ü ���� ��� ===");
		
		// averageAll() ȣ��
		// ����� average ��ȯ
		double average = scontrol.averageAll();
		
		// average ���
		System.out.println("��ü ���� ��� : "+(Math.round(average*100)/100.0));
	}
	
}
