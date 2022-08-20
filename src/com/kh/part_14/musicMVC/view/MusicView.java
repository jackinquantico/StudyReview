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
			
			System.out.println("*** Welcome ���� ***");
			System.out.println(" 1. ���ο� �� �߰�");
			System.out.println(" 2. �� ��ü ��ȸ");
			System.out.println(" 3. Ư�� �� �˻�");
			System.out.println(" 4. Ư�� �� ����");
			System.out.println(" 5. Ư�� �� ����");
			System.out.println(" 0. ���α׷� ����");
			System.out.println("-------------------");
			
			System.out.print("�޴� �Է� : ");
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
				System.out.println("���α׷� ����");
				return;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
			
			System.out.println();
		}
		
	} // mainMenu

	public void insertMusic() {
		
		System.out.println("== ���ο� �� �߰� ==");
		
		System.out.print("�� �� �Է� : ");
		String title = sc.nextLine();
		System.out.print("������ �Է� : ");
		String artist = sc.nextLine();
		
		int result = mc.insertMusic(title, artist);
		
		if (result > 0) {
			System.out.println("���������� �߰��߽��ϴ�.");
		} else {
			System.out.println("�� �߰��� �����߽��ϴ�.");
		}
		
	}
	
	public void selectMusicList() {
		
		System.out.println("�� ��ü ��ȸ");
		
		ArrayList<Music> list = mc.selectMusicList();
		
		if (list.isEmpty()) {
			System.out.println("���� �����ϴ� ���� �����ϴ�.");
		} else {
			for (Music m : list) {
				System.out.println(m);
			}
		}
	}
	
	public void searchMusic() {
		
		System.out.println("== Ư�� �� �˻� ==");
		System.out.print("�˻��� ��� Ű���� : ");
		String keyword = sc.nextLine();
		
		ArrayList<Music> searched = mc.searchMusic(keyword);
		
		if (searched.isEmpty()) {
			System.out.println("�����ϴ� ���� �����ϴ�.");
		} else {
			for (Music m : searched) {
				System.out.println(m);
			}
		}
		
	}
	
	public void deleteMusic() {
		
		System.out.println("== Ư�� �� ���� ==");
		System.out.print("������ ��� �Է� : ");
		String title = sc.nextLine();
		
		int result = mc.deleteMusic(title);
		
		if (result > 0) {
			System.out.println("������ �����߽��ϴ�.");
		} else {
			System.out.println("������ ���� �������� �ʽ��ϴ�.");
		}
		
	}
	
	public void updateMusic() {
		
		System.out.println("== Ư�� �� ���� ==");
		System.out.print("������ ��� �Է� : ");
		String title = sc.nextLine();
		System.out.print("���� ����(���) : ");
		String upTitle = sc.nextLine();
		System.out.print("���� ����(������) : ");
		String upArtist = sc.nextLine();
		
		int result = mc.updateMusic(title, upTitle, upArtist);
		
		if (result > 0) {
			System.out.println("�� ������ �����߽��ϴ�.");
		} else {
			System.out.println("������ ���� �������� �ʽ��ϴ�.");
		}
		
	}
	
}
