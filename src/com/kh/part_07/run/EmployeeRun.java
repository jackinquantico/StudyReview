package com.kh.part_07.run;

import com.kh.part_07.model.vo.Employee;

public class EmployeeRun {

	public static void main(String[] args) {
		
		// 크기가 3인 객체 배열
		Employee[] e = new Employee[3];
		
		// 0번은 기본 생성자, 1번은 매개변수6개 생성자, 2번은 매개변수 10개 생성자
		e[0] = new Employee();
		e[1] = new Employee(1, "홍길동", 19, 'M', "01022223333", "서울 잠실");
		e[2] = new Employee(2, "강말순", "교육부", "강사", 20, 'F', 1000000, 0.01, "01011112222","서울 마곡");
		
		// 출력
		for (int i=0; i<e.length; i++) {
			System.out.println(e[i].information());
		}
		
		System.out.println("===========================================================");
		
		// 값이 없는 필드에 값 넣어서 다시 출력
		e[0].setEmpNo(0);
		e[0].setEmpName("김말똥");
		e[0].setDept("영업부");
		e[0].setJob("팀장");
		e[0].setAge(30);
		e[0].setGender('M');
		e[0].setSalary(3000000);
		e[0].setBonusPoint(0.2);
		e[0].setPhone("01055559999");
		e[0].setAddress("전라도 광주");
		
		e[1].setDept("기획부");
		e[1].setJob("부장");
		e[1].setSalary(4000000);
		e[1].setBonusPoint(0.3);
		
		System.out.println(e[0].information());
		System.out.println(e[1].information());
		
		// 보너스가 적용된 1년 연봉 계산해서 출력
		// 보너스 연봉 = (급여 + (급여 * 보너스포인트)) * 12
		// 평균 연봉값 구하기 위해 sum 변수 설정
		
		int sum = 0;
		
		for (int i=0; i<e.length; i++) {
			int year = (int)(e[i].getSalary() + (e[i].getSalary() * e[i].getBonusPoint())) * 12;
			System.out.println(e[i].getEmpName()+"의 연봉 : "+year+"원");
			sum += year;
		}
		
		System.out.println("===========================================================");
		
		System.out.println("직원들의 연봉의 평균 : "+(sum/e.length)+"원");
		
	}

}
