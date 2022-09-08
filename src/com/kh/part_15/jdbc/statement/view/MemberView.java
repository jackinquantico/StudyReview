package com.kh.part_15.jdbc.statement.view;

import java.util.Scanner;

import com.kh.part_15.jdbc.statement.controller.MemberController;


public class MemberView {

	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	/**
	 * 사용자가 보게 될 첫 화면 (메인)
	 */
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
			case 0:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void insertMember() {
		
		System.out.println("----- 회원 추가 -----");
		
		// 회원 추가시 필요한 데이터들
		// 아이디, 비밀번호, 이름, 성별, 나이, 이메일, 휴대폰, 주소, 취미 (총 9개)
		System.out.print("아이디 : ");
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
		
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
	}
}
