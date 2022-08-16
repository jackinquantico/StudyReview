package com.kh.part_12.stream.charStream.run;

import com.kh.part_12.stream.charStream.model.dao.FileCharDao;

public class CharRun {
	
	/*
	 * * 문자 기반 스트림
	 * 문자 스트림 : 한 번에 2byte 단위의 데이터를 입출력할 수 있는 통로 (XXXReader / XXXWriter)
	 * 기반 스트림 : 외부 매체와 직접적으로 연결되는 메인 통로
	 * 
	 * => 외부 매체를 지정하고 그 외부 매체와 직접적으로 연결되는 2byte 단위의 통로를 만들겠다.
	 */

	public static void main(String[] args) {
		
		FileCharDao fc = new FileCharDao();
		
		// fc.fileSave();
		
		fc.fileRead();

	}
}
