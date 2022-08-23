package com.kh.part_14.movieReservation.controller;

import java.util.ArrayList;

import com.kh.part_14.movieReservation.model.vo.Movie;

public class MovieController {

	ArrayList<Movie> list = new ArrayList<Movie>();
	{
		list.add(new Movie("�ư���", "������", "�θǽ�", 180));
		list.add(new Movie("�����", "����ȣ", "����", 183));
		list.add(new Movie("������ �߾�", "����ȣ", "�߸�", 134));
	}
	
	public static char[] seatNoRow = {'A','B','C','D','E'};
	public static char[] seatNoCol = {'1','2','3','4','5','6'};
	private char tmpCol;
	private char tmpRow;
	
	public ArrayList<Movie> showMovieList() {
	
		return list;
	}
	
	public ArrayList<Movie> selectMovie(String title) {
		
		ArrayList<Movie> searched = new ArrayList<Movie>();
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getTitle().contains(title)) {
				searched.add(list.get(i));
			}
		}
		
		return searched;
	}
	
	public boolean selectSeat(char row, char col) {
		
		if (!('A' <= row && row <= 'E')) {
			System.out.println("�߸��� ���� �Է��߽��ϴ�.");
			return false;
		}
		if (!('1' <= col && col <= '6')) {
			System.out.println("�߸��� ���� �Է��߽��ϴ�.");
			return false;
		}
		if (tmpCol == col && tmpRow == row) {
			System.out.println("�̹� ����� �¼��Դϴ�.");
			return false;
		}
		
		tmpCol = col;
		tmpRow = row;
		
		return true;
	}
	
}
