package com.kh.part_11.exception.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//예측 불가능한 예외들 (IOException의 자손)
public class B_CheckedException {

	/*
	 * * CheckedException은 반드시 예외처리를 해야하는 예외들
	 * 	 (즉, 예측 불가한 곳에서 발생하기 때문에 미리 예외처리 구문을 작성해야 한다.)
	 * => 주로 사람이 아닌 외부 매체와 어떤 입출력이 발생할 때 예외가 나타남 
	 */
	
	public void method1() throws IOException {
		
		/*
		try {
			method2(); // method2에서 발생할 법한 예외지만, method2에서 처리를 바로 하지 않고
			
		} catch (IOException e) { // method1에서 try-catch문으로 처리해줌.
			
			System.out.println("예외 발생됨");
		}
		*/
		
		method2();
	}
	
	// throws
	public void method2() throws IOException {
		
		// Scanner와 같이 키보드로 값을 입력받을 수 있는 객체 (단, 문자열로만 가능)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("아무 문자열이나 입력하세요 : ");
		
		/* 1. try-catch문 : 예외가 발생할법한 코드가 있는 이 자리 그대로 바로 여기에서 예외 처리
		try {
			String str = br.readLine(); // 반드시 예외처리해야함.
			// readLine() 메소드를 호출시 IOException이 발생할 수도 있음을 컴파일 에러로 알려줌 (빨간 밑줄)
			
			System.out.println("문자열의 길이 : "+str.length());
			
		} catch (IOException e) { // checked exception : 예측할 수 없음
			System.out.println("예외 발생");
		}
		*/
		
		// 2. throws 키워드 : 떠넘기기, 위임하기
		// 					지금 여기서 예외를 바로 처리하지 않고, 현재 이 메소드를 호출한 곳으로 떠넘기겠다.
		String str = br.readLine();
		// readLine() 메소드의 선언부에서 throws IOException 이 정의되어 있어서
		// 호출시 무조건 IOException을 처리해줘야 한다.
		System.out.println("문자열의 길이 : "+str.length());
		
	}
}
