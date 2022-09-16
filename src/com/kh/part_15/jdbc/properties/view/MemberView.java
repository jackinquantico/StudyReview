package com.kh.part_15.jdbc.properties.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_15.jdbc.properties.controller.MemberController;
import com.kh.part_15.jdbc.properties.model.vo.Member;

public class MemberView {

	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	public void mainMenu() {
		
		while (true) {
		
			System.out.println("----- 회원 관리 프로그램 -----");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디로 검색");
			System.out.println("4. 회원 이름 키워드 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("7. 회원가입일로 검색");
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
			case 7:
				selectByEnrollDate();
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
		
		System.out.print("회원 아이디 입력 : ");
		String userId = sc.nextLine();
		System.out.print("회원 비밀번호 입력 : ");
		String userPwd = sc.nextLine();
		System.out.print("회원 이름 입력 : ");
		String userName = sc.nextLine();
		System.out.print("회원 성별 입력 : ");
		String gender = sc.nextLine().toUpperCase();
		System.out.print("회원 나이 입력 : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("회원 이메일 입력 : ");
		String email = sc.nextLine();
		System.out.print("회원 핸드폰 번호 입력 : ");
		String phone = sc.nextLine();
		System.out.print("회원 주소 입력 : ");
		String address = sc.nextLine();
		System.out.print("회원 취미 입력 : ");
		String hobby = sc.nextLine();
		
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);		
	}

	public void selectAll() {
		
		System.out.println("----- 회원 전체 조회 -----");
		
		mc.selectAll();
	}
	
	public void selectByUserId() {
		
		System.out.println("----- 회원 아이디로 검색 -----");
		
		System.out.print("회원 아이디 입력 : ");
		String userId = sc.nextLine();
		
		mc.selectByUserId(userId);
	}
	
	public void selectByUserName() {
		
		System.out.println("----- 이름 키워드로 검색 -----");
		
		System.out.print("이름 키워드 입력 : ");
		String keyword = sc.nextLine();
		
		mc.selectByUserName(keyword);
	}
	
	public void updateMember() {
		
		System.out.println("----- 회원 정보 수정 -----");
		
		System.out.print("변경할 회원 아이디 입력 : ");
		String userId = sc.nextLine();
		
		System.out.print("변경할 비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("변경할 이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("변경할 핸드폰 번호 : ");
		String phone = sc.nextLine();
		
		mc.updateMember(userId, userPwd, email, phone);
	}
	
	public void deleteMember() {
		
		System.out.println("----- 회원 탈퇴 -----");
		
		System.out.print("삭제할 회원 아이디 입력 : ");
		String userId = sc.nextLine();
		
		System.out.print("정말로 삭제하시겠습니까? (Y/N) : ");
		char answer = sc.nextLine().toUpperCase().charAt(0);
		
		if (answer == 'Y') {
			mc.deleteMember(userId);
		} else {
			System.out.println("삭제를 취소하고 이전 메뉴로 돌아갑니다.");
		}
	}
	
	public void selectByEnrollDate() {
		
		System.out.println("----- 회원가입일로 조회 -----");
		System.out.print("회원가입일을 입력하시오 (MMDD) : ");
		String enrollDate = sc.nextLine();
		
		mc.selectByEnrollDate(enrollDate);
	}
	
	//---------------------------------------
	
	public void displaySuccess(String message) {
		
		System.out.println("서비스 요청 성공 : "+message);
	}
	
	public void displayFail(String message) {
		
		System.out.println("서비스 요청 실패 : "+message);
	}
	
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
	
	public void displayList(ArrayList<Member> list) {
	
		System.out.println("조회된 결과 입니다.");
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public void displayOne(Member m) {
		
		System.out.println("조회된 결과 입니다.");
		System.out.println(m);
	}
	
}
