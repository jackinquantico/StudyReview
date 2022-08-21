package com.kh.part_14.studentMVC.view;

import java.util.Scanner;

import com.kh.part_14.studentMVC.controller.StudentController;

public class StudentView {

	Scanner sc = new Scanner(System.in);
	StudentController scontrol = new StudentController();
	
	public void mainMenu() {
		
		while (true) {
			System.out.println("=== 학생 관리 메뉴 ===");
			System.out.println(" 1. 학생 정보 추가하기");
			System.out.println(" 2. 학생 정보 조회하기");
			System.out.println(" 3. 학생 정보 수정하기");
			System.out.println(" 4. 학생 정보 삭제하기");
			System.out.println(" 5. 학생 정보 검색하기");
			System.out.println(" 6. 국어 점수 평균 조회");
			System.out.println(" 0. 프로그램 종료하기");
			
			System.out.println("=================");
			System.out.print("메뉴 번호 입력 : ");
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
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
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
