package com.kh.part_05;

import java.util.Arrays;

public class B_ArrayCopy {

	// 깊은 복사 1번째 방법
	public void method2() {
		
		// 1. for문을 이용하는 방법
		// 새로운 배열을 선언 및 할당까지 끝낸 다음에
		// 원본 배열로부터 일일이 for문을 이용해 옮겨서 대입하는 방법
		
		// 원본 배열
		int[] origin = {1,2,3,4,5};
		
		// 복사본 배열 선언 및 할당 - 기본값으로 초기화된 상태
		int[] copy = new int[origin.length];
		
		for (int i=0; i<origin.length; i++) {
			copy[i] = origin[i]; 
		}
		
		System.out.println("== 복사본 배열 출력 ==");
		for (int i=0; i<copy.length; i++) {
			System.out.print(copy[i]+" ");
		}
		
		copy[2] = 99;
		
		System.out.println("\n== 복사본 배열 수정 후");
		for (int i=0; i<copy.length; i++) {
			System.out.print(copy[i]+" ");
		}
		
		System.out.println("\n== 복사본 배열 수정 후 원본 ==");
		for (int i=0; i<origin.length; i++) {
			System.out.print(origin[i]+" ");
		}
		
		System.out.println("\n원본 배열 해시코드 : " + origin.hashCode());
		System.out.println("복사본 배열 해시코드 : " + copy.hashCode());
		// 원본이 훼손되지 않음 : 깊은 복사
		
	} // method2

	// 깊은 복사 2번째 방법
	public void method3() {
		
		// 2. 새로운 배열을 생성(선언 및 할당) 후
		// 	  System 클래스에서 제공하는 arraycopy 메소드 호출하여 복사
		
		// 원본 배열
		int[] origin = {1,2,3,4,5};
		
		// 복사본 배열
		int[] copy = new int[10]; // 기본값으로 초기화된 상태
		
		// System 클래스의 arraycopy 메소드 사용법
		// [ 표현법  ]
		// System.arraycopy(원본배열명, 복사를시작할인덱스, 복사본배열명, 복사를시작할인덱스, 복사할개수);
		
		System.arraycopy(origin, 0, copy, 0, 5);
		// copy = { 1,2,3,4,5,0,0,0,0,0 } 로 출력됨.

		// System.arraycopy(origin, 0, copy, 2, 5);
		// copy = { 0,0,1,2,3,4,5,0,0,0 }
		
		// System.arraycopy(origin, 0, copy, 1, 3);
		// copy = { 0,1,2,3,0,0,0,0,0,0 }
		
		// System.arraycopy(origin, 2, copy, 1, 3);
		// copy = { 0,3,4,5,0,0,0,0,0,0 }
		
		// System.arraycopy(origin, 2, copy, 9, 2);
		// copy = { 0,0,0,0,0,0,0,0,0,3 ... 4 } 
		// => ArrayIndexOutOfBoundsException : 복사 과정에서 인덱스 범위를 벗어남
		
		System.out.println("== 복사본 배열 출력 ==");
		for (int i=0; i<copy.length; i++) {
			System.out.print(copy[i]+ " ");
		}
		
		System.out.println("\n원본 해시코드 : " + origin.hashCode());
		System.out.println("복사본 해시코드 : " + copy.hashCode());
		
		// 다른 주소값을 가지고 있음 == 다른 곳을 참조하고 있다.
		// => 깊은 복사가 이루어져 배열을 수정할 때 서로 영향을 받지 않음.
	}
	
	// 깊은 복사 3번째 방법
	public void method4() {
		
		// 3. Arrays 클래스의 copyOf() 메소드 호출
		
		// 원본 배열
		int[] origin = {1,2,3,4,5};
		
		// 복사본 배열
		// Arrays.copyOf() 메소드 사용방법
		// [ 표현법 ] 복사본 배열 = Arrays.copyOf(원본배열명, 복사할개수);
		
		int[] copy = Arrays.copyOf(origin, 5);
		// 복사본 배열의 크기는 copyOf에서 작성한 복사할개수가 된다.
		
		System.out.println("== 복사본 배열 출력 ==");
		for (int i=0; i<copy.length; i++) {
			System.out.print(copy[i]+" ");
		}
		
		System.out.println("\n원본 해시코드 : " + origin.hashCode());
		System.out.println("복사본 해시코드 : " + copy.hashCode());
		// 다른 주소값을 가지고 있음 == 다른 곳을 참조하고 있다.
		
	} // method4
	
	// 깊은 복사 4번째 방법
	public void method5() {
		
		// 4. clone 메소드 호출하여 복사
		
		// 원본 배열
		int[] origin = {1,2,3,4,5};
		
		// 복사본 배열
		// clone 메소드
		// [ 표현법  ] 복사본배열명 = 원본배열명.clone(); -> 두 자료형이 동일해야 함
		// 별도의 옵션 존재하지 않음. 원본 배열을 그대로.
		int[] copy = origin.clone();
		
		System.out.println("== 복사본 배열 출력 ==");
		// [1, 2, 3, 4, 5] 형태
		/*
		System.out.print("[");
		for (int i=0; i<copy.length; i++) {
			if (i != copy.length-1) {		// 마지막 인덱스 전까지
				System.out.print(copy[i]+", ");
			} else {						// 마지막 인덱스는
				System.out.print(copy[i]);
			}
		}
		System.out.print("]");
		*/
		
		// Arrays 클래스의 toString 메소드 호출
		// Arrays.toString(출력할배열명)
		System.out.println(Arrays.toString(copy));
		
		System.out.println("원본 배열의 해시코드 : " + origin.hashCode());
		System.out.println("복사본 배열의 해시코드 : " + copy.hashCode());
		// 다른 주소값을 가지고 있음 == 다른 곳을 참조하고 있다.
		
	}

}
