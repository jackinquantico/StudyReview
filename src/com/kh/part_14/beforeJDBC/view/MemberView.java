package com.kh.part_14.beforeJDBC.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.beforeJDBC.controller.MemberController;
import com.kh.part_14.beforeJDBC.model.vo.Member;


public class MemberView {

	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	public void mainMenu() {
		
		int count = 0;
		
		while (true) {
			
			System.out.print("관리자 아이디 : ");
			String sysId = sc.nextLine();
			System.out.print("비밀번호 : ");
			String sysPwd = sc.nextLine();
			
			if (sysId.equals("admin") && sysPwd.equals("P@s$W0rd!")) {
				break;
			} else {
				count++;
			}
			
			if (count == 5) {
				System.out.println("관리자 로그인 실패 5회");
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
			
			System.out.println("아이디와 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			
		}
		
		System.out.println();
		
		while (true) {

			System.out.println("*** 메뉴 구성 ***");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체조회");
			System.out.println("3. 회원 아이디 검색");
			System.out.println("4. 회원 이름 키워드 검색");
			System.out.println("5. 회원 비밀번호 초기화");
			System.out.println("6. 회원 삭제");
			System.out.println("0. 프로그램 로그아웃");
			System.out.print("메뉴 번호 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				insertMember();
				break;
			case 2:
				selectMemberList();
				break;
			case 3:
				selectMemberById();
				break;
			case 4:
				searchMemberByName();
				break;
			case 5:
				initMemberPwd();
				break;
			case 6:
				deleteMember();
				break;
			case 0:
				System.out.println("프로그램에서 로그아웃합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
			
			System.out.println();
		}
	}
	
	public void insertMember() {
		
		System.out.println("*** 회원 추가 ***");
		System.out.print("아이디 입력 : ");
		String userId = sc.nextLine();
		int result = mc.idCheck(userId);
		
		if (result > 0) {
			System.out.println("이미 사용중인 아이디입니다.");
			return;
		}
		
		System.out.print("비밀번호 입력 : ");
		String userPwd = sc.nextLine();
		System.out.print("이름 입력 : ");
		String userName = sc.nextLine();
		System.out.print("나이 입력 : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("성별 입력 : ");
		char gender = sc.nextLine().charAt(0);
		System.out.print("이메일 입력 : ");
		String email = sc.nextLine();
		System.out.print("휴대폰 번호 입력 : ");
		String phone = sc.nextLine();
		
		int insertResult = mc.insertMember(userId, userPwd, userName, age, gender, email, phone);
		
		if (insertResult > 0) {
			System.out.println("회원 추가 성공");
		} else {
			System.out.println("회원 추가 실패");
		}
	}
	
	public void selectMemberList() {
		
		System.out.println("*** 회원 전체 조회 ***");
		
		ArrayList<Member> memberList = mc.selectMemberList();
		
		if (memberList.isEmpty()) {
			System.out.println("현재 조회할 회원이 존재하지 않습니다.");
		} else {
			for (Member m : memberList) {
				System.out.println(m);
			}
		}
		
	}

	public void selectMemberById() {
		
		System.out.println("*** 회원 아이디 검색 ***");
		System.out.print("아이디 입력 : ");
		String userId = sc.nextLine();
		
		Member user = mc.searchMemberById(userId);
		
		if (user.equals(null)) {
			System.out.println("일치하는 회원이 존재하지 않습니다.");
		} else {
			System.out.println(user);			
		}
	}
	
	public void searchMemberByName() {
		
		System.out.println("*** 회원 이름 키워드 검색 ***");
		System.out.print("아이디 입력 : ");
		String keyword = sc.nextLine();
		
		ArrayList<Member> searched = mc.searchMemberbyName(keyword);
		
		if (searched.isEmpty()) {
			System.out.println("일치하는 회원이 없습니다.");
		} else {
			for (Member m : searched) {
				System.out.println(m);
			}
		}
	}
	
	public void initMemberPwd() {
		
		System.out.println("*** 회원 비밀번호 초기화 ***");
		System.out.print("아이디 입력 : ");
		String userId = sc.nextLine();
		
		int result = mc.initMemberPwd(userId);
		
		if (result > 0) {
			System.out.println("비밀번호가 초기화되었습니다.");
		} else {
			System.out.println("해당 아이디가 존재하지 않습니다.");
		}
	}
	
	public void deleteMember() {
		
		System.out.println("*** 회원 삭제 ***");
		System.out.print("아이디 입력 : ");
		String userId = sc.nextLine();
		
		System.out.println("삭제 후 복구할 수 없습니다.");
		System.out.print("정말 삭제하시겠습니까? : ");
		char answer = sc.nextLine().toLowerCase().charAt(0);
		
		if (answer == 'y') {
			int result = mc.deleteMember(userId);
			
			if (result > 0) {
				System.out.println("회원을 삭제했습니다.");
			} else {
				System.out.println("해당 회원을 찾을 수 없습니다.");
			}
		} else {
			System.out.println("이전 화면으로 돌아갑니다.");
		}
		
	}
}
