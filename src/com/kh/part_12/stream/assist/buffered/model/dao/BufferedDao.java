package com.kh.part_12.stream.assist.buffered.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedDao {
	
	// BufferedWriter (출력용) / BufferedReader (입력용)

	// 프로그램 ---> 외부 매체 (파일)
	public void fileSave() {
		
		// FileWriter : 2바이트 단위로 파일로 데이터를 내보내는 기반 스트림
		// + 
		// BufferedWriter : 버퍼 공간을 제공해주는 보조 스트림 (속도 향상)
		
		/*
		// 1_1. 기반스트림 참조 변수 선언 및 null 초기화
		FileWriter fw = null;
		// 2_1. 보조스트림 참조 변수 선언 및 null 초기화
		BufferedWriter bw = null;
		
		try {
			// 1_2. 기반 스트림 객체 생성 (메인 연결통로 만들기)
			fw = new FileWriter("c_buffer.txt");
			
			// 2. 보조 스트림 객체 생성
			bw = new BufferedWriter(fw);
			
			// 3. 출력 : BufferedWriter 객체에서 제공하는 write() 메소드 사용
			// fw.write(); // FileWriter 객체에서 제공하는 write() 메소드를 사용하면 성능 향상 기대할 수 없음.
			bw.write("가나다라마바사아 ");
			bw.newLine(); // 개행 메소드
			bw.write("안녕하세요\n");
			bw.write("저리가세요.");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// 4. 자원 반납
			// 주의할 점 : 기반 스트림과 보조 스트림을 반납할 때
			// 			객체가 생성된 순서의 역순으로 반납
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		*/
		
		// 1_1. 기반스트림 참조 변수 선언 및 null 초기화
		// FileWriter fw = null;
		// 2_1. 보조스트림 참조 변수 선언 및 null 초기화
		BufferedWriter bw = null;
		
		try {			
			// 코드 한 줄로 줄여서 표현
			// 2. 보조 스트림 객체 생성 + 기반 스트림 객체 생성
			bw = new BufferedWriter(new FileWriter("c_buffer.txt"));
			
			// 3. 출력 : BufferedWriter 객체에서 제공하는 write() 메소드 사용
			// fw.write(); // FileWriter 객체에서 제공하는 write() 메소드를 사용하면 성능 향상 기대할 수 없음.
			bw.write("가나다라마바사아 ");
			bw.newLine(); // 개행 메소드
			bw.write("안녕하세요\n");
			bw.write("저리가세요.");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// 4. 자원 반납
			// 코드를 한 줄로 줄이면 기반 스트림 반납 구문도 생략 가능
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	} // fileSave
	
	// 프로그램 <--- 외부 매체 (파일)
	public void fileRead() {
		
		// FileReader : 파일과 직접적으로 연결해서 한 번에 2byte 단위로 데이터 입력할 수 있는 기반 스트림
		// +
		// BufferedReader : 버퍼 공간을 제공해주는 보조 스트림 (속도 향상)
		
		/*
		// 1_1. 참조 변수 선언 및 null 값으로 초기화
		BufferedReader br = null;
		
		try {
			// 1_2. 객체 생성 == 연결 통로 만들기
			br = new BufferedReader(new FileReader("c_buffer.txt"));
			
			// 2. 입력
			// System.out.println(br.read()); // 글자 하나 단위씩 읽어들임
			// System.out.println(br.readLine()); // 한 줄 단위씩 읽어들임
			// System.out.println(br.readLine());
			// System.out.println(br.readLine());
			// 문서의 끝을 만나면 null을 반환
			
			// 반복문 활용
			String input = ""; // null이든 빈문자열이든 상관 없음.
			while ((input = br.readLine()) != null) {
				System.out.println(input);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// 3. 연결 통로 끊기 == 자원 반납
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
		
		// try with resource 구문 (jdk7 버전부터 가능)
		/* [ 표현법  ]
		 * try (나중에 반납해야하는 객체 생성 구문) {
		 * 		예외가 발생할법한 구문;
		 * } catch (예외클래스명 e) {
		 * 		해당 예외 발생시 실행할 구문;
		 * }
		 * 
		 * => 스트림 객체 생성구문을 try()에 작성하게 되면
		 *    스트림 객체 생성 후 해당 try 블럭 내용이 실행된 후
		 *    알아서 자원 반납이 된다.
		 */
		
		try (BufferedReader br = new BufferedReader(new FileReader("c_buffer.txt"))) {
			
			// 반복문 활용
			String value = null;
			while ((value = br.readLine()) != null) {
				System.out.println(value);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	} // fileRead
}
