package com.kh.part_10.string;

import java.util.StringTokenizer;

public class A_StringTokenizer {


	public void method() {
		
		// 문자열을 분리시키는 방법
		
		String str = "Java,Oracle,JDBC,HTML,Server,Spring";
		
		// 구분자를 제시해서 해당 문자열을 분리시키는 방법
		// 방법 1. 분리된 문자열들을 String[] 배열에 담아서 관리하고자 할 때
		// 		   문자열.split(String 구분자) : 반환형 String[]
		String[] arr = str.split(",");
		
//		for (int i=0; i<arr.length; i++) {
//			System.out.println(arr[i]);
//		}
		
		// 문자열 배열의 경우 for문을 다르게 사용할 수 있다.
		// 향상된 for문, foreach 문
		// for (값을 받아줄 변수 선언문 : 접근할 배열 또는 컬렉션명)  {  }
		for (String s : arr) {
			System.out.println(s);
		}
		
		System.out.println("=============================");
		
		// 방법 2. 분리된 각각의 문자열들을 "토큰"(단어) 단위로 취급하고 싶을 때
		// 		 java.util.StringTokenizer 클래스 이용
		// 		 StringTokenizer stn = new StringTokenizer(분리시키고자하는문자열, 구분자);
		
		StringTokenizer stn = new StringTokenizer(str, ",");
		
		// 분리된 문자열의 개수를 알고 싶을 때
		System.out.println("분리된 문자열의 개수 : "+stn.countTokens());
		
		// 출력
//		System.out.println(stn.nextToken()); // Java
//		System.out.println(stn.nextToken()); // Oracle
//		System.out.println(stn.nextToken()); // JDBC
//		System.out.println(stn.nextToken()); // HTML
//		System.out.println(stn.nextToken()); // Server
//		System.out.println(stn.nextToken()); // Spring
		
		// 이 시점에서 다시 한 번 nextToken() 호출하면
		// NoSuchElementException 발생 : 더 이상 찾을 요소가 없음.
		// => 현재 남아있는 stn 토큰 개수의 범위를 벗어났을 때 발생하는 오류
		
//		System.out.println("현재 담겨있는 문자열의 개수 : "+stn.countTokens());
		
//		for (int i=0; i<stn.countTokens(); i++) {
//			System.out.println(stn.nextToken());
//			// Java, Oracle, JDBC 출력
//		}
		
		// i=0; token = 6, true; => Java 출력 => i++, token = 5;
		// i=1; token = 5, true; => Oracle 출력 => i++, token = 4;
		// i=2; token = 4, true; => JDBC 출력 => i++, token = 3;
		// i=3; token = 3, false => 반복문 종료
		
		// 해결방법 1. 변수 활용
//		int count = stn.countTokens();
//		
//		for (int i=0; i<count; i++) {
//			System.out.println(stn.nextToken());
//		}
		
		// 해결방법 2. while문 사용
		// stn.hasMoreTokens() : 반환할 token의 유무에 따라 true/false 반환
		while (stn.hasMoreTokens()) { // stn 공간에 남아있는 토큰이 있을 동안만 반복
			System.out.println(stn.nextToken());
		}
		
	}
}
