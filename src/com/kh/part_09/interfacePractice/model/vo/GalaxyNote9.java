package com.kh.part_09.interfacePractice.model.vo;

public class GalaxyNote9 extends SmartPhone {

	public GalaxyNote9() {
		
	}


	@Override
	public void picture() {
		System.out.println("1300만 듀얼카메라");
	}
	
	@Override
	public void charge() {
		super.charge();
		System.out.println(", 고속 무선 충전");		
	}

	@Override
	public void touch() {
		super.touch();
		System.out.println(", 와콤펜 지원");		
	}

	@Override
	public void printMaker() {
		System.out.println("갤럭시 노트 9은 삼성 에서 만들어 졌다.");
	}

	
}
