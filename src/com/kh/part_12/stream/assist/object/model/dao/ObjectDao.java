package com.kh.part_12.stream.assist.object.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.kh.part_12.stream.assist.object.model.vo.Phone;

//객체 단위 입출력
public class ObjectDao {
	
	// 프로그램 ---> 외부 매체 (파일)
	// 출력 : 파일로 저장
	public void fileSave() {
		
		// FileOutputStream : 파일에 데이터를 1 byte 단위로 출력할 수 있는 기반 스트림
		// + 
		// ObjectOutputStream : 객체 단위로 출력할 수 있는 보조 스트림
		
		// 테스트용 객체 생성
		Phone ph = new Phone("아이폰", 1300000);
		
		// 0. 스트림 참조 변수 선언 및 null로 초기화
		ObjectOutputStream oos = null;
		
		try {
			// 1. 스트림 객체 생성 == 연결 통로 만들기
			oos = new ObjectOutputStream(new FileOutputStream("phone.txt"));
			
			// 2. 파일로 출력 : 객체 단위로 출력시
			// ObjectOutputStream 클래스에서 제공하는 writeObject() 메소드 사용
			oos.writeObject(ph);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// 3. 자원 반납하기 == 연결 통로 끊기 => 필수
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		
		
	} // fileSave

	// 프로그램 <--- 외부 매체 (파일)
	// 입력 : 파일 읽기
	public void fileRead() {
		
		// FileInputStream : 파일로부터 데이터를 1byte 단위로 읽어들이기 위해 사용되는 기반 스트림
		// +
		// ObjectInputStream : 객체 단위로 입력받기 위해 사용되는 보조 스트림
		
		/*
		// 0. 스트림 참조 변수 선언 및 null로 초기화
		ObjectInputStream ois = null;
		
		try {
			// 1. 스트림 객체 생성 == 연결 통로 만들기
			ois = new ObjectInputStream(new FileInputStream("phone.txt"));	
			
			// 2. 입력 : 파일 읽어들이기
			// Object obj = ois.readObject();
			Phone ph = (Phone)ois.readObject(); // 다형성에 의해 다운캐스팅(강제형변환)
			System.out.println(ph);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			
			// 3. 자원 반납하기 == 연결통로 끊기 => 필수
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		*/
		
		// try with resource 구문 버전
		// 보조스트림 객체 생성과 기반 스트림 객체 생성 동시에 진행 -> 반납할 필요 없음
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("phone.txt"))) {
			
			// 입력 : 파일 읽어들이기
			Phone ph = (Phone)ois.readObject();
			System.out.println(ph);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	} // fileRead
}
