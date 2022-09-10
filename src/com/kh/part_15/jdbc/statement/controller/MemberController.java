package com.kh.part_15.jdbc.statement.controller;

import java.util.ArrayList;

import com.kh.part_15.jdbc.statement.model.dao.MemberDao;
import com.kh.part_15.jdbc.statement.model.vo.Member;
import com.kh.part_15.jdbc.statement.view.MemberView;

public class MemberController {

	/**
	 * 새로운 회원 추가 요청을 처리해주는 메소드
	 * @param userId ~ hobby : 사용자로부터 입력받은 값
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender
							, int age, String email, String phone, String address, String hobby) {
		
		// 1. VO 객체로 가공
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		// 2. VO 객체를 DAO 클래스로 넘기고 결과 받기
		int result = new MemberDao().insertMember(m);
		
		// 3. 결과에 따른 응답화면 지정
		if (result > 0) {
			new MemberView().displaySuccess("회원 추가 성공");
		} else {
			new MemberView().displayFail("회원 추가 실패");
		}
	}
	
	/**
	 * 전체 회원 조회 요청을 처리해주는 메소드
	 */
	public void selectAll() {
		
		// 1. Vo 객체로 가공 -> 입력값 없으므로 패스
		
		// 2. DAO 클래스 호출하고 결과 받기
		// => Member 객체들이 담긴 ArrayList
		ArrayList<Member> list = new MemberDao().selectAll();
		
		// 3. 결과에 따른 응답화면 지정
		if (list.isEmpty()) {
			new MemberView().displayNodata("현재 존재하는 회원이 없습니다.");
		} else {
			new MemberView().displayList(list);
		}
	}

	/**
	 * 회원 아이디로 검색 요청을 처리해주는 메소드
	 * @param userId : 사용자로부터 입력받은 아이디
	 */
	public void selectByUserId(String userId) {
		
		// 1. Vo 객체로 가공 -> 입력값이 하나이므로 패스
		
		// 2. DAO 클래스로 넘기고 결과받기
		Member m = new MemberDao().selectByUserId(userId);
		
		// 3. 결과에 따른 응답화면 지정
		if (m != null) {
			new MemberView().displayOne(m);
		} else {
			new MemberView().displayFail(userId +" 와 일치하는 결과가 없습니다.");
		}
		
	}

	/**
	 * 회원 이름 키워드로 검색 요청을 처리해주는 메소드
	 * @param keyword : 사용자로부터 입력받은 이름 키워드
	 */
	public void selectByUserName(String keyword) {
		
		// 1. VO 객체로 가공 -> 입력값이 하나이므로 패스
		
		// 2. DAO 클래스 호출하고 결과 받기
		// => 결과가 여러 행일 수 있으므로 ArrayList
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		// 3. 결과에 따른 응답화면 처리
		if (list.isEmpty()) {
			new MemberView().displayNodata(keyword +" 에 해당하는 결과가 없습니다.");
		} else {
			new MemberView().displayList(list);
		}
	}
	
	public void updateMember(String userId, String userPwd, String email, String address, String hobby) {
		
		// VO 객체 가공
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setAddress(address);
		m.setHobby(hobby);
		
		// DAO 단으로 넘기기
		int result = new MemberDao().updateMember(m);
		
		// 결과에 따른 응답 화면 처리
		if (result > 0) {
			new MemberView().displaySuccess("정보 변경 성공");
		} else {
			new MemberView().displayFail("정보 변경 실패");
		}
	}
	
}
