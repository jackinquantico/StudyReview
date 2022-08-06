package com.kh.part_06.model.vo;

public class Circle {
	
	public static final double PI = 3.14;
	private int radius = 1;
	
	public Circle() {
		
	}
	
	public void incrementRadius() {
		radius++;
	}
	
	public void getAreaOfCircle() {
		System.out.println(Math.pow(radius, 2)*PI);
	}
	
	public void getSizeOfCircle() {
		System.out.println(radius*2*PI);
	}
}
