package com.kh.part_14.movieReservation.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.movieReservation.controller.MovieController;
import com.kh.part_14.movieReservation.model.vo.Movie;

public class MovieView {

	Scanner sc = new Scanner(System.in);
	MovieController mc = new MovieController();
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("==== ## ��ȭ�� ====");
			System.out.println("1. ��ȭ ��� ����");
			System.out.println("2. ��ȭ �����ϱ�");
			System.out.println("3. ��ȭ ����ϱ�");
			System.out.println("0. �����ϱ�");
			System.out.println("=================");
			System.out.print("�޴� ��ȣ �Է� : ");
			int menu = sc.nextInt();
			
			switch (menu) {
			case 1:
				showMovieList();
				break;
			case 2:
				selectMovie();
				break;
			case 3:
				cancelMovie();
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
	
	public void showMovieList() {
		
		System.out.println("==== ��ȭ ��� ====");
		
		// showMovieList() ȣ��
		// ���ϰ����� list
		ArrayList<Movie> list = mc.showMovieList();
				
		// if ���ǽ����� �˻� �� ���
		if (list.isEmpty()) {
			System.out.println("�� ���� ��ȭ�� �����ϴ�.");
		} else {
			for (Movie m : list) {
				System.out.println(m);
			}
		}
	}
	
	public void selectMovie() {
		
	}
	
	public void cancelMovie() {
		
	}
	
}
