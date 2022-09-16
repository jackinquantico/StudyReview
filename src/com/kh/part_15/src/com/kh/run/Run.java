package com.kh.run;

import com.kh.view.MemberView;

public class Run {

	/*
	 * * MVC 패턴
	 * M : Model, 데이터 처리 담당 (데이터들을 담기위한 VO, 데이터들이 보관된 공간과 직접 접근해주는 DAO)
	 * V : View, 화면을 담당 (사용자가 보는 시각적인 요소, 출력 및 입력)
	 * C : Controller, 사용자의 요청을 담당 (사용자의 요청을 처리 후 해당되는 응답 화면 지정)
	 */
	
	public static void main(String[] args) {
		
		// 프로그램 실행만을 담당
		// 사용자가 보게 될 첫 화면을 띄워주는 역할
		// MemberView mv = new MemberView();
		// mv.mainMenu();
		
		// 변수에 담지 않고 생성과 동시에 호출
		new MemberView().mainMenu();

	}

}
