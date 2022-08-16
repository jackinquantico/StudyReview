package com.kh.part_12.stream.charStream.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//DAO : Data Access Object
public class FileCharDao {

	// 프로그램 ---> 외부매체 (File)
	// 출력
	public void fileSave() {
		
		// FileWriter : 파일로 데이터를 2byte 단위로 출력하는 스트림
		
		// 1_1. 스트림 참조변수 선언 및 null 로 초기화
		FileWriter fw = null;
		
		try {
			// 1_2. 스트림 객체 생성 == 연결통로 : 파일명을 생성자 매개변수로 제시
			fw = new FileWriter("b_char.txt");
			// 출력일 경우 없는 파일명을 제시하더라도 파일 생성 후 연결 통로가 만들어짐.
			
			// 2. 출력 == 데이터 내보내기 : write() 메소드 사용
			fw.write("와! IO 재밌다..ㅎ"); // 내부적으로 문자 한 개씩 끊어서 이동
			fw.write('A');
			fw.write(97);
			fw.write("\n");
			
			char[] cArr = {'k','i','w','i'};
			fw.write(cArr);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
			// 3. 연결통로 끊고 자원 반납
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// 프로그램 <--- 외부매체 (File)
	// 입력
	public void fileRead() {
		
		// FireReader : 파일로부터 데이터를 2byte 단위로 입력받는 스트림
		
		// 1_1. 스트림 참조변수 선언 및 null 로 초기화
		FileReader fr = null;
		
		try {
			// 1_2. 스트림 객체 생성 == 연결통로 : 파일명을 생성자 매개변수로 제시
			fr = new FileReader("b_char.txt");
			// 입력 구문에서는 반드시 존재하는 파일 경로로 제시해야 한다.
			
			// 2. 입력 == 데이터 읽기 : read() 메소드 사용
			// 내부적으로 문자 하나씩 끊어서 전달하는 것
			//System.out.println(fr.read());
			// 문자 기반 스트림도 마찬가지로 문서의 끝을 만났을 때 -1을 반환함
			
			// 반복문 활용
			int value = 0;
			while ((value = fr.read()) != -1) {
				System.out.print((char)value);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// 3. 연결통로 끊고 자원 반납 : close() 메소드 사용
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
