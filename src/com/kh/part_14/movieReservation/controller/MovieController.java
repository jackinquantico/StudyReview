package com.kh.part_14.movieReservation.controller;

import java.util.ArrayList;

import com.kh.part_14.movieReservation.model.vo.Movie;
import com.kh.part_14.movieReservation.model.vo.Theater;

public class MovieController {

	ArrayList<Movie> list = new ArrayList<Movie>();
	{
		list.add(new Movie("아가씨", "박찬욱", "로맨스", 180));
		list.add(new Movie("기생충", "봉준호", "스릴", 183));
		list.add(new Movie("살인의 추억", "봉준호", "추리", 134));
	}
	
	Theater th = new Theater();
	
	public static char[] seatNoRow = {'A','B','C','D','E'};
	public static char[][] seatNoCol = {{'1','2','3','4','5','6'},
										{'1','2','3','4','5','6'},
										{'1','2','3','4','5','6'},
										{'1','2','3','4','5','6'},
										{'1','2','3','4','5','6'}};
	private char tmpCol;
	private char tmpRow;
	private String tmpTitle;
	
	public ArrayList<Movie> showMovieList() {
	
		return list;
	}
	
	public ArrayList<Movie> selectMovie(String title) {
		
		ArrayList<Movie> searched = new ArrayList<Movie>();
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getTitle().equals(title)) {
				searched.add(list.get(i));
				tmpTitle = title;
			}
		}
		
		return searched;
	}
	
	public boolean selectSeat(char row, char col) {

		if (tmpCol == col && tmpRow == row) {
			System.out.println("이미 예약된 좌석입니다.");
			return false;
		}
		
		tmpCol = col;
		tmpRow = row;
		seatNoCol[row - 'A'][col - '1'] = 'X';
		
		return true;
	}
	
	public boolean cancelMovie(String title, char row, char col) {
		
		if (!(tmpTitle.equals(title))) {
			return false;
		}
		if (tmpRow != row || tmpCol != col) {
			return false;
		}
		
		seatNoCol[row - 'A'][col - '1'] = col;
		
		return true;
	}

	public int cosLogin(String cosId, String cosPw) {
		
		int result = 0;
		
		if (th.getCosId().equals(cosId)) {
			result += 2;
		}
		if (th.getCosPw().equals(cosPw)) {
			result++;
		}
		
		return result;
	}
	
	public int cosSignup(String cosId, String cosPw) {
		
		int result = 0;
		
		if (th.getCosId().equals(cosId)) {
			return result;
		}
		
		th.setCosId(cosId);
		th.setCosPw(cosPw);
		result++;
		
		return result;
	}
	
	public int sysLogin(String sysId, String sysPw) {
		
		int result = 0;
		
		if (th.getSysId().equals(sysId) && th.getSysPw().equals(sysPw)) {
			result++;
		}
		
		return result;
	}
	
	public int insertMovie(String title, String director, String genre, int runtime) {
		
		int result = 0;
		
		list.add(new Movie(title, director, genre, runtime));
		result++;
		
		return result;
	}
	
	public int deleteMovie(String title, String director) {
		
		int result = 0;
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getTitle().equals(title) && list.get(i).getDirector().equals(director)) {
				list.remove(i);
				result++;
			}
		}
		
		return result;
	}
}












