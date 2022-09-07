package com.kh.part_14.afterJDBC.controller;

import java.util.ArrayList;

import com.kh.part_14.afterJDBC.model.dao.MemberDao;
import com.kh.part_14.afterJDBC.model.vo.Member;
import com.kh.part_14.afterJDBC.view.MemberView;

/*
 * * Controller
 * View를 통해서 요청한 기능을 처리하는 담당
 * 해당 메소드로 전달된 데이터를 VO 객체로 가공처리한 후 Dao 메소드 호출 시 전달 
 * Dao 로부터 반환받은 결과에 따라 사용자가 보게 될 View (응답화면) 을 결정 (View 의 메소드 호출)
 */

public class MemberController {

	
	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메소드
	 * @param userId ~ hobby : 회원가입 요청 시 사용자가 입력한 값
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender
							, int age, String email, String phone, String address, String hobby) {
		
		// 1. 전달된 데이터들을 VO 객체로 담아주기 (가공)
		// => 기본 생성자로 생성 후 setter 메소드로 필드 채우기 / 매개변수 생성자로 생성하기
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		// 2. VO 객체 Dao 메소드로 넘겨주기 (== Dao 단의 메소드 호출)
		// 3. 결과 받기
		int result = new MemberDao().insertMember(m);
		// => 성공이면 1, 실패면 0
		
		// 4. 결과에 따른 응답화면 지정 (== View 단의 메소드 호출)
		if (result > 0) { // 성공했을 경우
			new MemberView().displaySuccess("회원가입에 성공했습니다.");
		} else { // 실패했을 경우
			new MemberView().displayFail("회원가입에 실패했습니다.");
		}
		
	}
		
	/**
	 * 사용자의 회원 전체 조회 요청을 처리해주는 메소드
	 */
	public void selectAll() {
		
		// 1. 전달받은 값들을 VO 객체로 가공
		// => View 단에서 사용자로부터 입력받은 값이 없으므로 패스
		
		// 2. VO 객체를 Dao 단의 메소드에 요청 (호출)
		// 3. 결과 받기
		// => Dao 메소드 호출 시 생각해봐야하는 것 : 매개변수 X, 리턴타입 
		// => 회원 전체 조회 (SELECT문) : 적어도 여러 명의 회원 정보가 조회됨 => ArrayList<Member> 로 받아줄 것
		ArrayList<Member> list = new MemberDao().selectAll();
		// => list 에 조회된 회원들의 정보가 다 담겨있을 것
		// => 만약 아무도 조회되지 않았다면 list.size () == 0, list.isEmpty() == true
				
		// 4. 결과에 따른 응답화면 지정
		if (list.isEmpty()) { // 조회 결과가 없을 경우
			new MemberView().displayNodata("전체 조회된 결과가 없습니다.");
			
		} else { // 조회 결과가 있을 경우
			new MemberView().displayList(list);
		}
		
	}
	
	/**
	 * 사용자의 아이디로 검색 요청을 처리하는 메소드 
	 * @param userId : 검색하고자 하는 아이디 값
	 */
	public void selectByUserId(String userId) {
		
		// 1. 전달받은 값들을 VO 객체로 가공
		// => 전달받은 값이 하나 뿐이기 때문에 패스
		
		// 2. 전달값을 DAO로 넘기면서 메소드 호출
		// 3. 결과값 받기
		// => DAO 메소드를 호출할 때 고려해야 할 것 : 매개변수 userId, 리턴타입 Member
		// => USERID 의 제약 조건 : UNIQUE => 많아봤자 단 하나의 결과값이 나올 것이기 때문에
		Member m = new MemberDao().selectByUserId(userId);
		// 조회된 회원이 있다면 Member 객체, 없다면 null 값이 담겨있음
		
		// 4. 결과에 따른 응답화면 지정
		if (m == null) { // 조회 결과가 없는 경우
			new MemberView().displayNodata(userId + " 에 해당하는 검색 결과가 없습니다.");
		
		} else { // 조회 결과가 있는 경우
			new MemberView().displayOne(m);
		}
		
	}
	
	/**
	 * 사용자의 회원명 (키워드) 검색 요청을 처리하는 메소드
	 * @param keyword : 검색하고자 하는 이름 키워드
	 */
	public void selectByUserName(String keyword) {
		
		// 1. 전달받은 값들을 VO 객체로 가공
		// => 전달받은 값이 하나 뿐이기 때문에 패스
		
		// 2. 전달값을 DAO 로 넘기면서 메소드 호출
		// 3. 결과값 받기
		// => 매개변수 : keyword, 리턴값의 타입 : ArrayList<Member>
		// => 키워드 검색 결과가 여러 행일 수 있음
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		// 이 시점에서 검색 결과가 없으면 list.size() == 0 또는 list.isEmpty == true		
		
		// 4. 결과에 따른 응답화면 지정
		if (list.isEmpty()) { // 조회 결과가 없는 경우
			new MemberView().displayNodata(keyword+" 에 해당하는 검색 결과가 없습니다.");
			
		} else { // 조회 결과가 있는 경우
			new MemberView().displayList(list);
		}
	}
	
	/**
	 * 사용자의 회원 정보 변경 요청 시 처리해주는 메소드
	 * @param userId : 변경하고자 하는 회원의 아이디 (구분용)
	 * @param newPwd ~ newAddress : 변경할 정보들 (비밀번호, 이메일, 전화번호, 주소)
	 */
	public void updateMember(String userId, String newPwd, String newEmail, String newPhone, String newAddress) {
		
		// 1. 요청 시 전달받은 값들을 VO 객체로 가공하기
		Member m = new Member();
		
		m.setUserId(userId);
		m.setUserPwd(newPwd);
		m.setEmail(newEmail);
		m.setPhone(newPhone);
		m.setAddress(newAddress);
		
		// 2. 전달값을 DAO 의 메소드로 넘기기
		// 3. 결과 받기
		int result = new MemberDao().updateMember(m);
		
		// 4. 결과에 따른 응답 화면 지정
		if (result > 0) { // 수정 성공
			new MemberView().displaySuccess("회원 정보 변경 성공");
		} else { // 수정 실패
			new MemberView().displayFail("회원 정보 변경 실패");
		}
		
	}
	
	/**
	 * 사용자의 회원 탈퇴 요청 시 처리해주는 메소드
	 * @param userId : 삭제하고자 하는 회원의 아이디 (구분용)
	 */
	public void deleteMember(String userId) {
		
		// 1. 요청 시 전달받은 값들을 VO 객체로 가공하기
		// => 전달받은 값이 1개 이므로 패스
		
		// 2. 전달값을 DAO의 메소드로 넘기기
		// 3. 결과 받기
		int result = new MemberDao().deleteMember(userId);
		
		// 4. 결과에 따른 응답 화면 지정
		if (result > 0) { // 삭제 성공
			new MemberView().displaySuccess("회원 삭제 성공");
		} else { // 삭제 실패
			new MemberView().displayFail("회원 삭제 실패");
		}
	}
		
}
