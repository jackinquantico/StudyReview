package com.kh.part_12.stream.assist.object.run;

import com.kh.part_12.stream.assist.object.model.dao.ObjectDao;
import com.kh.part_12.stream.assist.object.model.dao.ObjectsDao;

public class ObjectRun {

	/*
	 * * 보조스트림
	 * 기반 스트림만으로 부족한 기능을 추가해주는 보조 역할의 스트림
	 * 절대 단독으로 객체 생성 불가
	 * 
	 * 객체 단위로 입출력 할 수 있는 기능을 제공하는 보조 스트림
	 * => ObjectInputStream (1byte) / ObjectOutputStream (1byte)
	 */
	
	public static void main(String[] args) {
		
		ObjectDao od = new ObjectDao();
		
		// od.fileSave();
		// od.fileRead();
		
		ObjectsDao ods = new ObjectsDao();
		
		// ods.fileSave();
		ods.fileRead();
		
		// 스캐너로 입력받은 파일명을 매개변수로 넘겨서 저장하는 방식
		// 스캐너로 입력받은 파일명 출력해주는 방식
	}

}
