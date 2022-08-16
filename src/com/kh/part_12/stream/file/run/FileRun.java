package com.kh.part_12.stream.file.run;

import java.io.File;
import java.io.IOException;

// File을 통한 IO(입출력)
// 자바 코드로 간단하게 파일 만드는 과정 (java.io.File 클래스)
public class FileRun {

	public static void main(String[] args) {
		
		// 헷갈리지 말 것 : File 객체를 만든다고 해서 바로 파일이 컴퓨터 상에 만들어지는 것은 아님. -> 만드는 메소드 호출 해야 만들어짐.
		
		try {
			// 1. 별도의 경로 지정을 하지 않고 오로지 파일 명만 제시해서 생성해보기
			// 현재 이 프로젝트 내에 파일이 생성
			File file1 = new File("test.txt"); // test.txt 파일 만들겠다고 파일명 제시
			file1.createNewFile(); // creatNewFile 메소드를 호출해서 실행되는 시점에 파일이 만들어짐. // IOException 발생할법한 구문
			
			
			// 2. 경로 지정을 한 파일명을 제시해서 생성해보기 ("C:\aaa\test.txt")
			// => 항상 존재하는 경로로 지정해야 함.
			//    C:\aaa 폴더가 이미 존재해야지만 test.txt 생성될 수 있음.
			// 자바에서 \ 역슬래쉬는 단독 사용할 수 없음. -> \\ : \ 로 간주
			File file2 = new File("C:\\aaa\\test.txt");
			// file2.createNewFile(); // java.io.IOException: 지정된 경로를 찾을 수 없습니다
			
			
			// 3. 폴더를 생성하고 나서 그 안에 파일 생성해보기
			File bbbFolder = new File("C:\\bbb");
			bbbFolder.mkdir(); // make directory, 폴더 생성 메소드
			
			File file3 = new File("C:\\bbb\\test.txt");
			file3.createNewFile();
			
			
			// 4. 별도의 경로 지정 없이 폴더 생성 후 파일 생성해보기
			// => 현재 이 프로젝트 내부에 바로 생성
			File folder = new File("test");
			folder.mkdir();
			
			File file = new File("test\\person.txt"); // 상대 경로 : test 폴더가 상대경로라서
			file.createNewFile();
			
			
			/*
			 * - 명확한 시작점을 잡은 경로를 제시했을 때 : 절대경로
			 *   C드라이브, D드라이브, .. => 루트 디렉토리 (컴퓨터의 최상위 폴더)
			 * 
			 * - 명확한 시작점이 없는 경로를 제시했을 때 : 상대경로
			 *   루트 디렉토리를 포함하지 않는 경로, 현재 내가 작업 중인 위치를 시작점으로 잡혀서 경로가 만들어짐
			 */
			
			// File 클래스에서 제공하는 유용한 메소드들
			System.out.println(folder.isFile()); // 폴더이므로 false 반환
			System.out.println(file.isFile()); // 파일이므로 true 반환
			
			System.out.println("파일명" + file.getName()); // person.txt
			System.out.println("상위 폴더 : "+file.getParent()); // test
			System.out.println("파일 용량 : "+file.length());
			System.out.println("절대 경로 : "+file.getAbsolutePath());
			
			// 파일 삭제 메소드
			file.delete();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("프로그램 종료");
	}

	/*
	 * 프로그램 상의 데이터를 외부매체(모니터, 스피커, 파일 등) 로 출력하거나
	 * 입력장치(키보드, 마우스 등) 로 입력받는 과정을 진행하고자 한다면
	 * 반드시 프로그램과 외부 매체와의 연결 통로가 만들어져야만 한다. => "스트림"
	 * 
	 * * 스트림의 특징
	 * - 단방향 : 입력이면 입력, 출력이면 출력
	 * 			입력, 출력용 스트림이 각각 따로따로 존재한다.
	 * 			즉, 동시에 입출력을 하고자 한다면 적어도 2개의 스트림이 필요하다.
	 * 			(하나의 스트림으로는 절대 안 됨)
	 * - 선입선출(FIFO) : 먼저 전달한 값이 먼저 나오게 된다.
	 * 					Queue 구조를 가지고 있음.		<=> Stack 구조(FILO)
	 * - 데이터 전송시 시간지연 문제 (time-out, time-delay)가 발생할 수 있다. 
	 * 
	 * * 스트림의 구분
	 * - 통로의 사이즈
	 * 		바이트 스트림 : 한 번에 1byte 짜리만 이동할 수 있는 좁은 통로 => 입력(XXXInputStream) / 출력(XXXOutputStream)
	 * 		문자 스트림 : 한 번에 2byte씩 이동할 수 있는 넓은 통로 ⇒ 입력(XXXReader) / 출력(XXXWriter)
	 * 
	 * - 외부매체와의 직접적인 연결 여부
	 * 		기반 스트림 : 외부 매체와 직접적으로 연결되는 통로
	 * 		보조 스트림 : 기반 스트림만으로 부족한 성능을 향상시켜주는 용도의 스트림, 기반 스트림이 반드시 필요함.
	 * 				  (외부 매체와 직접적으로 연결이 불가, 단독 사용 불가, 단독 객체 생성 불가)
	 * 				  (ex) 속도 향상, 자료형에 맞춰 형변환, 객체 단위로 입출력하게 도와준다거나)
	 * 				    단, 보조스트림은 단독으로 사용 불가, 반드시 기반스트림이 필수
	 */
	
}
