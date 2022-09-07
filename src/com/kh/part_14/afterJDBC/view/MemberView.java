package com.kh.part_14.afterJDBC.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.afterJDBC.controller.MemberController;
import com.kh.part_14.afterJDBC.model.vo.Member;

/*
 * * View
 * 사용자가 보게 될 시각적인 요소, 화면
 * 
 * - CLI : Command Line Interface (단순히 키보드만으로 컴퓨터와 소통할 수 있는 환경)
 * 		     출력문 (println, print, ..), 입력문 (Scanner)
 * 
 * - GUI : Graphic User Interface (마우스, 키보드를 모두 이용해서 컴퓨터와 소통할 수 있는 환경)
 * 		   html 태그
 */

public class MemberView {
	
	// 전역변수로 스캐너 객체 생성
	private Scanner sc = new Scanner(System.in);
	
	// 전역변수로  MemberController 객체 생성
	private MemberController mc = new MemberController();
	
	// 메소드 주석 단축키 : alt + shift + j
	/**
	 * 사용자가 보게 될 첫 화면 (메인화면)
	 */
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("***** 회원 관리 프로그램 *****");
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
				insertMember(); // 회원 추가용 화면 담당 메소드
				break;
			case 2:
				selectAll(); // 회원 전체조회용 화면 담당 메소드
				break;
			case 3:
				selectByUserId(); // 회원 아이디 검색용 화면 담당 메소드
				break;
			case 4:
				selectByUserName(); // 회원 이름 키워드 검색용 화면 담당 메소드
				break;
			case 5:
				updateMember(); // 회원 정보 변경용 화면 담당 메소드
				break;
			case 6:
				deleteMember(); // 회원 정보 삭제용 화면 담당 메소드
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시 입력해주세요.");
			}
		
			System.out.println();
		}
	} // mainMenu
	
	/**
	 * 회원 추가용 화면
	 * 추가하고자 하는 회원의 정보를 입력받아서 추가 요청을 할 수 있는 화면
	 */
	public void insertMember() {
		
		System.out.println("----- 회원 추가 -----");
		
		// 회원 추가 --> MEMBER 테이블에 INSERT 하겠다
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
		
		// 회원 추가 요청 => MemberController 클래스의 메소드 호출
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
	} // insertMember()
	
	/**
	 * 회원 전체 조회용 화면
	 */
	public void selectAll() {
		
		System.out.println("----- 회원 전체 조회 -----");
		
		// 회원 전체 조회 요청 => MemberController 클래스로
		mc.selectAll();
		
	}
	
	/**
	 * 회원 아이디 검색용 화면
	 * 사용자에게 검색할 회원의 아이디를 입력받은 후 조회를 요청하는 화면
	 */
	public void selectByUserId() {
		
		System.out.println("----- 회원 아이디로 검색 -----");
		
		// 사용자로부터 검색하고자 하는 회원의 아이디를 입력받아야 함
		System.out.print("검색할 회원의 아이디 : ");
		String userId = sc.nextLine();
		
		// 입력한 아이디를 회원 아이디 검색 요청시 매개변수로 넘겨줌 => Controller 클래스로 요청
		mc.selectByUserId(userId);
				
	}
	
	/**
	 * 회원 이름 키워드 검색용 화면
	 * 사용자에게 검색할 회원의 이름 키워드를 입력받은 후 조회를 요청하는 화면
	 */
	public void selectByUserName() {
		
		System.out.println("----- 회원 이름 키워드 검색 -----");
		
		// 사용자로부터 검색하고자 하는 회원 이름 키워드 입력받아야 함 (일치 X, 키워드 포함 O)
		System.out.print("회원 이름 키워드 입력 : ");
		String keyword = sc.nextLine();
		
		// 입력받은 키워드를 회원 이름 검색 요청시 매개변수로 넘겨줌 => Controller 클래스로 요청
		mc.selectByUserName(keyword);
		
	}
	
	/**
	 * 사용자에게 변경할 회원의 아이디, 변경할 정보들(비밀번호, 이메일, 전화번호, 주소) 을 입력받은 후
	 * 변경을 요청하는 화면
	 */
	public void updateMember() {
		
		System.out.println("----- 회원정보 변경 -----");
		
		// 어느 데이터를 어떻게 변경할 것인지 언급
		// 어느 회원의 => unique 제약조건이 걸린 회원의 아이디로 구분
		// 어떻게 변경할 것인지 => 비밀번호, 이메일, 전화번호, 주소
		
		// 변경할 회원의 아이디
		System.out.print("변경할 회원의 아이디 : ");
		String userId = sc.nextLine();
		
		// 변경할 내용들
		System.out.print("변경할 비밀번호 : ");
		String newPwd = sc.nextLine();
		
		System.out.print("변경할 이메일 : ");
		String newEmail = sc.nextLine();
		
		System.out.print("변경할 전화번호 (숫자만) : ");
		String newPhone = sc.nextLine();
		
		System.out.print("변경할 주소 : ");
		String newAddress = sc.nextLine();
		
		// MemberController 의 메소드 호출하여 회원 정보 수정 요청
		mc.updateMember(userId, newPwd, newEmail, newPhone, newAddress);
	}
	
	/**
	 * 사용자에게 탈퇴할 회원의 아이디를 입력받아 삭제를 요청하는 화면
	 */
	public void deleteMember() {
		
		System.out.println("----- 회원 탈퇴  -----");
		
		// 탈퇴하고자 하는 회원의 아이디 입력받기 => unique 제약조건에 의해 회원 구분할 수 있는 식별자
		System.out.print("탈퇴할 회원 아이디 : ");
		String userId = sc.nextLine();
		
		mc.deleteMember(userId);
		
	}
	
	// ---------------------------------------------------
	// 서비스 요청 처리 후 사용자가 볼 응답 화면에 대한 메소드들
	
	/**
	 * 서비스 요청 성공 시 보게 될 화면
	 * @param message : 성공 메시지
	 */
	public void displaySuccess(String message) {
		
		System.out.println("서비스 요청 성공 : " + message);
	}
	
	/**
	 * 서비스 요청 실패 시 보게 될 화면
	 * @param message : 실패 메시지
	 */
	public void displayFail(String message) {
		
		System.out.println("서비스 요청 실패 : " + message);
	}
	
	/**
	 * 조회 서비스 요청 시 조회 결과가 없을 때 보게 될 화면
	 * @param message : 결과가 없음을 나타내는 메시지
	 */
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
	
	/**
	 * 조회 서비스 요청 시 여러 행이 조회된 결과를 받아서 보게 될 화면
	 * @param list : 여러 행이 조회된 결과물
	 */
	public void displayList(ArrayList<Member> list) {
		
		System.out.println("조회된 데이터는 다음과 같습니다.");
		
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	/**
	 * 조회 서비스 요청 시 한 행이 조회된 결과를 받아서 보게 될 화면
	 * @param m : 한 행이 조회된 결과물
	 */
	public void displayOne(Member m) {
		
		System.out.println("조회된 데이터는 다음과 같습니다.");
		System.out.println(m);
	}
	
}