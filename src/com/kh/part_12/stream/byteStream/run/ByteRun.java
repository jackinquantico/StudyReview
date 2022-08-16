package com.kh.part_12.stream.byteStream.run;

import com.kh.part_12.stream.byteStream.model.dao.FileByteDao;

//바이트 기반 스트림
public class ByteRun {
	
	/*
	 * * 바이트 기반 스트림
	 * 바이트 스트림 : 1byte 단위로만 입출력 할 수 있는 좁은 통로 (XXXInputStream / XXXOutputStream)
	 * 기반 스트림 : 외부 매체와 직접적으로 연결되는 메인 통로
	 * 
	 * => 외부 매체를 지정하고 그 외부매체와 직접적으로 연결되는 1byte 단위의 통로를 만든다.
	 * XXXInputStream : XXX매체로부터 데이터를 입력받는 통로
	 * 					(외부 매체로부터 데이터를 가지고 오겠다. 읽어들이겠다.)
	 * XXXOutputStream : XXX매체로부터 데이터를 출력하는 통로
	 * 					 (외부매체에 데이터를 내보내겠다, 쓰겠다)
	 */

	public static void main(String[] args) {
		
		FileByteDao fb = new FileByteDao();
		
		// fb.fileSave();
		// 파일 경로를 따로 지정하지 않았기 때문에 상대 경로에 의해
		// 지금 작업 중인 12_IO 프로젝트 폴더 내에 생성됨.
		
		fb.fileRead();
	}

}
