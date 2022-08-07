package com.kh.part_03;

import java.util.Scanner;

// ���ǹ� �ǽ�����
public class C_ConditionPractice {

	public void method1() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. �Է�");
		System.out.println("2. ����");
		System.out.println("3. ��ȸ");
		System.out.println("4. ����");
		System.out.println("9. ����");
		
		System.out.print("�޴� ��ȣ�� �Է��ϼ��� : ");
		int input = sc.nextInt();
		sc.nextLine();
		String output = "";
		
		switch (input) {
		case 1:
			output = "�Է�";
			break;
		case 2:
			output = "����";
			break;
		case 3:
			output = "��ȸ";
			break;
		case 4:
			output = "����";
			break;
		case 9:
			System.out.println("���α׷��� ����˴ϴ�.");
			return;
		default:
			System.out.println("���� �޴��Դϴ�.");
			return;
		}
		
		System.out.println(output + " �޴��Դϴ�.");
	}
	
	public void method2() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("���ڸ� �� �� �Է��ϼ��� : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		if (num > 0) {
			if (num%2 == 0) {
				System.out.println("¦����");
			} else {
				System.out.println("Ȧ����");
			}
		} else {
			System.out.println("����� �Է����ּ���.");
		}
	}
	
	public void method3() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�������� : ");
		int kor = sc.nextInt();
		sc.nextLine();
		
		System.out.print("�������� : ");
		int math = sc.nextInt();
		sc.nextLine();
		
		System.out.print("�������� : ");
		int eng = sc.nextInt();
		sc.nextLine();
		
		int sum = kor + math + eng;
		double average = sum / 3.0;
		
		if (kor>=40 && eng>=40 && math>=40 && average>=60) {
			System.out.println("���� : "+kor);
			System.out.println("���� : "+math);
			System.out.println("���� : "+eng);
			System.out.println("�հ� : "+sum);
			System.out.println("��� : "+average);
			System.out.println("�����մϴ�, �հ��Դϴ�!");
		} else {
			System.out.println("���հ��Դϴ�.");
		}
		
	}
	
	public void method4() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1~12 ������ ���� �Է� : ");
		int num = sc.nextInt();
		sc.nextLine();

		String season = "";
		
		switch (num) {
		case 3:
		case 4:
		case 5:
			season = "��";
			break;
		case 6:
		case 7:
		case 8:
			season = "����";
			break;
		case 9:
		case 10:
		case 11:
			season = "����";
			break;
		case 12:
		case 1:
		case 2:
			season = "�ܿ�";
			break;
		default:
			System.out.println(num+"���� �߸� �Էµ� ���Դϴ�.");
			return;
		}
		
		System.out.println(num+"���� "+season+"�Դϴ�");
		
	}

	public void method5() {
		
		Scanner sc = new Scanner(System.in);
		String id = "scheyng";
		String pwd = "asdfqwer";
		
		System.out.print("���̵� : ");
		String inputId = sc.nextLine();
		
		System.out.print("��й�ȣ : ");
		String inputPwd = sc.nextLine();
		
		if (id.equals(inputId) && pwd.equals(inputPwd)) {
			System.out.println("�α��� ����");
		} else if(id.equals(inputId) && !pwd.equals(inputPwd)) {
			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		} else if(!id.equals(inputId) && pwd.equals(inputPwd)) {
			System.out.println("���̵� Ʋ�Ƚ��ϴ�.");
		}
		
	}
	
	public void method6() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("������ Ȯ���ϰ��� �ϴ� ȸ�� ��� : ");
		String input = sc.nextLine();
		
		switch (input) {
		case "������":
			System.out.println("ȸ������, �Խñ� ����");
		case "ȸ��":
			System.out.println("�Խñ� �ۼ�, ��� �ۼ�");
		case "��ȸ��":
			System.out.println("�Խñ� ��ȸ");
			break;
		default:
			System.out.println("�߸� �Է��߽��ϴ�.");
		}
	}

	public void method7() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Ű(m)�� �Է����ּ��� : ");
		double height = sc.nextDouble();
		sc.nextLine();
		
		System.out.print("������(kg)�� �Է����ּ��� : ");
		double weight = sc.nextDouble();
		sc.nextLine();
		
		double bmi = weight / (height * height);
		String type = "";
		
		if (bmi < 18.5) {
			type = "��ü��";
		} else if (bmi < 23) {
			type = "����ü��";
		} else if (bmi < 25) {
			type = "��ü��";
		} else if (bmi < 30) {
			type = "��";
		} else {
			type = "�� ��";
		}
		
		System.out.println("BMI ���� : "+bmi);
		System.out.println(type);
		
	}

	public void method8() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�ǿ�����1 �Է� : ");
		int num1 = sc.nextInt();
		sc.nextLine();
		
		System.out.print("�ǿ�����2 �Է� : ");
		int num2 = sc.nextInt();
		sc.nextLine();
		
		System.out.print("�����ڸ� �Է�(+,-,/,*,%) : ");
		char op = sc.nextLine().charAt(0);
		
		int result;
		
		if (num1 <= 0 || num2 <= 0) {
			System.out.println("�߸� �Է��ϼ̽��ϴ�. ���α׷��� �����մϴ�.");
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
			System.out.println("�߸� �Է��ϼ̽��ϴ�. ���α׷��� �����մϴ�.");
			return;
		}
		
		
		
		System.out.printf("%d %c %d = %d", num1, op, num2, result);
	}

	public void method9() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�߰� ��� ���� : ");
		int mid = sc.nextInt();
		sc.nextLine();
		
		System.out.print("�⸻ ��� ���� : ");
		int fin = sc.nextInt();
		sc.nextLine();
		
		System.out.print("���� ���� : ");
		int hw = sc.nextInt();
		sc.nextLine();
		
		System.out.print("�⼮ Ƚ�� : ");
		double chul = sc.nextDouble();
		sc.nextLine();
		
		double midScore = mid * 0.2;
		double finScore = fin * 0.3;
		double hwScore = hw * 0.3;
		
		double chulScore = chul / 20 * 100;
		double sum = midScore + finScore + hwScore + chul;
		
		if (sum >= 70 && chulScore >= 70) {
			System.out.println("======���======");
			System.out.println("�߰� ��� ����(20) : "+midScore);
			System.out.println("�⸻ ��� ����(30) : "+finScore);
			System.out.println("���� ���� (30) : "+hwScore);
			System.out.println("�⼮ ����(20) : "+chul);
			System.out.println("���� : "+sum);
			System.out.println("Pass");
		} else {
			System.out.println("======���======");
			if (sum<70 && chulScore<70) {
				System.out.print("FAIL [�⼮ Ƚ�� ����] ("+(int)chul+"/20)\n");
				System.out.print("FAIL [���� �̴�] (���� "+sum+")");
			} else if(chulScore < 70) {
				System.out.print("FAIL [�⼮ Ƚ�� ����] ("+(int)chul+"/20)");
			} else if(sum<70) {
				System.out.print("FAIL [���� �̴�] (���� "+sum+")");
			}
		}
		
	}

	public void method10() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("������ ����� �����ϼ���.");
		System.out.println("1. �޴� ���");
		System.out.println("2. ¦��/Ȧ��");
		System.out.println("3. �հ�/���հ�");
		System.out.println("4. ����");
		System.out.println("5. �α���");
		System.out.println("6. ���� Ȯ��");
		System.out.println("7. BMI");
		System.out.println("8. ����");
		System.out.println("9. Pass/Fail");
		
		System.out.print("���� : ");
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
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		}
	}
	
}
