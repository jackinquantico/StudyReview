package com.kh.part_03;

import java.util.Scanner;

// 조건문 실습문제
public class C_ConditionPractice {

	public void method1() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. 입력");
		System.out.println("2. 수정");
		System.out.println("3. 조회");
		System.out.println("4. 삭제");
		System.out.println("9. 종료");
		
		System.out.print("메뉴 번호를 입력하세요 : ");
		int input = sc.nextInt();
		sc.nextLine();
		String output = "";
		
		switch (input) {
		case 1:
			output = "입력";
			break;
		case 2:
			output = "수정";
			break;
		case 3:
			output = "조회";
			break;
		case 4:
			output = "삭제";
			break;
		case 9:
			System.out.println("프로그램이 종료됩니다.");
			return;
		default:
			System.out.println("없는 메뉴입니다.");
			return;
		}
		
		System.out.println(output + " 메뉴입니다.");
	}
	
	public void method2() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자를 한 개 입력하세요 : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		if (num > 0) {
			if (num%2 == 0) {
				System.out.println("짝수다");
			} else {
				System.out.println("홀수다");
			}
		} else {
			System.out.println("양수만 입력해주세요.");
		}
	}
	
	public void method3() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("국어점수 : ");
		int kor = sc.nextInt();
		sc.nextLine();
		
		System.out.print("수학점수 : ");
		int math = sc.nextInt();
		sc.nextLine();
		
		System.out.print("영어점수 : ");
		int eng = sc.nextInt();
		sc.nextLine();
		
		int sum = kor + math + eng;
		double average = sum / 3.0;
		
		if (kor>=40 && eng>=40 && math>=40 && average>=60) {
			System.out.println("국어 : "+kor);
			System.out.println("수학 : "+math);
			System.out.println("영어 : "+eng);
			System.out.println("합계 : "+sum);
			System.out.println("평균 : "+average);
			System.out.println("축하합니다, 합격입니다!");
		} else {
			System.out.println("불합격입니다.");
		}
		
	}
	
	public void method4() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1~12 사이의 정수 입력 : ");
		int num = sc.nextInt();
		sc.nextLine();

		String season = "";
		
		switch (num) {
		case 3:
		case 4:
		case 5:
			season = "봄";
			break;
		case 6:
		case 7:
		case 8:
			season = "여름";
			break;
		case 9:
		case 10:
		case 11:
			season = "가을";
			break;
		case 12:
		case 1:
		case 2:
			season = "겨울";
			break;
		default:
			System.out.println(num+"월은 잘못 입력된 달입니다.");
			return;
		}
		
		System.out.println(num+"월은 "+season+"입니다");
		
	}

	public void method5() {
		
		Scanner sc = new Scanner(System.in);
		String id = "scheyng";
		String pwd = "asdfqwer";
		
		System.out.print("아이디 : ");
		String inputId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String inputPwd = sc.nextLine();
		
		if (id.equals(inputId) && pwd.equals(inputPwd)) {
			System.out.println("로그인 성공");
		} else if(id.equals(inputId) && !pwd.equals(inputPwd)) {
			System.out.println("비밀번호가 틀렸습니다.");
		} else if(!id.equals(inputId) && pwd.equals(inputPwd)) {
			System.out.println("아이디가 틀렸습니다.");
		}
		
	}
	
	public void method6() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("권한을 확인하고자 하는 회원 등급 : ");
		String input = sc.nextLine();
		
		switch (input) {
		case "관리자":
			System.out.println("회원관리, 게시글 관리");
		case "회원":
			System.out.println("게시글 작성, 댓글 작성");
		case "비회원":
			System.out.println("게시글 조회");
			break;
		default:
			System.out.println("잘못 입력했습니다.");
		}
	}

	public void method7() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("키(m)를 입력해주세요 : ");
		double height = sc.nextDouble();
		sc.nextLine();
		
		System.out.print("몸무게(kg)을 입력해주세요 : ");
		double weight = sc.nextDouble();
		sc.nextLine();
		
		double bmi = weight / (height * height);
		String type = "";
		
		if (bmi < 18.5) {
			type = "저체중";
		} else if (bmi < 23) {
			type = "정상체중";
		} else if (bmi < 25) {
			type = "과체중";
		} else if (bmi < 30) {
			type = "비만";
		} else {
			type = "고도 비만";
		}
		
		System.out.println("BMI 지수 : "+bmi);
		System.out.println(type);
		
	}

	public void method8() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("피연산자1 입력 : ");
		int num1 = sc.nextInt();
		sc.nextLine();
		
		System.out.print("피연산자2 입력 : ");
		int num2 = sc.nextInt();
		sc.nextLine();
		
		System.out.print("연산자를 입력(+,-,/,*,%) : ");
		char op = sc.nextLine().charAt(0);
		
		int result;
		
		if (num1 <= 0 || num2 <= 0) {
			System.out.println("잘못 입력하셨습니다. 프로그램을 종료합니다.");
			return;
		}
		
		switch (op) {
		case '+':
			result = num1 + num2;
			break;
		case '-':
			result = num1 - num2;
			break;
		case '*':
			result = num1 * num2;
			break;
		case '/':
			result = num1 / num2;
			break;
		case '%':
			result = num1 % num2;
			break;
		default:
			System.out.println("잘못 입력하셨습니다. 프로그램을 종료합니다.");
			return;
		}
		
		
		
		System.out.printf("%d %c %d = %d", num1, op, num2, result);
	}

	public void method9() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("중간 고사 점수 : ");
		int mid = sc.nextInt();
		sc.nextLine();
		
		System.out.print("기말 고사 점수 : ");
		int fin = sc.nextInt();
		sc.nextLine();
		
		System.out.print("과제 점수 : ");
		int hw = sc.nextInt();
		sc.nextLine();
		
		System.out.print("출석 횟수 : ");
		double chul = sc.nextDouble();
		sc.nextLine();
		
		double midScore = mid * 0.2;
		double finScore = fin * 0.3;
		double hwScore = hw * 0.3;
		
		double chulScore = chul / 20 * 100;
		double sum = midScore + finScore + hwScore + chul;
		
		if (sum >= 70 && chulScore >= 70) {
			System.out.println("======결과======");
			System.out.println("중간 고사 점수(20) : "+midScore);
			System.out.println("기말 고사 점수(30) : "+finScore);
			System.out.println("과제 점수 (30) : "+hwScore);
			System.out.println("출석 점수(20) : "+chul);
			System.out.println("총점 : "+sum);
			System.out.println("Pass");
		} else {
			System.out.println("======결과======");
			if (sum<70 && chulScore<70) {
				System.out.print("FAIL [출석 횟수 부족] ("+(int)chul+"/20)\n");
				System.out.print("FAIL [점수 미달] (총점 "+sum+")");
			} else if(chulScore < 70) {
				System.out.print("FAIL [출석 횟수 부족] ("+(int)chul+"/20)");
			} else if(sum<70) {
				System.out.print("FAIL [점수 미달] (총점 "+sum+")");
			}
		}
		
	}

	public void method10() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("실행할 기능을 선택하세요.");
		System.out.println("1. 메뉴 출력");
		System.out.println("2. 짝수/홀수");
		System.out.println("3. 합격/불합격");
		System.out.println("4. 계절");
		System.out.println("5. 로그인");
		System.out.println("6. 권한 확인");
		System.out.println("7. BMI");
		System.out.println("8. 계산기");
		System.out.println("9. Pass/Fail");
		
		System.out.print("선택 : ");
		int num = sc.nextInt();
		
		switch (num) {
		case 1:
			method1();
			break;
		case 2:
			method2();
			break;
		case 3:
			method3();
			break;
		case 4:
			method4();
			break;
		case 5:
			method5();
			break;
		case 6:
			method6();
			break;
		case 7:
			method7();
			break;
		case 8:
			method8();
			break;
		case 9:
			method9();
			break;
		default :
			System.out.println("잘못 입력하셨습니다.");
		}
	}
	
}
