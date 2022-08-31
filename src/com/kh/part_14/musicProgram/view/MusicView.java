package com.kh.part_14.musicProgram.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.part_14.musicProgram.controller.MusicController;
import com.kh.part_14.musicProgram.model.vo.Music;

public class MusicView {
	
	private Scanner sc = new Scanner(System.in);
	private MusicController mc = new MusicController();
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("=== 쥑쥑이 뮤쥑 ===");
			System.out.println("* 1. 로그인          *");
			System.out.println("* 2. 회원가입       *");
			System.out.println("* 0. 프로그램 종료 *");
			System.out.println("================");
			System.out.print("* 메뉴 번호 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				loginMenu();
				break;
			case 2:
				signupMenu();
				break;
			case 0:
				System.out.println("쥑쥑이 뮤쥑을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 메뉴 번호입니다.");
				break;
			}
			
			System.out.println();
		}
		
	}

	public void loginMenu() {
		
		System.out.print("* 아이디 입력 : ");
		String userId = sc.nextLine();
		System.out.print("* 비밀번호 입력 : ");
		String userPwd = sc.nextLine();
		System.out.println("================");
		
		int result = mc.loginMenu(userId, userPwd);
		
		if (result > 0) {
			System.out.println(userId + "님 로그인 성공");
			System.out.println("메인 화면으로 이동합니다.\n");
			selectMenu();
		} else {
			System.out.println("존재하는 아이디가 없습니다.");
		}
	}
	
	public void signupMenu() {
		
		System.out.println("* 쥑쥑이 회원가입중");
		System.out.print("* 아이디 입력 : ");
		String userId = sc.nextLine();
		System.out.print("* 비밀번호 입력 : ");
		String userPwd = sc.nextLine();
		
		mc.signupMenu(userId, userPwd);
		
		System.out.println("* * * * * * * *");
		System.out.println("쥑쥑이 가족이 된 것을 축하합니다.");
		System.out.println("로그인 화면으로 이동합니다.");
		System.out.println("* * * * * * * *");
		loginMenu();
	}
	
	public void selectMenu() {
		
		while (true) {
			
			System.out.println("=== 쥑쥑이 뮤쥑 ===");
			System.out.println("* * 어서오세요 * *");
			System.out.println("* 1. 노래 추가하기");
			System.out.println("* 2. 노래 삭제하기");
			System.out.println("* 3. 노래 변경하기");
			System.out.println("* 4. 노래 목록보기");
			System.out.println("* 0. 프로그램 종료");
			System.out.println("================");
			System.out.print("* 메뉴 번호 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			System.out.println();
			
			switch (menu) {
			case 1:
				insertMusic();
				break;
			case 2:
				deleteMusic();
				break;
			case 3:
				updateMusic();
				break;
			case 4:
				selectAll();
				break;
			case 0:
				System.out.println("쥑쥑이 뮤직을 종료합니다.");
				System.out.println("다음에 또 와주세요 쥑쥑");
				System.exit(0);
			default:
				System.out.println("잘못된 메뉴 번호입니다.");
				break;
			}
			
			System.out.println();
		}
	}
	
	public void insertMusic() {
		
		System.out.println("================");
		System.out.println("* * 노래 추가 * *");
		System.out.print("* 노래 제목 입력 : ");
		String title = sc.nextLine();
		System.out.print("* 가수 이름 입력 : ");
		String singer = sc.nextLine();
		System.out.print("* 노래 장르 입력 : ");
		String genre = sc.nextLine();
		
		int result = mc.insertMusic(title, singer, genre);
		
		if (result > 0) {
			System.out.println("* 노래 추가 성공!");
		} else {
			System.out.println("* 노래 추가 실패..");
		}
	}

	public void deleteMusic() {
		
		System.out.println("================");
		System.out.println("* * 노래 삭제 * *");
		System.out.print("* 노래 제목 입력 : ");
		String title = sc.nextLine();
		System.out.print("* 가수 이름 입력 : ");
		String singer = sc.nextLine();
		
		int result = mc.deleteMusic(title, singer);
		
		if (result > 0) {
			System.out.println("* 노래 삭제 성공!");
		} else {
			System.out.println("* 노래 삭제 실패..");
		}
	}
	
	public void updateMusic() {
		
		System.out.println("================");
		System.out.println("* * 노래 추가 * *");
		System.out.print("* 바꿀 노래 제목 : ");
		String title = sc.nextLine();
		System.out.print("* 바꿀 노래 가수 : ");
		String singer = sc.nextLine();
		System.out.print("* 노래 제목 입력 : ");
		String uptitle = sc.nextLine();
		System.out.print("* 가수 이름 입력 : ");
		String upsinger = sc.nextLine();
		System.out.print("* 노래 장르 입력 : ");
		String upgenre = sc.nextLine();
		
		int result = mc.updateMusic(title, singer, uptitle, upsinger, upgenre);
		
		if (result > 0) {
			System.out.println("* 노래 변경 성공!");
		} else {
			System.out.println("* 노래 변경 실패..");
		}
	}
	
	public void selectAll() {
		
		System.out.println("================");
		System.out.println("* * 목록 보기 * *");
		
		ArrayList<Music> list = mc.selectAll();
		
		if (list.isEmpty()) {
			System.out.println("목록이 비어있다쥑");
		} else {
			for (Music m : list) {
				System.out.println(m);
			}
		}
		
	}
}
