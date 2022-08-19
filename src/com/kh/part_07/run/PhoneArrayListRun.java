package com.kh.part_07.run;

import java.util.ArrayList;

import com.kh.part_07.model.vo.Phone;


public class PhoneArrayListRun {

	public static void main(String[] args) {
		
		// ArrayList버전
		ArrayList<Phone> list = new ArrayList<>(3);
		
		list.add(new Phone());
		list.add(new Phone("갤럭시s", "10", "삼성", 1000000));
		list.add(new Phone("아이폰", "11프로", "애플", 800000));
		
		// 향상된 for문으로 출력
		for (Phone p : list) {
			System.out.println(p.information());
		}

	}

}
