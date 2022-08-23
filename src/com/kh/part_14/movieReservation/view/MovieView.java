package com.kh.part_14.movieReservation.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.movieReservation.controller.MovieController;
import com.kh.part_14.movieReservation.model.vo.Movie;

public class MovieView {

	private Scanner sc = new Scanner(System.in);
	private MovieController mc = new MovieController();
	
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
		
		System.out.println("=== 영화 예매 ===");
		System.out.print("예매할 영화를 선택해주세요 : ");
		String title = sc.nextLine();
		
		ArrayList<Movie> searched = mc.selectMovie(title);
		
		if (searched.isEmpty()) {
			System.out.println("해당 영화가 존재하지 않습니다.");
		} else {
			selectSeat();
		}
		
	}
	
	public void selectSeat() {
		
		System.out.println("=== 자리 선택 ===");
		
		System.out.println("=============="); //14
		for (int i=0; i<mc.seatNoRow.length; i++) {
			System.out.print(mc.seatNoRow[i]+":");
			for (int j=0; j<mc.seatNoCol.length; j++) {
				System.out.print(" " + mc.seatNoCol[j]);
			}
			System.out.println();
		}
		
		System.out.print("좌석 열 입력(A~E) : ");
		char row = sc.nextLine().toUpperCase().charAt(0);
		System.out.print("좌석 행 입력(1~6) : ");
		char col = sc.nextLine().charAt(0);
		
		boolean result = mc.selectSeat(row, col);
		
		if (result == true) {
			System.out.println("예매에 성공했습니다.");
		} else {
			System.out.println("예매에 실패했습니다.");
		}
		
	}
	
	public void cancelMovie() {
		
	}
	
}












