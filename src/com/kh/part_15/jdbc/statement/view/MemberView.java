package com.kh.part_15.jdbc.statement.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_15.jdbc.statement.controller.MemberController;
import com.kh.part_15.jdbc.statement.model.vo.Member;


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
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
			System.out.println();
		}
	}
	
	/**
	 * 사용자의 회원 추가 요청시 보게 될 화면
	 */
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

	/**
	 * 사용자의 회원 전체 조회 요청시 보게 될 화면
	 */
	public void selectAll() {
		
		System.out.println("----- 회원 전체 조회 -----");
		
		mc.selectAll();
	}
	
	/**
	 * 사용자의 회원 아이디로 검색 요청 시 보게 될 화면
	 * 검색할 아이디를 입력받아야 한다.
	 */
	public void selectByUserId() {
		
		System.out.println("----- 회원 아이디로 검색 -----");
		
		System.out.print("검색할 아이디 입력 : ");
		String userId = sc.nextLine();
		
		mc.selectByUserId(userId);
	}
	
	/**
	 * 사용자의 이름 키워드로 검색 요청 시 보게 될 화면
	 * 검색할 키워드 입력받기
	 */
	public void selectByUserName() {
		
		System.out.println("----- 이름 키워드로 검색 -----");
		
		System.out.print("이름 키워드 : ");
		String keyword = sc.nextLine();
		
		mc.selectByUserName(keyword);
	}
	
	public void updateMember() {
		
		System.out.println("----- 회원 정보 변경 -----");
		
		System.out.print("변경할 회원 아이디 : ");
		String userId = sc.nextLine();
		
		System.out.print("변경할 비밀번호 입력 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("변경할 이메일 입력 : ");
		String email = sc.nextLine();
		
		System.out.print("변경할 주소 입력 : ");
		String address = sc.nextLine();
		
		System.out.print("변경할 취미 입력 : ");
		String hobby = sc.nextLine();
		
		mc.updateMember(userId, userPwd, email, address, hobby);
		
	}
	
	public void deleteMember() {
		
		System.out.println("----- 회원 탈퇴 -----");
		
		System.out.print("탈퇴할 회원 아이디 : ");
		String userId = sc.nextLine();
		
		mc.deleteMember(userId);
	}
	
	// --------------------------------------------------
	
	/**
	 * 서비스 요청 성공 시 보게 될 화면
	 * @param message : 성공 메시지
	 */
	public void displaySuccess(String message) {
		
		System.out.println("서비스 요청 성공 : "+message);
	}
	
	/**
	 * 서비스 요청 실패 시 보게 될 화면
	 * @param message : 실패 메시지
	 */
	public void displayFail(String message) {
		
		System.out.println("서비스 요청 실패 : "+message);
	}
	
	/**
	 * 조회 서비스 요청 시 여러 행이 조회된 결과를 보여줄 화면
	 * @param list : 전달받은 조회 목록
	 */
	public void displayList(ArrayList<Member> list) {
		
		System.out.println("조회된 결과는 다음과 같습니다.");
		
		for (Member m : list) {
			System.out.println(m);
		}
	}
	
	/**
	 * 조회 서비스 요청 시 한 행이 조회된 결과를 보여줄 화면
	 * @param m : 조회된 한 행의 데이터
	 */
	public void displayOne(Member m) {
		
		System.out.println("조회된 결과는 다음과 같습니다.");
		System.out.println(m);
	}
	
	/**
	 * 조회 서비스 요청 시 조회 결과가 없을 때 보여줄 화면
	 * @param message : 결과가 없음을 나타내는 메시지
	 */
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
}
