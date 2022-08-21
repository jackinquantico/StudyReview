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
			System.out.println("=== 학생 관리 메뉴 ===");
			System.out.println(" 1. 학생 정보 추가하기");
			System.out.println(" 2. 학생 정보 조회하기");
			System.out.println(" 3. 학생 정보 수정하기");
			System.out.println(" 4. 학생 정보 삭제하기");
			System.out.println(" 5. 학생 정보 검색하기");
			System.out.println(" 6. 국어 점수 평균 조회");
			System.out.println(" 7. 영어 점수 평균 조회");
			System.out.println(" 8. 수학 점수 평균 조회");
			System.out.println(" 9. 전체 성적 평균 조회");
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
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
			System.out.println();
		}
	}
	
	public void insertStudent() {
		
		System.out.println("=== 학생 정보 추가 ===");
		
		// 학생 이름, 나이, 국어, 영어, 수학 점수 입력
		System.out.print("학생 이름 입력 : ");
		String name = sc.nextLine();
		System.out.print("학생 나이 입력 : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("국어 점수 입력 : ");
		int kor = sc.nextInt();
		sc.nextLine();
		System.out.print("영어 점수 입력 : ");
		int eng = sc.nextInt();
		sc.nextLine();
		System.out.print("수학 점수 입력 : ");
		int math = sc.nextInt();
		sc.nextLine();
		
		// insertStudent() 매개변수로 전달
		// 결과물 result 반환
		int result = scontrol.insertStudent(name, age, kor, eng, math);
		
		// 양수면 성공, 0이면 실패 출력
		if (result > 0) {
			System.out.println("성공적으로 추가되었습니다.");
		} else {
			System.out.println("추가에 실패했습니다.");
		}
		
	}
	
	public void selectStudentList() {
		
		System.out.println("=== 학생 정보 조회 ===");
		
		// selectStudentList() 호출
		// 결과물 list 반환
		ArrayList<Student> list = scontrol.selectStudentList();
		
		// list가 비어있으면 실패, list가 비어있지 않으면 for문으로 출력
		if (list.isEmpty()) {
			System.out.println("현재 학생 목록이 비어있습니다.");
		} else {
			for (Student s : list) {
				System.out.println(s);
			}
		}
		
	}
	
	public void updateStudent() {
		
		System.out.println("=== 학생 정보 수정 ===");
		
		// 수정할 학생 이름 입력
		// 수정내용 입력받기
		System.out.print("수정할 학생 이름 입력 : ");
		String name = sc.nextLine();
		System.out.print("수정내용 입력(이름) : ");
		String upName = sc.nextLine();
		System.out.print("수정내용 입력(나이) : ");
		int upAge = sc.nextInt();
		sc.nextLine();
		System.out.print("수정내용 입력(국어 점수) : ");
		int upKor = sc.nextInt();
		sc.nextLine();
		System.out.print("수정내용 입력(영어 점수) : ");
		int upEng = sc.nextInt();
		sc.nextLine();
		System.out.print("수정내용 입력(수학 점수) : ");
		int upMath = sc.nextInt();
		sc.nextLine();		
		
		// updateStudnt() 호출
		// 결과물 result 반환
		int result = scontrol.updateStudent(name, upName, upAge, upKor, upEng, upMath);
		
		// result가 0이면 실패, 양수면 성공 출력
		if (result > 0) {
			System.out.println("정보 수정에 성공했습니다.");
		} else {
			System.out.println("수정할 학생 정보가 존재하지 않습니다.");
		}
		
	}
	
	public void deleteStudent() {
		
		System.out.println("=== 학생 정보 삭제 ===");
		
		// 삭제할 학생 이름 입력
		System.out.print("삭제할 학생 이름 입력 : ");
		String name = sc.nextLine();
		
		// deleteStudent() 호출
		// 결과물 result 반환
		int result = scontrol.deleteStudent(name);
		
		// result 가 0이면 실패, 양수면 성공 출력
		if (result > 0) {
			System.out.println("정보 삭제에 성공했습니다.");
		} else {
			System.out.println("삭제할 학생 정보가 존재하지 않습니다.");
		}
				
	}
	
	public void searchStudent() {
		
		System.out.println("=== 학생 정보 검색 ===");
		
		// 검색할 키워드 입력
		System.out.print("검색할 학생 이름 키워드 입력 : ");
		String keyword = sc.nextLine();
		
		// searchStudent() 호출
		// 결과물 searched 반환
		ArrayList<Student> searched = scontrol.searchStudent(keyword);
		
		// list가 비어있으면 실패, 비어있지 않으면 for문으로 출력
		if (searched.isEmpty()) {
			System.out.println("해당 학생이 존재하지 않습니다.");
		} else {
			for (Student s : searched) {
				System.out.println(s);
			}
		}
		
	}
	
	public void averageKor() {
		
		System.out.println("=== 국어 점수 평균 ===");
		
		// averageKor() 호출
		// 결과물 average 반환
		double average = scontrol.averageKor();
		
		// average 출력
		System.out.println("국어 점수 평군 : "+(Math.round(average*100)/100.0));
	}
	
	public void averageEng() {
		
		System.out.println("=== 영어 점수 평균 ===");
		
		// averageEng() 호출
		// 결과물 average 반환
		double average = scontrol.averageEng();
		
		// average 출력
		System.out.println("영어 점수 평균 : "+(Math.round(average*100)/100.0));
	}
	
	public void averageMath() {
		
		System.out.println("=== 수학 점수 평균 ===");
		
		// averageMath() 호출
		// 결과물 average 반환
		double average = scontrol.averageMath();
		
		// average 출력
		System.out.println("수학 점수 평균 : "+(Math.round(average*100)/100.0));		
	}
	
	public void averageAll() {
		
		System.out.println("=== 전체 점수 평균 ===");
		
		// averageAll() 호출
		// 결과물 average 반환
		double average = scontrol.averageAll();
		
		// average 출력
		System.out.println("전체 점수 평균 : "+(Math.round(average*100)/100.0));
	}
	
}
