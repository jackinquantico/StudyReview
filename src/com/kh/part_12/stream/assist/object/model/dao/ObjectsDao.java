package com.kh.part_12.stream.assist.object.model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.kh.part_12.stream.assist.object.model.vo.Phone;


public class ObjectsDao {
	
	// 프로그램 ---> 외부매체 (파일)
	// 출력
	public void fileSave() {
		
		// FileOutputStream + ObjectOutputStream
		// 1 바이트짜리 좁은 통로
		
		// 테스트용 객체 배열 생성
		Phone[] arr = new Phone[3];
		
		// 테스트 데이터 담기
		arr[0] = new Phone("아이폰", 1300000);
		arr[1] = new Phone("갤럭시", 1500000);
		arr[2] = new Phone("플립폰", 2000000);
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("phones.txt"))) {
			
			// ObjectOutputStream 객체에서 제공하는 writeObject() 메소드 사용
			// oos.writeObject(arr[0]);
			// oos.writeObject(arr[1]);
			// oos.writeObject(arr[2]);
			
			// 반복문 이용해서 파일로 내보내기
			for (int i=0; i<arr.length; i++) {
				oos.writeObject(arr[i]);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	// 프로그램 <--- 외부매체 (파일)
	// 입력
	public void fileRead() {
		
		// FileInputStream + ObjectInputStream
		// 1 바이트짜리 좁은 통로
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("phones.txt"))) {
			
			// ObjectInputStream 객체에서 제공하는 readObject() 메소드 사용
			// toString() 메소드를 오버라이딩 했기 때문에 굳이 형변환하지 않고 바로 출력해보기 = 동적바인딩
			// System.out.println(ois.readObject() /* .toString() 생략된 형태 */);
			// System.out.println(ois.readObject());
			// System.out.println(ois.readObject());
			// System.out.println(ois.readObject());
			// 파일의 끝을 만났을 때 EOFException 예외를 발생 // End Of File 의 약자
			
			// EOFException 이 발생될 때까지만 반복 돌리기
			// => 예측 불가능한 오류이기 때문에 정확한 조건을 세울 수 없음.
			while (true) { // 일단 무한 반복
				System.out.println(ois.readObject());
			}
			// 무한 반복되다 언젠가 EOFException이 발생하는 순간		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {  // EOFException 캐치 블록으로 이동
			System.out.println("파일을 다 읽었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("프로그램 종료");
	}
}
