package com.kh.part_14.studentMVC.view;

import java.util.Scanner;

import com.kh.part_14.studentMVC.controller.StudentController;

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
		
	}
	
	public void selectStudentList() {
		
	}
	
	public void updateStudent() {
		
	}
	
	public void deleteStudent() {
		
	}
	
	public void searchStudent() {
		
	}
	
	public void averageKor() {
		
	}
	
}
