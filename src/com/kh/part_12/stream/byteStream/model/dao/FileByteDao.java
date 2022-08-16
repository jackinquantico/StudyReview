package com.kh.part_12.stream.byteStream.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//DAO : Data Access Object 
//데이터가 보관되어 있는 공간과 직접 접근해서 데이터를 입출력하는 용도의 클래스
public class FileByteDao {

	// 프로그램 ---> 외부매체(File) 
	// 출력 : 프로그램 내의 데이터를 파일로 내보내기 (즉, 파일에 기록하겠다, 저장하겠다)
	public void fileSave() {
		
		// FileOutputStream 객체 : 파일로 데이터를 1byte 단위로 출력하는 스트림
	
		// 1. FileOutputStream 객체 생성하기 : 연결통로 만들기
		// => 해당 파일과 직접 연결되는 통로를 만들겠다.
		// => 파일명을 매개변수로 생성자 호출
		// FileNotFoundException 발생할법한 구문
		// FileOutputStream fout = new FileOutputStream("a_byte.txt");
		// 해당 파일이 존재하지 않는다면 해당 파일이 생성되면서 연결 통로가 만들어짐
		// 			존재하는 파일이면 연결 통로만 바로 만들어짐.
		
		// 1-1. FileOutputStream 참조변수 선언 및 null로 초기화
		FileOutputStream fout = null;
		
		try {
			// 1-2. FileOutputStream 객체 생성 및 할당
			fout = new FileOutputStream("a_byte.txt" /* , false */ );
			// fout = new FileOutputStream("a_byte.txt", true);
			// => 기존에 해당 파일이 있을 경우, 덮어씌워지는 특징이 있음
			
			// 2. 연결통로로 데이터를 출력 : write() 메소드 사용
			// => 1byte 범위만 전송 가능 : -128 ~ 127까지의 숫자
			// 	  (단, 파일에 기록되기를 해당 숫자의 고유한 문자가 기록됨 : 아스키코드)
			fout.write(97); // int타입 97이 'a' 로 변환됨.	 // 'a' 기록
			fout.write('b'); // 문자와 int는 자동형변환되므로 가능 // 'b' 기록
			// fout.write('김'); // '김'은 2byte이기 때문에 한글이 깨져서 @ 로 저장됨
								 // 한글은 바이트스트림으로 내보낼 수 없음. 바이트스트림으로는 제한적임
			
			byte[] bArr = {99, 100, 101}; 
			fout.write(bArr); // 아스키코드표에 의해 자동형변환되어 // 'c', 'd', 'e' 기록
			
			fout.write(bArr, 1, 2); // bArr의 1번째 인덱스부터 2개를 내보내기
									// 100, 101이 자동형변환되어 // 'd', 'e' 기록
			
			// 3. 스트림을 다 사용한 뒤에는 자원을 반납해줘야 한다.
			// 즉, 연결통로를 끊겠다.
			// fout.close(); // 위의 코드에서 예외가 발생했을 경우, 이 코드는 실행이 안 될 수도 있음.
			
		} catch (FileNotFoundException e) { // 존재하지 않는 경로를 제시했을 때
			e.printStackTrace();
		} catch (IOException e) { // 입출력 상황에서 어느 오류든지 발생했을 때
			e.printStackTrace();
		} finally { // 어떤 예외가 발생하던지, 안 하던지 간에 반드시 실행할 구문을 작성하는 블럭
			
			// 3. 스트림을 다 이용한 뒤에는 반드시 자원을 반납해야 하기 때문에
			// 어떤 예외가 발생하던지간에 반드시 실행해야 하는 구문을 작성하는 finally에 작성
			// close() 도 IOException을 발생시키기 때문에 try-catch문으로 감싸줘야 한다.
			try {
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	} // fileSave
	
	// 프로그램 <--- 외부매체(File)
	// 입력 : 파일로부터 데이터를 읽어오기, 불러오기
	public void fileRead() {
		
		// FileInputStream : 파일로부터 데이터를 1byte 단위로 입력받는 스트림
		// 1_1. FileInputStream 참조변수 선언 및 null로 초기화
		FileInputStream fin = null;
		
		try {
			// 1_2. FileInutStream 객체 생성하기 ==연결통로 만들기
			fin = new FileInputStream("a_byte.txt");
			// 입력받을 때에는 반드시 존재하는 파일명으로 제시
			
			// 2. 파일로부터 읽어들이기 == 입력받기 : read()
			// => 단, 1byte 단위로 하나씩 읽어옴
			// 출력시에는 숫자 -> 문자
			// 입력시에는 문자 → int형으로 불러들여와짐 
			// 				(char) 강제 형변환해서 문자 형태로 볼 수 있음
			/*
			System.out.println(fin.read());
			System.out.println(fin.read());
			System.out.println(fin.read());
			System.out.println(fin.read());
			System.out.println(fin.read());
			System.out.println(fin.read());
			System.out.println(fin.read());
			// 만약 원래 파일의 글자수를 넘어서면 -1 이 리턴됨.
			*/
			
			/* 2. 반복문을 사용해서 파일 읽어들이기
			// 제대로 읽히지 않음. 건너뛰어짐. 한 번 반복에 2개의 값이 소모됨.
			while(fin.read() != -1) { // 읽어들인 결과값이 -1이 아닌 동안만
				System.out.println(fin.read());
			}
			*/
			
			/* 해결방법 1. 무한반복으로 매번 조건검사하기
			while (true) {
				int value = fin.read(); // 단 한 번 읽어온 값을 value 변수에 담아서
				
				if (value == -1) { // 조건검사해서
					break;
				}
				
				System.out.println(value); // 조건에 맞으면 출력
			}
			*/
			
			// 해결방법 2. 조건식 내부에 변수 대입 구문을 활용하는 방법
			int value = 0;
			while ((value = fin.read()) != -1) {
				System.out.println(value);
			}
				
		} catch (FileNotFoundException e) { // 자식 catch 블럭이 더 위에
			e.printStackTrace();
		} catch (IOException e) { // 부모 catch 블럭이 가장 나중에
			e.printStackTrace();
		} finally {
			
			// 3. 연결통로 끊고 자원 반납하기 : finally 블럭에 작성
			try {
				fin.close();		  // IOException이 발생할법한 구문
			} catch (IOException e) { // try-catch문으로 예외 처리
				e.printStackTrace();
			}
		}
		
	} // fileRead
}
