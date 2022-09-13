package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

/*
 * * Service
 * 기존의 Dao 의 역할 중 DB에 접속 관련한 코드들만 빼두는 곳
 * JDBC 흐름 중 접속과 관련된 Connection 객체 관련 코드들만 작업할 예정
 * 
 * Connection 객체 생성 -> Dao 로 전달 -> Connection 을 이용한 트랜잭션 처리 -> Connection 객체 반납
 */


// 1. Connection 객체 생성
// 2. Dao 의 메소드 호출 (conn, 전달값)
// 3. 트랜잭션 처리
// 4. Connection 객체 반납
// 5. 결과 리턴 (Controller 클래스로)

public class MemberService {

	/**
	 * 회원 추가 기능을 위한 INSERT 문을 실행하기 전 필요한 Connection 객체를 만들어주는 메소드
	 * @param m : 추가할 회원의 정보
	 * @return : INSERT 된 행의 갯수
	 */
	public int insertMember(Member m) {
		
		// 1. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. Dao의 메소드 호출 (conn, m(Controller로부터 받은 전달값) 전달)
		int result = new MemberDao().insertMember(conn, m);
		
		// 3. 트랜잭션 처리
		if (result > 0) { // 성공
			JDBCTemplate.commit(conn);
		} else { // 실패
			JDBCTemplate.rollback(conn);
		}
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과값 리턴 (Controller 클래스로)
		return result;		
	}
	
	/**
	 * 회원 전체 조회를 위한 SELECT 문을 실행할 때 필요한 Connection 객체를 생성해주는 메소드
	 * @return : 전체 회원들의 정보들
	 */
	public ArrayList<Member> selectAll() {
		
		// 1. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. Dao의 메소드 호출
		ArrayList<Member> list = new MemberDao().selectAll(conn);
		
		// 3. SELECT 문 -> 트랜잭션 처리 필요 없으므로 패스
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과값 리턴 (Controller 클래스로)
		return list;		
	}
	
	/**
	 * 회원 아이디 검색 기능을 위한 SELECT 문 실행 시 필요한 Connection 객체를 생성해주는 메소드
	 * @param userId : 검색할 회원의 아이디
	 * @return : 검색 결과로 조회된 회원 한명의 정보
	 */
	public Member selectByUserId(String userId) {
		
		// 1. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. Dao의 메소드 호출 (conn, userId 전달)
		Member m = new MemberDao().selectByUserId(conn, userId);
		
		// 3. SELECT 문 -> 트랜잭션 처리 필요 없으므로 패스
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과값 리턴 (Controller 클래스로)
		return m;
	}
	
	/**
	 * 회원 이름 키워드로 검색 기능을 위한 SELECT 문 실행 시 필요한 Connection 객체를 생성해주는 메소드
	 * @param keyword : 검색할 회원 이름 키워드
	 * @return : 검색 결과로 조회된 회원들의 목록
	 */
	public ArrayList<Member> selectByUserName(String keyword) {
		
		// 1. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. Dao 의 메소드 호출 (conn, keyword 전달)
		ArrayList<Member> list = new MemberDao().selectByUserName(conn, keyword);
		
		// 3. SELECT 문 -> 트랜잭션 필요없으므로 패스
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과값 리턴 (Controller 클래스로)
		return list;
	}
	
	/**
	 * 회원 정보 수정을 위한 UPDATE 문 실행 시 필요한 Connection 객체를 생성해주는 메소드
	 * @param m : 수정할 회원의 정보
	 * @return : UPDATE 된 행의 개수
	 */
	public int updateMember(Member m) {
		
		// 1. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. Dao 의 메소드 호출 (conn, m 전달)
		int result = new MemberDao().updateMember(conn, m);
		
		// 3. UPDATE 문 -> 트랜잭션 처리
		if (result > 0) { // 성공
			JDBCTemplate.commit(conn);
		} else { // 실패
			JDBCTemplate.rollback(conn);
		}
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과 리턴 (Controller 클래스로)
		return result;
	}
	
	/**
	 * 회원 정보 삭제를 위한 DELETE 문 실행 시 필요한 Connection 객체를 생성해주는 메소드
	 * @param userId : 삭제할 회원의 아이디
	 * @return : DELETE 된 행의 개수
	 */
	public int deleteMember(String userId) {
	
		// 1. Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. Dao 의 메소드 호출 (conn, m 전달)
		int result = new MemberDao().deleteMember(conn, userId);
		
		// 3. UPDATE 문 -> 트랜잭션 처리
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. 결과 리턴 (Controller 클래스로)
		return result;
	}
	
}
