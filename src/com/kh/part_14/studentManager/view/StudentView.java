package com.kh.part_14.studentManager.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.studentManager.controller.StudentController;
import com.kh.part_14.studentManager.model.vo.Student;

public class StudentView {
	
	private Scanner sc = new Scanner(System.in);
	private StudentController stc = new StudentController();

	public void mainMenu() {
		
		while(true) {
		
			System.out.println("== �л����� �ý��� ==");
			System.out.println("1. �л� ��ü ���� ���");
			System.out.println("2. �л� ���� ��ȸ(�й�)");
			System.out.println("3. �л� ���� �Է�");
			System.out.println("4. �л� ���� ����");
			System.out.println("5. �л� ����");
			System.out.println("0. ���α׷� ����");
			System.out.println("=================");
			System.out.print("�޴� ��ȣ �Է� : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				selectAll();
				break;
			case 2:
				selectOne();
				break;
			case 3:
				insertStudent();
				break;
			case 4:
				updateStudent();
				break;
			case 5:
				deleteStudent();
				break;
			case 0:
				System.out.println("���α׷��� �����մϴ�.");
				return;
			default:
				System.out.println("�߸��� �����Դϴ�. �ٽ� �Է����ּ���.");
				break;
			}
			
			System.out.println();
		
		}
	}
	
	public void selectAll() {
		
		System.out.println("�л� ��ü ������ ����մϴ�.");
		
		ArrayList<Student> list = stc.selectAll();
		
		if (list.isEmpty()) {
			System.out.println("���� ����Ʈ�� �л� ������ �������� �ʽ��ϴ�.");
		} else {
			for (Student s : list) {
				System.out.println(s);
			}
		}
		
	}
	
	public void selectOne() {
		
		System.out.print("�л� �й��� �Է��ϼ��� : ");
		int classNumber = sc.nextInt();
		sc.nextLine();
		
		Student tmp = stc.selectOne(classNumber);
		
		System.out.println(tmp);
		
	}
	
	public void insertStudent() {
		
		System.out.println("�л� ������ �Է��մϴ�.");
		System.out.print("�л� �й� �Է� : ");
		int classNumber = sc.nextInt();
		sc.nextLine();
		System.out.print("�л� �̸� �Է� : ");
		String name = sc.nextLine();
		System.out.print("�л� ���� �Է� : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("�л� �ּ� �Է� : ");
		String address = sc.nextLine();
		System.out.print("�л� ���� �Է� : ");
		double grade = sc.nextDouble();
		sc.nextLine();
		
		Student tmp = new Student(classNumber, name, age, address, grade);
		
		stc.insertStudent(tmp);
		
		System.out.println("�л� ������ �߰��߽��ϴ�.");
		
	}
	
	public void updateStudent() {
		
		System.out.println("�л� ������ �����մϴ�.");
		System.out.print("������ ���� �Է� : ");
		String info = sc.nextLine();
		
		int result = 0;
		
		if (info.equals("�й�")) {
			System.out.print("������ �л� �̸� �Է� : ");
			String name = sc.nextLine();
			System.out.print("������ �й� �Է� : ");
			int classNumber = sc.nextInt();
			sc.nextLine();
			
			result = stc.updateStudent(name,classNumber);
			
		} else if (info.equals("�̸�")) {
			System.out.print("������ �л� �й� �Է� : ");
			int classNumber = sc.nextInt();
			sc.nextLine();
			System.out.print("������ �̸� �Է� : ");
			String name = sc.nextLine();
			
			result = stc.updateStudent(classNumber, name);
			
		} else if (info.equals("����")) {
			System.out.print("������ �л� �й� �Է� : ");
			int classNumber = sc.nextInt();
			sc.nextLine();
			System.out.print("������ ���� �Է� : ");
			int age = sc.nextInt();
			sc.nextLine();
			
			result = stc.updateStudent(classNumber, age);
			
		} else if (info.equals("�ּ�")) {
			System.out.print("������ �л� �̸� �Է� : ");
			String name = sc.nextLine();
			System.out.print("������ �ּ� �Է� : ");
			String address = sc.nextLine();
			
			result = stc.updateStudent(name, address);
			
		} else if (info.equals("����")) {
			System.out.print("������ �л� �й� �Է� : ");
			int classNumber = sc.nextInt();
			sc.nextLine();
			System.out.print("������ ���� �Է� : ");
			double grade = sc.nextDouble();
			sc.nextLine();
			
			result = stc.updateStudent(classNumber, grade);
			
		} else {
			System.out.println("�Է��� �������� �������� �ʽ��ϴ�.");
			return;
		}
		
		if (result > 0) {
			System.out.println("������ ���������� �Ϸ�Ǿ����ϴ�.");
		} else {
			System.out.println("������ �л��� �������� �ʽ��ϴ�.");
		}
		
	}
	
	public void deleteStudent() {
	
		System.out.println("�л� ������ �����մϴ�.");
		System.out.print("������ �л� �й� �Է� : ");
		int classNumber = sc.nextInt();
		sc.nextLine();
		
		int result = stc.deleteStudent(classNumber);
		
		if (result > 0) {
			System.out.println("������ �����߽��ϴ�.");
		} else {
			System.out.println("������ �л� ������ �������� �ʽ��ϴ�.");
		}
		
	}
}
