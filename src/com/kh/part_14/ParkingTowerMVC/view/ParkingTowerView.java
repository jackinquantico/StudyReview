package com.kh.part_14.ParkingTowerMVC.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.ParkingTowerMVC.controller.ParkingTowerController;
import com.kh.part_14.ParkingTowerMVC.model.vo.Car;

public class ParkingTowerView {

	private Scanner sc = new Scanner(System.in);
	private ParkingTowerController ptc = new ParkingTowerController();
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("=== �޴� ���� ===");
			System.out.println(" 1. ���� ����");
			System.out.println(" 2. ���� ����");
			System.out.println(" 3. ������ ���� �˻�");
			System.out.println(" 4. ��ü ���");
			System.out.println(" 0. ���α׷� ����");
			System.out.println("==============");
			System.out.print("�޴� ��ȣ �Է� : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				insertCar();
				break;
			case 2:
				deleteCar();
				break;
			case 3:
				searchCar();
				break;
			case 4:
				selectList();
				break;
			case 0:
				System.out.println("���α׷��� �����մϴ�.");
				return;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
			
			System.out.println();
		}
	}
	
	public void insertCar() {
		
		System.out.println("=== ���� ���� ===");
		System.out.print("���� ��ȣ �Է� : ");
		int carNum = sc.nextInt();
		sc.nextLine();
		System.out.print("���� ���� �Է� : ");
		int carType = sc.nextInt();
		sc.nextLine();
		System.out.print("���� ���� �Է� : ");
		String owner = sc.nextLine();
		
		int result = ptc.insertCar(carNum, carType, owner);
		
		if (result > 0) {
			System.out.println("������ �����Ͽ����ϴ�.");
		} else {
			System.out.println("������ �����Ͽ����ϴ�.");
		}
	}
	
	public void deleteCar() {
		
		System.out.println("=== ���� ���� ===");
		System.out.print("���� ��ȣ �Է� : ");
		int carNum = sc.nextInt();
		sc.nextLine();
		
		int result = ptc.deleteCar(carNum);
		
		if (result > 0) {
			System.out.println("������ �����Ͽ����ϴ�.");
		} else {
			System.out.println("������ �����Ͽ����ϴ�.");
		}
	}

	public void searchCar() {
		
		System.out.println("=== ���� �˻� ===");
		System.out.print("���� ���� �̸� �Է� : ");
		String owner = sc.nextLine();
		
		ArrayList<Car> searched = ptc.searchCar(owner);
		
		if (searched.isEmpty()) {
			System.out.println("�˻��� ������ �����ϴ�.");
			
		} else {
			for (Car c : searched) {
				System.out.println(c);
			}
		}
	}

	public void selectList() {
		
		System.out.println("=== ���� ��ü ��� ===");
		
		ArrayList<Car> carList = ptc.selectList();
		
		if (carList.isEmpty()) {
			System.out.println("���� ��ϵ� ������ �����ϴ�.");
		} else {
			for (Car c : carList) {
				System.out.println(c);
			}
		}
		
	}

}
