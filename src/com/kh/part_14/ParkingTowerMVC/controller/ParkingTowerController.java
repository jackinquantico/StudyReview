package com.kh.part_14.ParkingTowerMVC.controller;

import java.util.ArrayList;

import com.kh.part_14.ParkingTowerMVC.model.vo.Car;

public class ParkingTowerController {

	private ArrayList<Car> carList = new ArrayList<>();
	
	public int insertCar(int carNum, int carType, String owner) {
		
		int lastNo = 0;
		
		try {
			lastNo = carList.get(carList.size() - 1).getParkingNum() + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			lastNo = 1;
		}
		
		carList.add(new Car(lastNo, carNum, carType, owner));
		
		return lastNo;
	}
	
	public int deleteCar(int carNum) {
		
		int result = 0;
		
		for (int i=0; i<carList.size(); i++) {
			if (carList.get(i).getCarNum() == carNum) {
				carList.remove(i);
				result++;
			}
		}
		
		return result;
	}
	
	public ArrayList<Car> searchCar(String owner) {
		
		ArrayList<Car> searched = new ArrayList<Car>();
		
		for (int i=0; i<carList.size(); i++) {
			if (carList.get(i).getOwner().contains(owner)) {
				searched.add(carList.get(i));
			}
		}
		
		return searched;
	}
	
	public ArrayList<Car> selectList() {
		
		return carList;
	}
	
}
