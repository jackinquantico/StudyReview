package com.kh.part_06.run;

import com.kh.part_06.model.vo.Circle;

public class CircleRun {

	public static void main(String[] args) {
		
		// 기본 생성자로 객체 생성
		Circle c = new Circle();
		
		// 원의 둘레, 넓이값 출력
		c.getSizeOfCircle();
		c.getAreaOfCircle();
		
		// 반지름 1 증가 후
		c.incrementRadius();
		
		// 원의 둘레, 넓이값 재출력
		c.getSizeOfCircle();
		c.getAreaOfCircle();

	}

}
