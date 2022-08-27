package com.kh.part_14.studentManager.view;

import java.util.Scanner;

import com.kh.part_14.studentManager.controller.StudentController;

public class StudentView {
	
	private Scanner sc = new Scanner(System.in);
	private StudentController stc = new StudentController();

	public void mainMenu() {
		
		while(true) {
		
			System.out.println("== 학생관리 시스템 ==");
			System.out.println("1. 학생 전체 정보 출력");
			System.out.println("2. 학생 정보 조회(학번)");
			System.out.println("3. 학생 정보 입력");
			System.out.println("4. 학생 정보 변경");
			System.out.println("5. 학생 삭제");
			System.out.println("0. 프로그램 종료");
			System.out.println("=================");
			System.out.print("메뉴 번호 입력 : ");
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
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 숫자입니다. 다시 입력해주세요.");
				break;
			}
			
			System.out.println();
		
		}
	}
	
	public void selectAll() {
		
		System.out.println("학생 전체 정보를 출력합니다.");
		
		
	}
	
	public void selectOne() {
		
		System.out.print("학생 학번을 입력하세요 : ");
		int classNumber = sc.nextInt();
		sc.nextLine();
		
	}
	
	public void insertStudent() {
		
		System.out.println("학생 정보를 입력합니다.");
		System.out.print("학생 학번 입력 : ");
		int classNumber = sc.nextInt();
		sc.nextLine();
		System.out.print("학생 이름 입력 : ");
		String name = sc.nextLine();
		System.out.print("학생 나이 입력 : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("학생 주소 입력 : ");
		String address = sc.nextLine();
		System.out.print("학생 학점 입력 : ");
		double grade = sc.nextDouble();
		sc.nextLine();
		
	}
	
	public void updateStudent() {
		
		System.out.println("학생 정보를 변경합니다.");
		System.out.print("변경할 정보 입력 : ");
		String info = sc.nextLine();
		
		int result = 0;
		
		if (info.equals("학번")) {
			System.out.print("변경할 학생 이름 입력 : ");
			String name = sc.nextLine();
			System.out.print("변경할 학번 입력 : ");
			int classNumber = sc.nextInt();
			sc.nextLine();
			
		} else if (info.equals("이름")) {
			System.out.print("변경할 학생 학번 입력 : ");
			int classNumber = sc.nextInt();
			sc.nextLine();
			System.out.print("변경할 이름 입력 : ");
			String name = sc.nextLine();
			
		} else if (info.equals("나이")) {
			System.out.print("변경할 학생 학번 입력 : ");
			int classNumber = sc.nextInt();
			sc.nextLine();
			System.out.print("변경할 나이 입력 : ");
			int age = sc.nextInt();
			sc.nextLine();
			
		} else if (info.equals("주소")) {
			System.out.print("변경할 학생 학번 입력 : ");
			int classNumber = sc.nextInt();
			sc.nextLine();
			System.out.print("변경할 주소 입력 : ");
			String address = sc.nextLine();
			
		} else if (info.equals("학점")) {
			System.out.print("변경할 학생 학번 입력 : ");
			int classNumber = sc.nextInt();
			sc.nextLine();
			System.out.print("변경할 학점 입력 : ");
			int grade = sc.nextInt();
			sc.nextLine();
			
		} else {
			System.out.println("입력한 정보란이 존재하지 않습니다.");
			return;
		}
		
		if (result > 0) {
			System.out.println("변경이 성공적으로 완료되었습니다.");
		} else {
			System.out.println("변경할 학생이 존재하지 않습니다.");
		}
		
	}
	
	public void deleteStudent() {
	
		System.out.println("학생 정보를 삭제합니다.");
		System.out.print("삭제할 학생 학번 입력 : ");
		int classNumber = sc.nextInt();
		sc.nextLine();
		
	}
}
