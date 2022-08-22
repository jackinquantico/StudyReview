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
			
			System.out.println("==== ## 영화관 ====");
			System.out.println("1. 영화 목록 보기");
			System.out.println("2. 영화 예매하기");
			System.out.println("3. 영화 취소하기");
			System.out.println("0. 종료하기");
			System.out.println("=================");
			System.out.print("메뉴 번호 입력 : ");
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
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
			System.out.println();
			
		}
	}
	
	public void showMovieList() {
		
		System.out.println("==== 영화 목록 ====");
		
		// showMovieList() 호출
		// 리턴값으로 list
		ArrayList<Movie> list = mc.showMovieList();
				
		// if 조건식으로 검사 후 출력
		if (list.isEmpty()) {
			System.out.println("상영 중인 영화가 없습니다.");
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
