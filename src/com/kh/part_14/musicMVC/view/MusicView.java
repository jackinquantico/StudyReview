package com.kh.part_14.musicMVC.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kh.part_14.musicMVC.controller.MusicController;
import com.kh.part_14.musicMVC.model.vo.Music;

public class MusicView {
	
	MusicController mc = new MusicController();
	Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("*** Welcome 별밤 ***");
			System.out.println(" 1. 새로운 곡 추가");
			System.out.println(" 2. 곡 전체 조회");
			System.out.println(" 3. 특정 곡 검색");
			System.out.println(" 4. 특정 곡 삭제");
			System.out.println(" 5. 특정 곡 수정");
			System.out.println(" 0. 프로그램 종료");
			System.out.println("-------------------");
			
			System.out.print("메뉴 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				insertMusic();
				break;
			case 2:
				selectMusicList();
				break;
			case 3:
				searchMusic();
				break;
			case 4:
				deleteMusic();
				break;
			case 5:
				updateMusic();
				break;
			case 0:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
			System.out.println();
		}
		
	} // mainMenu

	public void insertMusic() {
		
		System.out.println("== 새로운 곡 추가 ==");
		
		System.out.print("곡 명 입력 : ");
		String title = sc.nextLine();
		System.out.print("가수명 입력 : ");
		String artist = sc.nextLine();
		
		int result = mc.insertMusic(title, artist);
		
		if (result > 0) {
			System.out.println("성공적으로 추가했습니다.");
		} else {
			System.out.println("곡 추가에 실패했습니다.");
		}
		
	}
	
	public void selectMusicList() {
		
		System.out.println("곡 전체 조회");
		
		ArrayList<Music> list = mc.selectMusicList();
		
		if (list.isEmpty()) {
			System.out.println("현재 존재하는 곡이 없습니다.");
		} else {
			for (Music m : list) {
				System.out.println(m);
			}
		}
	}
	
	public void searchMusic() {
		
		System.out.println("== 특정 곡 검색 ==");
		System.out.print("검색할 곡명 키워드 : ");
		String keyword = sc.nextLine();
		
		ArrayList<Music> searched = mc.searchMusic(keyword);
		
		if (searched.isEmpty()) {
			System.out.println("존재하는 곡이 없습니다.");
		} else {
			for (Music m : searched) {
				System.out.println(m);
			}
		}
		
	}
	
	public void deleteMusic() {
		
		System.out.println("== 특정 곡 삭제 ==");
		System.out.print("삭제할 곡명 입력 : ");
		String title = sc.nextLine();
		
		int result = mc.deleteMusic(title);
		
		if (result > 0) {
			System.out.println("삭제에 성공했습니다.");
		} else {
			System.out.println("삭제할 곡이 존재하지 않습니다.");
		}
		
	}
	
	public void updateMusic() {
		
		System.out.println("== 특정 곡 수정 ==");
		System.out.print("수정할 곡명 입력 : ");
		String title = sc.nextLine();
		System.out.print("수정 내용(곡명) : ");
		String upTitle = sc.nextLine();
		System.out.print("수정 내용(가수명) : ");
		String upArtist = sc.nextLine();
		
		int result = mc.updateMusic(title, upTitle, upArtist);
		
		if (result > 0) {
			System.out.println("곡 수정에 성공했습니다.");
		} else {
			System.out.println("수정할 곡이 존재하지 않습니다.");
		}
		
	}
	
}
