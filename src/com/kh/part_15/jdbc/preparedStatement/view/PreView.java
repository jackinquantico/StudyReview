package com.kh.part_15.jdbc.preparedStatement.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_15.jdbc.preparedStatement.controller.PreController;
import com.kh.part_15.jdbc.preparedStatement.model.vo.PreMember;

public class PreView {
	
	private Scanner sc = new Scanner(System.in);
	private PreController pc = new PreController();
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("===== 회원 관리 프로그램 =====");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디로 검색");
			System.out.println("4. 회원 이름 키워드 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.println("-------------------------");
			System.out.print(">> 이용할 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				insertMember();
				break;
			case 2:
				selectAll();
				break;
			case 3:
				selectByUserId();
				break;
			case 4:
				selectByUserName();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
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
	
	public void insertMember() {
		
		System.out.println("----- 회원 추가 -----");
		
		System.out.print("아이디 입력 : ");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("성별 (M/F) : ");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("휴대폰 (숫자만) : ");
		String phone = sc.nextLine();
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		System.out.print("취미 (, 로 공백 없이 나열) : ");
		String hobby = sc.nextLine();
		
		pc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
	}

	public void selectAll() {
		
		System.out.println("----- 전체 조회 -----");
		
		pc.selectAll();
	}
	
	public void selectByUserId() {
		
		System.out.println("----- 아이디로 검색 -----");
		
		System.out.print("아이디 입력 : ");
		String userId = sc.nextLine();
		
		pc.selectByUserId(userId);
	}
	
	public void selectByUserName() {
		
		System.out.println("----- 이름 키워드 검색 -----");
		
		System.out.print("키워드 입력 : ");
		String keyword = sc.nextLine();
		
		pc.selectByUserName(keyword);
	}
	
	public void updateMember() {
		
		System.out.println("----- 회원 정보 변경 -----");
		
		System.out.print("변경할 회원 아이디 입력 : ");
		String userId = sc.nextLine();
		
		System.out.print("변경할 비밀번호 입력 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("변경할 이메일 입력 : ");
		String email = sc.nextLine();
		
		System.out.print("변경할 핸드폰 번호 입력 : ");
		String phone = sc.nextLine();
		
		System.out.print("변경할 주소 입력 : ");
		String address = sc.nextLine();
		
		System.out.print("변경할 취미 입력 : ");
		String hobby = sc.nextLine();
		
		pc.updateMember(userId, userPwd, email, phone, address, hobby);
	}
	
	public void deleteMember() {
		
		System.out.println("----- 회원 탈퇴 -----");
		
		System.out.print("탈퇴할 회원 아이디 입력 : ");
		String userId = sc.nextLine();
		
		pc.deleteMember(userId);
	}
	
	// --------------------------------------
	
	public void displaySuccess(String message) {
		
		System.out.println("서비스 요청 결과 : "+message);
	}
	
	public void displayFail(String message) {
		
		System.out.println("서비스 요청 결과 : "+message);
	}
	
	public void displayList(ArrayList<PreMember> list) {
		
		for (PreMember p : list) {
			System.out.println(p);
		}
	}
	
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
	
	public void displayOne(PreMember m) {
		
		System.out.println("조회된 결과입니다.");
		System.out.println(m);
	}
}
