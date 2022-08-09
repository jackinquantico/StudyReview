package com.kh.part_09.polyPractice.run;

import com.kh.part_09.polyPractice.controller.ElectronicController;
import com.kh.part_09.polyPractice.model.vo.Desktop;
import com.kh.part_09.polyPractice.model.vo.Electronic;
import com.kh.part_09.polyPractice.model.vo.NoteBook;
import com.kh.part_09.polyPractice.model.vo.Tablet;

public class ElectronicRun {

	public static void main(String[] args) {
		
		// 컨트롤러 객체 생성
		ElectronicController ec = new ElectronicController();
		
		// 참조변수 ec 통해서 배열 인덱스마다 객체 생성하기
		ec.insert(new Desktop("a", "b", 1, "c"), 0);
		ec.insert(new NoteBook("ㄱ", "ㄴ", 1, 2), 1);
		ec.insert(new Tablet("1", "2", 1, false), 2);
		
		// 출력
		Desktop d = (Desktop)(ec.select(0)); // 다운캐스팅
		System.out.println(d);
		
		// 출력2
		System.out.println(ec.select(1));
		
		// 반복문으로 물건 꺼내기 1
		Electronic[] elec = ec.select();
		// select() 는 Electronic 객체 배열을 반환하므로
		// 같은 타입의 Electronic 객체 배열로 받아줄 수 있음.
		
		for (int i=0; i<elec.length; i++) {
			System.out.println(elec[i]);
		}
		
		// 반복문으로 물건 꺼내기 2
		for (int i=0; i<3; i++) {
			System.out.println(ec.select()[i]);
		}

	}

}
