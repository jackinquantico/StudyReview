package com.kh.part_10.hw;

import java.util.Scanner;

public class NumberOk {
	
	private int ran;
	
	public NumberOk() {
		
	}
	
	public void numGame() {
		
		ran = (int)(Math.random() * 100) + 1;
		int count = 0;
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("1���� 100 ������ ������ �ϳ� �Է��ϼ��� : ");
			int input = sc.nextInt();
			sc.nextLine();
			
			if (input < ran) {
				count++;
				System.out.println(input + "���� Ů�ϴ�. "+ count + "��° ����!!");
			} else if (input > ran) {
				count++;
				System.out.println(input + "���� �۽��ϴ�."+ count+ "��° ����!!");
			} else {
				System.out.println(count + "�� ���� ������ϴ�!");
				
				while (true) {
					System.out.print("��� �Ͻðڽ��ϱ�? (Y/N) : ");
					char answer = sc.nextLine().toUpperCase().charAt(0);
					
					if (answer == 'Y') {
						System.out.println("\n���ο� ������ �����մϴ�!!");
						break;
					} else if (answer == 'N') {
						System.out.println("������ �����մϴ�.");
						return;
					} else {
						System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
					}
				}
			}
		}
		
	}

}
