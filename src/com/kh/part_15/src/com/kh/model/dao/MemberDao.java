package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.Member;

import oracle.jdbc.driver.OracleDriver;

/*
 * * DAO (Data Access Object)
 * Controller 를 통해서 호출된 기능을 수행하기 위해
 * DB에 직접적으로 접근한 후 해당 SQL 문 실행 및 결과를 받는 부분
 * => JDBC 코드 작성
 */

public class MemberDao {

	/*
	 * * JDBC 용 객체
	 * - Connection : DB 의 연결 정보를 담고 있는 객체
	 * - (Prepared)Statement : 해당 DB에 SQL 문을 전달하고 실행한 결과를 받아내는 객체
	 * - ResultSet : 만일 실행한 SQL 문이 SELECT 문일 경우 조회된 결과들이 담겨있는 객체
	 * 
	 * 
	 * * Statement(부모) 와 PreparedStatement(자식) 의 차이점
	 * - Statement 의 경우 완성된 SQL 문을 바로 실행하는 객체
	 * 	 (즉, SQL 문이 완성된 형태로 세팅되어 있어야만 한다. => 사용자가 입력했던 값들이 다 채워진 채로 세팅)
	 * 	 > Connection 객체를 가지고 Statement 객체 생성 : stmt = conn.createStatement();
	 * 	 > executeQuery / executeUpdate() 메소드를 이용해서 SQL 문을 전달하면서 실행 : 결과 = stmt.executeXXXX(sql);
	 * 
	 * - PreparedStatement 의 경우 SQL 문을 바로 실행하지 않고 잠시 보관해둘 수 있음
	 *   (즉, 완성/미완성 여부에 상관 없이 SQL 문을 잠시 보관해둘 수 있음 => 단, 미완성된 경우 사용자가 입력한 값들이 들어갈 수 있는
	 *   													공간을 ? 기호(위치 홀더)로 확보해둬야 함.)
	 *   해당 SQL 문을 실행하기 전에 완성 형태로 만든 후 실행만 하면 된다.
	 *   	> Connection 객체를 가지고 PreparedStatement 객체 생성 : pstmt = conn.prepareStatement(sql);
	 *   	> 현재 담긴 SQL 문이 미완성된 형태일 경우
	 *   	    빈 공간 (?) 을 실제 값으로 채워주는 과정을 거쳐야 한다. : pstmt.setString() / pstmt.setInt() 메소드 사용 (위치홀더의 순번, 실제 값)
	 * 		> executeQuery / executeUpdate() 메소드를 이용해서 SQL 문을 실행 : 결과 = pstmt.executeXXXX();
	 * 
	 * 
	 * * JDBC 처리 순서
	 * 1) JDBC Driver 등록 : 해당 DBMS 가 제공하는 클래스 등록
	 * 2) Connection 객체 생성 : 접속하고자 하는 DB 정보를 기술해서 DB에 접속
	 * 
	 * 3) PreparedStatement 객체 생성 : Connection 객체를 이용해서 생성  (매개변수로 SQL문을 넘기면서)
	 * 3_2) 현재 미완성인 SQL 문일 경우 완성 형태로 채우는 과정
	 * 		=> 미완성된 경우에만 해당 / 완성된 경우네는 과정 생략
	 * 
	 * 4) SQL 문 실행 : PreparedStatement 객체에서 제공하는 메소드를 호출함으로써 실행
	 * 			> SELECT 문일 경우 - executeQuery 메소드를 이용하여 실행
	 * 			> 나머지 DML 문일 경우 - executeUpdate 메소드를 이용하여 실행
	 * 			  (INSERT, UPDATE, DELETE)
	 * 5) 결과 받기
	 * 			> SELECT 문일 경우 - ResultSet 객체(조회된 데이터들이 담겨있음) 로 반환 => 6_1)
	 * 			> 나머지 DML 문 일 경우 - int 타입(처리된 행의 갯수)으로 반환			  => 6_2)
	 * 6_1) ResultSet 에 담겨있는 데이터들을 하나씩 뽑아서 VO 객체에 담기
	 * 6_2) 트랜잭션 처리 (성공 : COMMIT, 실패 : ROLLBACK)
	 * 7) 다 쓴 JDBC 용 객체들을 반드시 자원반납 (close메소드) => 생성된 순서의 역순으로
	 * 8) 결과 반환 (Controller 클래스로)
	 *  		> SELECT 문일 경우 - 6_1)에서 만들어진 결과 리턴
	 *  		> 나머지 DML 문일 경우 - int (처리된 행의 갯수) 값 리턴
	 * 			  (INSERT, UPDATE, DELETE)
	 * 
	 * ** Statement 특징 : 완성된 SQL 문을 실행할 수 있는 객체
	 */
	
	/**
	 * 사용자가 회원 추가 요청 시 입력했던 값들을 가지고 INSERT 문을 실행하는 메소드
	 * @param m : 사용자가 입력했던 값들이 모두 담겨있는 Member 객체
	 * @return : INSERT 문 실행 후 처리된 행의 개수
	 */
	public int insertMember(Connection conn, Member m) {
		
		// INSERT 문 => 처리된 행의 개수 (int) => 트랜잭션 처리
		
		// 0) 필요한 변수 선언 및 null 초기화
		int result = 0; // 처리된 결과를 받을 수 있는 변수
		// Connection conn = null; // 접속된 DB의 연결정보를 담을 수 있는 변수
		PreparedStatement pstmt = null; // SQL 문 실행 후 결과를 받기 위한 변수
		
		// 실행할 SQL 문 (완성된 형태) => Statement 버전
		// => 끝에 세미콜론은 뺀 형태 (세미콜론이 있으면 안 됨)
		// INSERT INTO MEMBER
		/*
		String sql = "INSERT INTO MEMBER "
							   + "VALUES (SEQ_USERNO.NEXTVAL, "
						   			+"'"+ m.getUserId() + "', "
						   			+"'"+ m.getUserPwd() + "', "
						   			+"'"+ m.getUserName() + "', "
						   			+"'"+ m.getGender() + "', "
							   			+ m.getAge() + ", "
						   			+"'"+ m.getEmail() + "', "
						   			+"'"+ m.getPhone() + "', "
						   			+"'"+ m.getAddress() + "', "
						   			+"'"+ m.getHobby() + "', DEFAULT)";
		*/
		
		// VALUES (SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)
		// 실행할 SQL 문 (미완성된 형태) => PreparedStatement 버전
		String sql = "INSERT INTO MEMBER "
				   + "VALUES (SEQ_USERNO.NEXTVAL, ?, ?, ?, ?"
				   	 	 + ", ?, ?, ?, ?, ?, DEFAULT)";
		
		// System.out.println(sql);
		
		try {
			// 1) JDBC Driver 등록
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			// ojdbc6.jar 파일 내에 있는 oracle.jdbc.driver.OracleDriver 클래스를 등록
			// => ojdbc6.jar 파일이 누락되었거나 잘 추가되었지만 오타가 있는 경우
			//	  ClassNotFoundException 발생
			
			// 2) Connection 객체 생성 (DB와 연결 -> url : DB의 주소지, 계정명, 비밀번호)
			// conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			// => 적어도 이 단계까지 완료되었다면 DB에 연결이 완료된 것
			
			// 3) PreparedStatement 객체 생성 (Connection 객체로부터)
			// stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			
			// 3_2) 내가 담은 SQL 문이 미완성된 형태일 경우 값 채우기
			//		pstmt.setXXX(위치홀더의 순번, 값)
			// 		pstmt.setString(위치홀더의 순번, 값); => ? 자리에 '실제채울값' (양 옆에 홑따옴표가 붙어서 들어감)
			//		pstmt.setInt(위치홀더의 순번, 값); => ? 자리에 실제채울값 (양 옆에 홑따옴표가 붙지 않음)
			pstmt.setString(1, m.getUserId());   // 'test02'
			pstmt.setString(2, m.getUserPwd());  // 'pwd02'
			pstmt.setString(3, m.getUserName()); // '홍길동'
			pstmt.setString(4, m.getGender());   // 'M'
			pstmt.setInt(5, m.getAge());	     // 45
			pstmt.setString(6, m.getEmail());    // 'test02@naver.com'
			pstmt.setString(7, m.getPhone());	 // '01022223333'
			pstmt.setString(8, m.getAddress());  // '서울시'
			pstmt.setString(9, m.getHobby());	 // '운동'
			
			// => 적어도 이 단계까지 완료되었다면 본격적으로 SQL 문 실행시킬 수 있음
			
			// 4, 5) DB에 SQL 문을 실행 후 결과 (처리된 행의 갯수) 받기
			// result = stmt.executeUpdate(sql);
			result = pstmt.executeUpdate();
			// => result 에는 처리된 행의 갯수가 들어있음
			
			// 6_2) 트랜잭션 처리
			// => result 변수에 담긴 값 기준으로 조건 처리
//			if (result > 0) { // 1개의 행이 insert => insert 가 성공 => COMMIT 처리
//				// Connection 객체를 통해 처리
//				conn.commit();
//			} else { // 0개의 행이 insert => insert 가 실패 => ROLLBACK 처리
//				conn.rollback();
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
//			try {
//				// 7) 다 쓴 JDBC 용 객체들을 반드시 자원반납
//				// => 생성된 순서의 역순으로 close
//				// 생성 순서 : Connection -> PreparedStatement
//				// 닫는 순서 : PreparedStatement -> Connection
//				// stmt.close();
//				// pstmt.close();
//				// conn.close();
//				
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			
			JDBCTemplate.close(pstmt);
			
		}
		
		// 8) Controller 로 결과 반환 (처리된 행의 갯수)
		// 	  => 성공했을 경우 1, 실패했을 경우 0
		return result;
	}
	
	/**
	 * 사용자가 회원 전체 조회 요청 시 SELECT 문을 실행하는 메소드
	 * @return : 테이블로부터 조회된 전체 회원의 정보를 나타내는 ArrayList
	 */
	public ArrayList<Member> selectAll(Connection conn) {
		
		// SELECT 문 => ResultSet 객체 => 여러 행 조회 (Member 1개당 회원 한 명) : ArrayList 타입으로 묶기
		
		// 0) 필요한 변수 선언 및 초기화
		ArrayList<Member> list = new ArrayList<>(); // 조회된 결과를 뽑아서 담아줄 ArrayList 생성 (텅 빈 리스트)
		// Connection conn = null; // 접속된 DB의 연결 정보를 담는 Connection 변수
		// Statement stmt = null; // SQL 문 실행 후 결과를 받기 위한 변수
		PreparedStatement pstmt = null;
		ResultSet rset = null; // SELECT 문이 실행될 조회결과값이 처음 실질적으로 담겨올 객체
		
		// 실행할 SQL 문을 담을 변수 (완성된 형태, 세미콜론 포함 X)
		// SELECT * FROM MEMBER
		String sql = "SELECT * FROM MEMBER";
		// => PreparedStatement 는 완성된 뭐리문을 먼저 보내도 무방
		
		try {
			// 1) JDBC Driver 등록
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 (url, 계정명, 비밀번호)
			// conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) PreparedStatement 객체 생성 (Connection 객체로부터 얻을 수 있음)
			// stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql); // 애초에 SQL 문을 담은 채로 생성
			
			// 3_2) 미완성된 쿼리문 완성시키기
			// => 미완성된 쿼리문이 아니었기 때문에 패스
			
			// 4, 5) SQL 문 (SELECT) 실행 후 결과 받기
			// rset = stmt.executeQuery(sql);
			rset = pstmt.executeQuery();
			// => rset 에는 조회된 결과물들이 다 담겨있을 것임.
			
			// 6_1) 현재 조회 결과가 담긴 ResultSet 에서 한 행씩 뽑아서 VO 객체에 담기
			// => ResultSet 객체는 커서를 한 줄씩 아래로 옮겨서 현재 행의 위치를 나타내는 구조
			// => 이 때, 커서를 rset.next() 메소드로 다음 줄로 옮겨버리기 : 더 뽑을 것이 있으면 true, 없으면 false 반환
			while (rset.next()) { // 커서를 한 줄 아래로 움직여주고, 해당 행이 존재하면 true, 없으면 false 반환
				
				// 적어도 이 중괄호 내에 들어와서 코드가 실행된다 == 더 뽑아낼 것이 있을 경우
				
				// 현재 rset 의 커서가 가리키고 있는 해당 행의 데이터를 하나씩 뽑아서 Member 객체에 담기
				// 한 행의 데이터 == 회원 한 명의 정보 == Member VO 객체 한 개
				Member m = new Member();
				
				// rset으로부터 어떤 컬럼에 해당하는 값을 뽑을 건지 제시
				// 각 컬럼에 들어있던 값들을 각 필드로 옮겨주기
				// rset.getInt(컬럼명 / 컬럼순번) : int 형 값을 뽑아낼 때 
				// rset.getString(컬럼명 / 컬럼순번) : String 형 값을 뽑아낼 때
				// rset.getDate(컬럼명 / 컬럼순번) : Date 형 값을 뽑아낼 때
				// => 컬럼명은 대소문자를 가리지 않음
				// => 컬럼명을 대문자로 쓰는 것을 권장 (가독성을 위해)
				
				// m.userNo = rset.getInt("USERNO"); => private 라서 불가
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				// 한 행에 대한 모든 데이터 값들을 하나의 Member 객체로 옮겨담기 끝
				
				// Member VO 객체를 list에 추가 (담아두기)
				list.add(m);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
//			try {
//				// 7) 다 쓴 JDBC 용 객체 반납 (생성된 역순)
//				// 생성 순서 : Connection > PreparedStatement > ResultSet
//				// 닫는 순서 : ResultSet > PreparedStatement > Connection
//				// rset.close();
//				// pstmt.close();
//				// conn.close();
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// 8) 결과 반환 (Controller 클래스로)
		return list; // 회원들의 정보가 담겨있음
					 // 만약 아무도 조회되지 않았다면 list.size () == 0, list.isEmpty() == true
		
	}

	/**
	 * 사용자의 아이디 검색 조회를 위한 SELECT 문을 실행할 메소드
	 * @param userId : 검색하고자 하는 조건에 해당되는 아이디값
	 * @return Member : 검색된 회원 한 명의 정보
	 */
	public Member selectByUserId(Connection conn, String userId) {
		
		// SELECT 문 => ResultSet 객체 => Member 타입 하나로 결과 받기 (한 행 조회)
		
		// 0) 필요한 변수 선언 및 초기화
		Member m = null; // 조회된 한 회원에 대한 정보를 담는 변수
		// Connection conn = null; // 접속된 DB의 연결정보를 담는 변수
		PreparedStatement pstmt = null; // SQL 문 실행 후 결과를 받기 위한 변수
		ResultSet rset = null; // SELECT 문이 실행된 조회 결과값들이 처음에 실질적으로 담길 객체
		
		// 실행할 SQL 문 (완성된 형태, 세미콜론 제외)
		// SELECT * FROM MEMBER WHERE USERID = 'userId';
		// String sql = "SELECT * FROM MEMBER WHERE USERID = '" + userId + "'";
		
		// 실행할 SQL 문 (미완성된 형태, 세미콜론 제외)
		// SELECT * FROM MEMBER WHERE USERID = ?
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
		
		try {
			// 1) JDBC Driver 등록
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 (url, 계정명, 비밀번호)
			// conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) PreparedStatement 객체 생성 (Connection 객체로부터)
			// stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			
			// 3_2) 현재 미완성인 SQL 문일 경우 완성 형태로 채우는 과정
			pstmt.setString(1, userId); // 'userid'
			
			// 4, 5) SQL 문 (SELECT) 실행 후 결과 받기
			// rset = stmt.executeQuery(sql);
			rset = pstmt.executeQuery();
			
			// 6_1) 현재 조회 결과가 담긴 ResultSet 에서 한 행씩 뽑아서 VO 객체에 담기
			// 여러 행 조회 일 경우 : 더 이상 뽑아낼 데이터가 없을 때까지 돌리기 => while (rset.next())
			if (rset.next()) { // 한 행 조회일 경우 (UNIQUE 제약조건에 의해) : if 문으로 조건 검사
				
				// 적어도 이 중괄호 안 에 들어왔다는 것은 조회된 결과가 있다는 것
				// => 한 개의 행에 대해서 각 컬럼마다 값을 매번 뽑아서 VO 객체의 필드로 가공
				
				// 조회된 한 행에 대한 데이터값들을 뽑아서 하나의 Member 객체에 담기
				// 조회된 행이 있을 경우에만 Member 객체 생성 => 매개 변수 생성자로
				m = new Member(rset.getInt("USERNO")
							 , rset.getString("USERID")
							 , rset.getString("USERPWD")
							 , rset.getString("USERNAME")
							 , rset.getString("GENDER")
							 , rset.getInt("AGE")
							 , rset.getString("EMAIL")
							 , rset.getString("PHONE")
							 , rset.getString("ADDRESS")
							 , rset.getString("HOBBY")
							 , rset.getDate("ENROLLDATE"));
			}
			
			// 이 시점 기준으로 봤을 때
			// 만약 조회된 회원이 있다면 m이라는 변수에 Member 타입 객체가 담겨있음
			// 만약 조회된 회원이 없다면 m이라는 변수에 null 값이 그대로 담겨있음
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
//			try {
//				// 7) 다 쓴 JDBC 용 객체들을 반드시 자원반납 (생성된 역순)
//				// ResultSet -> PreparedStatement -> Connection 순으로
//				rset.close();
//				pstmt.close();
//				conn.close();
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// 8) 결과 리턴
		return m; // 조회된 회원이 있다면 Member 객체, 없다면 null 값이 담겨있음
	}
	
	/**
	 * 사용자의 이름 키워드 검색 요청을 실행하기 위한 SELECT 문을 실행할 메소드
	 * @param keyword : 이름 키워드 검색을 위한 검색어
	 * @return ArrayList : 조회된 회원들의 목록
	 */
	public ArrayList<Member> selectByUserName(Connection conn, String keyword) {
		
		// SELECT 문 => ResultSet 객체 => 여러 행 조회 (ArrayList<Member>)
		
		// 변수 선언 및 초기화
		ArrayList<Member> list = new ArrayList<>();
		// Connection conn = null; // 접속된 DB의 정보를 담아둘 수 있는 변수
		PreparedStatement pstmt = null; // SQL 문 (SELECT) 실행 및 결과를 받을 수 있는 변수
		ResultSet rset = null; // SELECT 문 실행 후 결과들이 담길 변수
		
		// 실행할 SQL 문 (완성된 형태, 세미콜론 제외)
		// SELECT * FROM MEMBER WHERE USERNAME LIKE '%XXX%'
		// String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%" +keyword+ "%'";
		
		// 실행할 SQL 문 (미완성된 형태, 세미콜론 제외)
		// SELECT * FROM MEMBER WHERE USERNAME LIKE '%?%'
		// => 빈 자리를 메꾸는 순간 문법에 맞지 않는 구문이 될 것 (오류 발생)
		
		// 해결방법 1) 오라클에서의 연결 연산자 || 사용하여 문자열을 연이어주기
		// String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%'";
		// => 이 방법을 사용할 경우 메꿀 때 값을 그냥 제시하면 된다.
		
		// 해결방법 2) 통째로 구멍을 뚫고 메꿔줄 때 와일드카드를 덧붙이는 방법
		// => 단, 이 방법을 쓸 경우에는 메꿀 때 양 사이드에 꼭 자바에서의 연결연산자로 연결해줘야 한다.
		// SELECT * FROM MEMBER WHERE USERNAME LIKE ?  =>  "%" + keyword + "%"
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
		
		try {
			// 1) JDBC Driver 등록
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 (url, 계정명, 비밀번호)
			// conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) PreparedStatement 객체 생성 (Connection 객체로부터 얻을 수 있음)
			// stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			
			// 3_2) 현재 미완성인 SQL 문일 경우 완성 형태로 채우는 과정
			pstmt.setString(1, "%"+keyword+"%");
			
			// 4, 5) SQL 문 (SELECT) 실행 후 결과 받기
			// SELECT 문이므로 executeQuery() 메소드 실행
			// rset = stmt.executeQuery(sql);
			rset = pstmt.executeQuery();
			
			// 6_1) 현재 조회 결과가 담긴 ResultSet 에서 한 행씩 뽑아서 VO 객체에 담기
			// rset.next()로 커서를 한 행씩 아래로 내리면서 더 이상 뽑을 행이 없을 때까지 반복문
			while (rset.next()) {
				
				// 이 중괄호 내에 들어왔다면 뽑을 값이 있는 경우를 의미
				
				// 한 행에 담긴 데이터를 한 Member 타입의 객체에 옮겨담기
				// Member 객체를 매개변수 생성자로 생성과 동시에 값 담기
				Member m = new Member(rset.getInt("USERNO")
									, rset.getString("USERID")
									, rset.getString("USERPWD")
									, rset.getString("USERNAME")
									, rset.getString("GENDER")
									, rset.getInt("AGE")
									, rset.getString("EMAIL")
									, rset.getString("PHONE")
									, rset.getString("ADDRESS")
									, rset.getString("HOBBY")
									, rset.getDate("ENROLLDATE"));
				
				// 검색 결과 값을 담을 ArrayList에 객체 담기
				list.add(m);
				
				// 매개변수 생성자로 생성과 동시에 list.add() 로 넣기
				// list.add(new Member(rset.getInt("USERNO")
				//					 , rset.getString("USERID")
				//					 , rset.getString("USERPWD")
				//					 , rset.getString("USERNAME")
				//					 , rset.getString("GENDER")
				//					 , rset.getInt("AGE")
				//					 , rset.getString("EMAIL")
				//					 , rset.getString("PHONE")
				//					 , rset.getString("ADDRESS")
				//					 , rset.getString("HOBBY")
				//					 , rset.getDate("ENROLLDATE"));
			}
			
			// 이 시점 기준으로 봤을 때
			// 조회된 회원이 없다면 list.size() == 0 또는 list.isEmpty() == true
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
//			try {
//				// 7. JDBC 객체 자원 반납 (생성 순서의 역순)
//				// 생성 순서 : Connection -> PreparedStatement -> ResultSet
//				rset.close();
//				pstmt.close();
//				conn.close();
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// 8. 결과값 리턴
		// 검색된 결과값이 없다면 list.size() == 0 또는 list.isEmpty() == true
		return list;
	}
	
	/**
	 * 회원 정보 수정을 위한 UPDATE 구문을 실행하는 메소드
	 * @param m : 회원정보 수정을 위한 데이터
	 * @return int : 테이블에 UPDATE 되는 행의 갯수
	 */
	public int updateMember(Connection conn, Member m) {
		
		// UPDATE 문 => 처리된 행의 갯수 (int) => 트랜잭션 처리
		
		// 0) 필요한 변수 선언 및 초기화
		int result = 0; // 처리된 행의 갯수를 받을 수 있는 변수
		// Connection conn = null; // 접속된 DB의 정보를 담을 수 있는 변수
		PreparedStatement pstmt = null; // SQL 문 (UPDATE) 실행 후 결과를 받을 수 있는 변수
		
		// 실행할 SQL 문 (완성된 형태, 세미콜론 제외)
		/* UPDATE MEMBER
		   SET USERPWD = 'XXX'
			 , EMAIL = 'XXX'
			 , PHONE = 'XXX'
			 , ADDRESS = 'XXX'
		   WHERE USERID = 'XXX';
		*/
		/*
		String sql = "UPDATE MEMBER " + 
					 "SET USERPWD = '" + m.getUserPwd() + "'"
					 + ", EMAIL = '" + m.getEmail() + "'"
					 + ", PHONE = '" + m.getPhone() + "'"
					 + ", ADDRESS = '" + m.getAddress() + "' "
				   + "WHERE USERID = '"+ m.getUserId() + "'";
		*/
		
		// 실행할 SQL 문 (미완성된 형태, 세미콜론 제외)
		String sql = "UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? " + 
					 "WHERE USERID = ?";
		
		try {
			// 1) JDBC Driver 등록
			// Class.forName("oracle.jdbc.driver.OracleDriver");
		
			// 2) Connection 객체 생성
			// conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) PreparedStatement 객체 생성
			// stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			
			// 3_2) SQL 문 완성시키기
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
			// 4, 5) SQL 문 (UPDATE) 실행 후 결과 받기
			// result = stmt.executeUpdate(sql);
			result = pstmt.executeUpdate();
			
			// 6_2) 트랜잭션 처리
//			if (result > 0) {
//				conn.commit();
//			} else {
//				conn.rollback();
//			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
//			try {
//				// 7) JDBC 자원 반납 (생성 역순으로)
//				// PreparedStatement -> Connection
//				pstmt.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			
			JDBCTemplate.close(pstmt);
		}
		
		// 8) 결과 반환
		return result;
	}
	
	/**
	 * 회원 정보 삭제를 위한 DELETE 구문을 실행하는 메소드
	 * @param userId : 회원정보 삭제를 위한 데이터
	 * @return int : 테이블에서 DELETE 되는 행의 갯수
	 */
	public int deleteMember(Connection conn, String userId) {
		
		// DELETE 문 => 처리된 행의 갯수 (int) => 트랜잭션 처리
		
		// 0) 변수 선언 및 초기화
		int result = 0;
		// Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문 (DELETE)
		// DELETE FROM MEMBER WHERE USERID = 'XXX';
		// String sql = "DELETE FROM MEMBER WHERE USERID = '"+userId+"'";

		// 실행할 SQL 문 (미완성된 형태, 세미콜론 제외)
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		try {
			// 1) JDBC Driver 등록
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성
			// conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) PreparedStatement 객체 생성
			// stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			
			// 3_2) 완성하기
			pstmt.setString(1, userId);
						
			// 4, 5) SQL 문 실행 후 결과 받기 (DELETE)
			// result = stmt.executeUpdate(sql);
			result = pstmt.executeUpdate();
			
			// 6_2) 트랜잭션 처리
//			if (result > 0) {
//				conn.commit();
//			} else {
//				conn.rollback();
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
//			try {
//				// 7) 자원 반납
//				pstmt.close();
//				conn.close();
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			
			JDBCTemplate.close(pstmt);
		}
		
		// 8) 결과 반환
		return result;
	}
	
}
