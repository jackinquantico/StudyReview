package com.kh.part_14.movieReservation.view;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.RowFilter;

import com.kh.part_14.movieReservation.controller.MovieController;
import com.kh.part_14.movieReservation.model.vo.Movie;

public class MovieView {

	private Scanner sc = new Scanner(System.in);
	private MovieController mc = new MovieController();
	
	public void loginMenu() {
			
		while(true) {
			
			System.out.println("==== ## 영화관 ====");
			System.out.println("1. 고객 로그인");
			System.out.println("2. 고객 회원가입");
			System.out.println("3. 관리자 로그인");
			System.out.println("0. 프로그램 종료");
			System.out.println("=================");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				cosLogin();
				break;
			case 2:
				cosSignup();
				break;
			case 3:
				sysLogin();
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
	
	public void cosLogin() {
		
		System.out.println("고객 로그인 창입니다.");
		System.out.print("고객 아이디를 입력하세요 : ");
		String cosId = sc.nextLine();
		System.out.print("고객 비밀번호를 입력하세요 : ");
		String cosPw = sc.nextLine();
		
		int result = mc.cosLogin(cosId, cosPw);
		
		if (result == 3) {
			System.out.println("로그인에 성공했습니다.");
			
			while (true) {
				System.out.print("메인 메뉴로 접속하시겠습니까?(Y/N) : ");
				String answer = sc.nextLine();
				
				if (answer.toUpperCase().charAt(0) == 'Y') {
					System.out.println("메인 메뉴로 접속합니다.\n");
					mainMenu();
				} else if (answer.toUpperCase().charAt(0) == 'N') {
					System.out.println("프로그램을 종료합니다.");
					return;
				} else {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			}
		} else {
			if (result == 2) {
				System.out.println("비밀번호가 일치하지 않습니다.");
			} else if (result == 1) {
				System.out.println("아이디가 일치하지 않습니다.");
			} else {
				System.out.println("해당 아이디는 존재하지 않습니다.");
			}
			
			System.out.println("로그인에 실패했습니다.");
		}
		
	}
	
	public void cosSignup() {
		
		System.out.println("고객 회원가입 창입니다.");
		System.out.print("사용할 아이디를 입력하세요 : ");
		String cosId = sc.nextLine();
		System.out.print("사용할 비밀번호를 입력하세요 : ");
		String cosPw = sc.nextLine();
		
		int result = mc.cosSignup(cosId, cosPw);
		
		if (result > 0) {
			System.out.println("회원가입에 성공했습니다.");
		} else {
			System.out.println("이미 존재하는 아이디입니다.");
			System.out.println("회원가입에 실패했습니다.");
		}
	}
	
	public void sysLogin() {
		
		System.out.println("관리자 로그인 창입니다.");
		
		System.out.print("관리자 아이디를 입력하세요 : ");
		String sysId = sc.nextLine();
		System.out.print("관리자 비밀번호를 입력하세요 : ");
		String sysPw = sc.nextLine();
		
		int result = mc.sysLogin(sysId, sysPw);
			
		if (result > 0) {
			System.out.println("로그인에 성공했습니다.");
			
			while (true) {
				System.out.print("관리자 메뉴로 이동하시겠습니까?(Y/N) : ");
				String answer = sc.nextLine();
				
				if (answer.toUpperCase().charAt(0) == 'Y') {
					System.out.println("관리자 메뉴로 접속합니다.\n");
					sysMenu();
				} else if (answer.toUpperCase().charAt(0) == 'N') {
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				} else {
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			}
			
		} else {
			System.out.println("로그인에 실패했습니다.");
		}
		
	}
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("==== ## 영화관 ====");
			System.out.println("1. 영화 목록 보기");
			System.out.println("2. 영화 예매하기");
			System.out.println("3. 영화 취소하기");
			System.out.println("0. 프로그램 종료");
			System.out.println("=================");
			System.out.print("메뉴 번호 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
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
				System.exit(0);;
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
		ArrayList<Movie> list = mc.showMovieList();
		for (Movie m : list) {
			System.out.println(m);
		}
		
		System.out.print("예매할 영화를 선택해주세요 : ");
		String title = sc.nextLine();
		
		ArrayList<Movie> searched = mc.selectMovie(title);
		
		if (searched.isEmpty()) {
			System.out.println("해당 영화가 존재하지 않습니다.");
		} else {
			for (Movie m : searched) {
				System.out.println(m);
			}
			selectSeat();
		}
		
	}
	
	public void selectSeat() {
		
		System.out.println("\n=== 자리 선택 ===");
		
		System.out.println("=============="); //14
		for (int i=0; i<mc.seatNoRow.length; i++) {
			System.out.print(mc.seatNoRow[i]+":");
			for (int j=0; j<mc.seatNoCol[i].length; j++) {
				System.out.print(" " + mc.seatNoCol[i][j]);
			}
			System.out.println();
		}
		
		System.out.print("좌석 열 입력(A~E) : ");
		char row = sc.nextLine().toUpperCase().charAt(0);
		System.out.print("좌석 행 입력(1~6) : ");
		char col = sc.nextLine().charAt(0);
		
		if (!('A' <= row && row <= 'E')) {
			System.out.println("잘못된 열을 입력했습니다.");
			return;
		}
		if (!('1' <= col && col <= '6')) {
			System.out.println("잘못된 행을 입력했습니다.");
			return;
		}
		
		boolean result = mc.selectSeat(row, col);
		
		if (result == true) {
			System.out.println("예매에 성공했습니다.");
		} else {
			System.out.println("예매에 실패했습니다.");
		}
		
	}
	
	public void cancelMovie() {
		
		System.out.println("=== 영화 취소 ===");
		ArrayList<Movie> list = mc.showMovieList();
		for (Movie m : list) {
			System.out.println(m);
		}
		
		System.out.print("취소할 영화를 입력해주세요 : ");
		String title = sc.nextLine();
		System.out.print("예매 좌석 열 입력(A~E) : ");
		char row = sc.nextLine().toUpperCase().charAt(0);
		System.out.print("예매 좌석 행 입력(1~6) : ");
		char col = sc.nextLine().charAt(0);
		
		boolean result = mc.cancelMovie(title, row, col);
		
		if (result == true) {
			System.out.println("취소에 성공했습니다.");
		} else {
			System.out.println("예약 내역이 존재하지 않습니다.");
		}
	}
	
	public void sysMenu() {
		
		while (true) {
		
			System.out.println("==== ## 영화관 ====");
			System.out.println("==== 관리자 메뉴 ====");
			System.out.println("1. 영화 추가하기");
			System.out.println("2. 영화 삭제하기");
			System.out.println("3. 영화 목록보기");
			System.out.println("0. 프로그램 종료");
			System.out.println("=================");
			System.out.print("메뉴 번호 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				insertMovie();
				break;
			case 2:
				deleteMovie();
				break;
			case 3:
				showMovieList();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
			System.out.println();
		}
	}
	
	public void insertMovie() {
		
		System.out.println("=== 영화 추가 ===");
		System.out.print("추가할 영화 제목 입력 : ");
		String title = sc.nextLine();
		System.out.print("추가할 영화 감독 입력 : ");
		String director = sc.nextLine();
		System.out.print("추가할 영화 장르 입력 : "); 
		String genre = sc.nextLine();
		System.out.print("추가할 영화 상영 시간 : ");
		int runtime = sc.nextInt();
		sc.nextLine();
		
		int result = mc.insertMovie(title, director, genre, runtime);
		
		if (result > 0) {
			System.out.println("추가에 성공했습니다.");
		} else {
			System.out.println("추가에 실패했습니다.");
		}
	}
	
	public void deleteMovie() {
		
		System.out.println("=== 영화 삭제 ===");
		System.out.print("삭제할 영화 제목 입력 : ");
		String title = sc.nextLine();
		System.out.print("삭제할 영화 감독 입력 : ");
		String director = sc.nextLine();
		
		int result = mc.deleteMovie(title, director);
		
		if (result > 0) {
			System.out.println("삭제에 성공했습니다.");
		} else {
			System.out.println("삭제할 영화가 존재하지 않습니다.");
		}
	}
	
}












