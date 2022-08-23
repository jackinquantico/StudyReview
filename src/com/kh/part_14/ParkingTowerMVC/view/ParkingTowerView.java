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
			
			System.out.println("=== 메뉴 구성 ===");
			System.out.println(" 1. 차량 주차");
			System.out.println(" 2. 차량 출차");
			System.out.println(" 3. 주차된 차량 검색");
			System.out.println(" 4. 전체 출력");
			System.out.println(" 0. 프로그램 종료");
			System.out.println("==============");
			System.out.print("메뉴 번호 입력 : ");
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
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
			System.out.println();
		}
	}
	
	public void insertCar() {
		
		System.out.println("=== 차량 주차 ===");
		System.out.print("차량 번호 입력 : ");
		int carNum = sc.nextInt();
		sc.nextLine();
		System.out.print("차량 종류 입력 : ");
		int carType = sc.nextInt();
		sc.nextLine();
		System.out.print("차량 주인 입력 : ");
		String owner = sc.nextLine();
		
		int result = ptc.insertCar(carNum, carType, owner);
		
		if (result > 0) {
			System.out.println("주차에 성공하였습니다.");
		} else {
			System.out.println("주차에 실패하였습니다.");
		}
	}
	
	public void deleteCar() {
		
		System.out.println("=== 차량 출차 ===");
		System.out.print("차량 번호 입력 : ");
		int carNum = sc.nextInt();
		sc.nextLine();
		
		int result = ptc.deleteCar(carNum);
		
		if (result > 0) {
			System.out.println("출차에 성공하였습니다.");
		} else {
			System.out.println("출차에 실패하였습니다.");
		}
	}

	public void searchCar() {
		
		System.out.println("=== 차량 검색 ===");
		System.out.print("차량 주인 이름 입력 : ");
		String owner = sc.nextLine();
		
		ArrayList<Car> searched = ptc.searchCar(owner);
		
		if (searched.isEmpty()) {
			System.out.println("검색된 차량이 없습니다.");
			
		} else {
			for (Car c : searched) {
				System.out.println(c);
			}
		}
	}

	public void selectList() {
		
		System.out.println("=== 차량 전체 출력 ===");
		
		ArrayList<Car> carList = ptc.selectList();
		
		if (carList.isEmpty()) {
			System.out.println("현재 등록된 차량이 없습니다.");
		} else {
			for (Car c : carList) {
				System.out.println(c);
			}
		}
		
	}

}
