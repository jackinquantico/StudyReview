package com.kh.part_05.array.practice;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JMenu;

public class C_ArrayPractice {

	public void practice1() {
		
		int[] arr = new int[10];
		
		for (int i=0; i<arr.length; i++) {
			arr[i] = i+1; 
			
			System.out.print(arr[i] + " ");
		}
	}
	
	public void practice2() {
		
		int[] arr = new int[10];
		
		for(int i=arr.length-1; i>=0; i--) {
			arr[i] = i+1;
			
			System.out.print(arr[i]+" ");
		}
	}
	
	public void practice3() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		for (int i=0; i<arr.length; i++) {
			arr[i] = i+1;
			
			System.out.print(arr[i]+" ");
		}
	}

	public void practice4() {
		String[] arr = {"���", "��", "����", "������", "����"};
		
		System.out.println(arr[1]);
	}
	
	public void practice5() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("���ڿ� : ");
		String str = sc.nextLine();
		
		System.out.print("���� : ");
		char is = sc.nextLine().charAt(0);
		
		char[] ch = new char[str.length()];
		
		for (int i=0; i<str.length(); i++) {
			ch[i] = str.charAt(i);
		}
		
		int[] result = new int[ch.length];
		int many = 0;
		
		for (int i=0; i<ch.length; i++) {
			if (ch[i] == is) {
				result[i] = i;
				many++;
			}
		}
		
		System.out.print(str+"�� "+is+"�� �����ϴ� ��ġ(�ε���) : ");
		for (int i=0; i<result.length; i++) {
			if (result[i] != 0) {
				System.out.print(result[i] + " ");
			}
		}
		
		System.out.println("\n"+is+" ���� : "+many);
	}

	public void practice6() {
	
		String[] day = {"��", "ȭ", "��", "��", "��", "��", "��"};
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("0~6 ���� ���� �Է� : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		if (!(0<=num && num<day.length)) {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		} else {
			System.out.println(day[num]+"����");
		}
		
	}
	
	public void practice7() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("���� : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		int[] data = new int[num];
		
		for (int i=0; i<num; i++)  {
			System.out.print("�迭 "+i+"��° �ε����� ���� �� : ");
			data[i] = sc.nextInt();
		}
		
		System.out.println();
		
		for (int i=0; i<num; i++) {
			System.out.print(data[i]+" ");
		}
		
		System.out.println();
		
		int sum = 0;
		for (int i=0; i<num; i++) {
			sum += data[i];
		}
		
		System.out.println("�� �� : " + sum);
	}
	
	public void practice8() {
		
		Scanner sc = new Scanner(System.in);
		
		int num;
		
		System.out.print("���� : ");
		while(true) {
			num = sc.nextInt();
			sc.nextLine();
			
			if ((num % 2 == 1) && (num > 2)) {
				break;
			} else {
				System.out.println("�ٽ� �Է��ϼ���.");
			}
		}
		
		int length = num/2+1;
		int[] arr = new int[num];
		
		int second = num-length;
		
		for (int i=0; i<num; i++) {
			if (i < length) {
				for (int j=0; j<=i; j++) {
					arr[i] = i+1;
				}
			} else if (i>=length) {
				for (int j=1; j>0; j--) {
					arr[i] = num-i;
				}
			}
		}
		
		for (int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+", ");
		}
				
	}
	
	public void practice9() {
		
		String[] chicken = {"���", "�Ҵ�", "ġ��", "�Ķ��̵�"};
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("ġŲ �̸��� �Է��ϼ��� : ");
		String input = sc.nextLine();
		
		for (int i=0; i<chicken.length; i++) {
			if (input.equals(chicken[i])) {
				System.out.println(input + "ġŲ ��� ����");
				return;
			}
		}
		
		System.out.println(input+"ġŲ�� ���� �޴��Դϴ�.");
		
	}

	public void practice10() {
		
		int[] num = new int[10];
		
		for (int i=0; i<num.length; i++) {
			num[i] = (int)(Math.random()*10) +1;
		}
		
		for (int i=0; i<num.length; i++) {
			System.out.print(num[i]+" ");
		}
		
	}

	public void practice11() {
		int[] num = new int[10];
		
		for (int i=0; i<num.length; i++) {
			num[i]= (int)(Math.random()*10) + 1; 
		}
		
		int max = num[0];
		int min = num[0];
		
		for (int i=1; i<num.length; i++) {
			if (num[i] > max) {
				max = num[i];
			} else if (num[i] < min) {
				min = num[i];
			}
			System.out.print(num[i] + " ");
		}
		
		System.out.println("\n�ִ밪 : " + max);
		System.out.println("�ּҰ� : " + min);
	}
	
	public void practice12() {
		int[] num = new int[10];
		
		for (int i=0; i<num.length; i++) {
			num[i] = (int)(Math.random()*10) + 1; 

			for (int j=0; j<i; j++) {
				if (num[i] == num[j]) {
					i--;
					break;
				}
			}
		}
		
		for (int i=0; i<num.length; i++) {
			System.out.print(num[i] + " ");			
		}
		
	}
	
	public void practice13() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�ֹε�Ϲ�ȣ(-����) : ");
		String id = sc.nextLine();
		char[] result = new char[id.length()];
		
		for (int i=0; i<id.length(); i++) {
			if (i<8) {
				result[i] = id.charAt(i);
			} else {
				result[i] = '*'; 
			}
		}
		
		for (int i=0; i<result.length; i++) {
			System.out.print(result[i]);
		}
		
	}
	
	public void practice14() {
		
		int[] num = new int[6];
		
		for (int i=0; i<num.length; i++) {
			num[i] = (int)(Math.random()*45)+1;
			
			for (int j=0; j<i; j++) {
				if (num[i] == num[j]) {
					i--;
					break;
				}
			}
		}
		
		Arrays.sort(num);
		
		System.out.println(Arrays.toString(num));
	}
	
}













