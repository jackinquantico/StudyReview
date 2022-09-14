package com.kh.run;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

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
		
		
		
		/*
		// Properties 복습
		// Properties : Map 계열의 컬렉션 (key + value 세트로 담는 게 특징)
		// 				주로 외부 설정 파일 읽어오기 또는 파일 형태로 출력하고자 할 때 사용
		//				(파일 입출력에 특화된 컬렉션 : key, value 모두 String 타입으로 넣어주는 것을 권장)
		
		// .properties / .xml 파일 형식으로 내보내기
		Properties prop = new Properties();
		
		// setProperty() : String 타입으로만 key, value 를 넣을 수 있게 만들어진 메소드
		prop.setProperty("List", "ArrayList");
		prop.setProperty("Set", "HashSet");
		prop.setProperty("Map", "HashMap");
		prop.setProperty("Map", "Properties"); // key 값이 중복되면 value 값은 덮어씌워진다.
											   // 총 3개의 key + value 세트가 들어있음
		
		System.out.println(prop);
		// {키=밸류, 키=밸류, ... } 형태로 출력됨
		// 키 값은 중복 불가, 넣은 순서 유지 X
		
		try {
			// prop 에 담긴 내용물들을 파일로 내보내기 (출력)
			// store / storeToXML (출력스트림객체, "파일에대한설명문구")
			prop.store(new FileOutputStream("resources/test.properties"), "test.properties");
			prop.storeToXML(new FileOutputStream("resources/test.xml"), "test.xml");
			// java.io.FileNotFoundException: resources\test.properties (지정된 경로를 찾을 수 없습니다)
			// 없는 이름의 파일명만 제시하면 제대로 잘 만들어짐.
			// 없는 폴더명/파일명을 제시했기 때문에 경로를 찾을 수 없다고 오류 발생
			
			// resources 폴더 : 주로 프로젝트 내의 외부 파일들을 저장하는 역할의 폴더 (이미지 파일 등)
			// 				  resources 폴더 만든 뒤 다시 실행하면 파일이 오류 없이 잘 만들어짐
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		/*
		// .properties 파일 읽어들이기
		// load(입력스트림객체)
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 출력
		// getProperty("key") 제시하면 "value" 리턴
		System.out.println(prop.getProperty("driver"));
		System.out.println(prop.getProperty("url"));
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));
		System.out.println(prop.getProperty("pass")); // 없는 key값 제시하면 null 반환
		*/
		
		/*
		// .xml 파일 읽어들이기
		// loadFromXML(입력스트림객체)
		Properties prop = new Properties();
		
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 출력
		// getProperty("key") : "value"
		System.out.println(prop.getProperty("select"));
		System.out.println(prop.getProperty("insert"));
		System.out.println(prop.getProperty("update"));
		*/
	}

}
