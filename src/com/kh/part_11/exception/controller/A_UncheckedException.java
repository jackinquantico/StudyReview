package com.kh.part_11.exception.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

//충분히 예측 가능한 예외 (RuntimeException 클래스의 자손들)
public class A_UncheckedException {
	
	/*
	 * * RuntimeException
	 * - 프로그램 실행 중 발생하는 예외들
	 * 
	 * * RuntimeException 의 자식 클래스
	 * - ArrayIndexOutOfBoundsException : 배열의 부적절한 인덱스로 접근할 때 발생하는 예외
	 * - NegativeArraySizeException : 배열의 크기를 음수로 지정했을 경우 발생하는 예외
	 * - ClassCastException : 허용할 수 없는 형변환이 진행될 경우 발생하는 예외
	 * - NullPointerException : 참조변수값이 아직 null임에도 불구하고 접근하려고 할 때 발생하는 예외
	 * - ArithmeticException : 나누기 연산 시 0으로 나눌 때 발생
	 * - ...
	 * 
	 * => 이러한 RuntimeException과 관련된 예외들은 충분히 예측 가능하기 때문에
	 *    예외 자체가 애초에 발생이 안되게끔 조건문으로 해결 가능하긴 함. (선처리) -> 권장
	 *    굳이 예외처리 (예외가 발생했을 때 실행할 내용을 정의해두는 후처리) 할 필요가 없음.
	 */
	
	// 스캐너 객체를 전역 변수로 설정 : 클래스 내에 바로 선언
	// 클래스 내부 모든 메소드에서 가져다 쓸 수 있게
	
	Scanner sc = new Scanner(System.in);
	
	public void method1() {
		
		// ArithmeticException : 나누기 연산 시 0으로 나눌 때 발생
		
		// 사용자로부터 두 개의 정수값을 입력 받아 나눗셈 연산을 출력
		System.out.print("첫번째 정수 : ");
		int num1 = sc.nextInt();
		sc.nextLine();
		
		System.out.print("두번째 정수 (0 제외) : ");
		int num2 = sc.nextInt();
		sc.nextLine();
		
		/*
		int beforeresult = num1 / num2;		// num2 값이 0으로 들어오면 예외 발생
		System.out.println(beforeresult);
		*/
		
		/* 해결방법 1. 조건문으로 처리 (선처리) : 예외가 발생하지 않게 if문으로 조건 검사후 계산 진행
		//			=> 예외처리가 아님, 예방
		if (num2 != 0) {
			int result = num1 / num2;
			System.out.println("나눗셈 연산 결과 : "+result);
		} else {
			System.out.println("0으로 나눌 수 없습니다.");
		}
		
		System.out.println("프로그램 종료");
		*/
		
		// 해결방법 2. 예외처리 구문으로 해결 : try - catch문
		//			=> 예외가 발생했을 경우에 대비해서 실행할 내용을 정의해두는 것
		// 			=> 선택적으로 실행하는 일종의 조건문 개념
		
		/*
		 * try - catch문
		 * 
		 * [ 표현법 ]
		 * try {
		 * 		실행할 코드 (예외가 발생할 법한 구문 포함);
		 * } catch (발생할예외클래스명 변수명) {
		 * 		해당 예외가 발생할 경우 실행할 구문;
		 * }
		 */
		
		try {
			int result = num1 / num2;
			System.out.println("나눗셈 연산 결과 : " + result);
			
		} catch (ArithmeticException e) {
//			System.out.println("0으로 나눌 수 없습니다.");
			
			e.printStackTrace(); // 오버라이딩 된 메소드
			// 현재 예외가 어디에서 발생했는지 알려줌
		}
		
		System.out.println("프로그램 종료");
	}
	
	public void method2() {
		
		System.out.print("정수 입력 (0 제외) : ");
		try {
			int num = sc.nextInt(); // InputMismatchException 발생할법한 구문 (정수 이외의 값 입력할 경우)
			sc.nextLine();
			
			int result = 10 / num; // ArithmeticException 발생할법한 구문
			
			System.out.println("나눗셈 결과 : "+result);
			
		} catch (ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다.");
			
		} catch (InputMismatchException e) {
			System.out.println("정수만 입력하세요.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("프로그램 종료");
			
		}
		
		// 다중 catch 블럭 : 예외가 여러 개 발생할 가능성이 있을 경우, 일일이 catch 블럭을 추가하여
		// 더 디테일한 예외처리가 가능해짐
		
	}
	
	public void method3() {
		
		// ArrayIndexOutOfBoundsException : 배열의 부적절한 인덱스로 접근할 때 발생하는 예외
		// NegativeArraySizeException : 배열의 크기를 음수로 지정했을 경우 발생하는 예외
		
		System.out.print("배열의 크기를 입력하세요 : ");
		int size = sc.nextInt();
		sc.nextLine();
		
		/* if 문 활용
		if (size >= 101) {
			int[] arr = new int[size]; // NegativeArraySizeException 발생할법한 구문
			
			System.out.println("100번 인덱스 값 : "+arr[100]); // ArrayIndexOutOfBoundsException 발생할법한 구문
		}
		*/
		
		/*
		try {
			int[] arr = new int[size];
			
			System.out.println("100번 인덱스 값 : "+arr[100]);
		} catch (NegativeArraySizeException e) {
			System.out.println("배열의 크기로는 양수만 입력해주세요");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("부적절한 인덱스로 접근했습니다.");
		}
		*/
		
		/*
		try {
			int[] arr = new int[size];
			
			System.out.println("100번 인덱스 값 : "+arr[100]);
		} catch (RuntimeException e) {
			// 다형성을 적용하여 부모 타입의 예외 클래스로도 처리 가능
			// 다중 catch 블럭은 줄어들지만, 정확히 어떤 예외가 발생했는지 파악하기 어려워짐.
			System.out.println("예외 발생. 배열의 크기를 음수로 입력했거나 부적절한 인덱스로 접근.");
		}
		*/
		
		/*
		try {
			int[] arr = new int[size];
			System.out.println("100번째 인덱스 값 : "+arr[100]);
			
		} catch (RuntimeException e) { 		
			// 다형성을 적용해서 부모 예외클래스와 자식 예외 클래스의 catch블럭을 다중 작성하려면
			// => 자식 예외 클래스에서 부모 예외 클래스 순서로 작성해야 한다.
			// => 범위가 작은 자식 타입의 예외 클래스를 먼저 기술해야 한다.
			System.out.println("예외 발생");
		} catch (NegativeArraySizeException e) { // unreachable catch 블럭이 됨
			System.out.println("양수만 입력하세요");
		}
		*/
		
		try {
			int[] arr = new int[size];
			System.out.println("100번째 인덱스 값 : "+arr[100]);
			
		} catch (NegativeArraySizeException e) {
			System.out.println("양수만 입력하세요");
			
		} catch (RuntimeException e) {
			System.out.println("예외 발생");
		}
		
		
		/* instanceof 연산자
		try {
			int[] arr = new int[size];
			System.out.println("100번째 인덱스 값 : "+arr[100]);
		} catch (RuntimeException e) {
			System.out.println("예외 발생");
			if (e instanceof NegativeArraySizeException) {
				System.out.println("양수만 입력하세요");
			} else if (e instanceof ArrayIndexOutOfBoundsException) {
				System.out.println("부적절한 인덱스로 접근했습니다.");
			} else {
				e.printStackTrace();
			}
		}
		*/
		
		System.out.println("프로그램 종료");
	}
	
	/*
	 * * RuntimeException 관련 예외는
	 * - 조건문으로 해결 가능 : 예외 자체가 발생 안 되게끔 개발자가 소스코드로 핸들링 가능 (예외처리가 아님)
	 * - 예외처리 구문으로 해결 가능 : 예외가 발생했을 때에 대비해서 그 때 실행할 내용을 정의해두는 것 => 수습의 개념
	 * 
	 * 예측이 가능한 상황은 조건문으로 해결하는 것을 더 권장
	 * 예측이 불가능한 상황은 무조건 예외처리 구문으로 해결할 수 밖에 없음.
	 * 
	 * => RuntimeException 계열은 모두 충분히 예측 가능한 상황이기 때문에
	 *    조건문으로 해결하는 것을 권장
	 *    즉, 예외처리 구문을 작성하는 것이 필수는 아님. => Unchecked Exception
	 */
}
