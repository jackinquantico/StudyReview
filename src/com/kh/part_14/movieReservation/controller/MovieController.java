package com.kh.part_14.movieReservation.controller;

import java.util.ArrayList;

import com.kh.part_14.movieReservation.model.vo.Movie;
import com.kh.part_14.movieReservation.model.vo.MovieSeat;
import com.kh.part_14.movieReservation.model.vo.Theater;

public class MovieController {

	ArrayList<Movie> list = new ArrayList<Movie>();
	{
		list.add(new Movie("�ư���", "������", "�θǽ�", 180));
		list.add(new Movie("�����", "����ȣ", "����", 183));
		list.add(new Movie("������ �߾�", "����ȣ", "�߸�", 134));
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
	
	private MovieSeat ms = new MovieSeat();
	
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
	
//	public boolean selectSeat(char row, char col) {
//
//		if (tmpCol == col && tmpRow == row) {
//			System.out.println("�̹� ����� �¼��Դϴ�.");
//			return false;
//		}
//		
//		tmpCol = col;
//		tmpRow = row;
//		seatNoCol[row - 'A'][col - '1'] = 'X';
//		
//		return true;
//	}
	
	public int mapSeat(char tmpRow, char tmpCol) {
		
		int result = 0;
		
		if (ms.getTmpRow()==tmpRow && ms.getTmpCol() == tmpCol) {
			return result;
			
		} else {
			ms.getTmpSeat().put(tmpRow, tmpCol);
			ms.setTmpCol(tmpCol);
			ms.setTmpRow(tmpRow);
			
			seatNoCol[tmpRow - 'A'][tmpCol - '1'] = 'X';
			result++;		
			
		}
				
		return result;
	}
	
//	public boolean cancelMovie(String title, char row, char col) {
//		
//		if (!(tmpTitle.equals(title))) {
//			return false;
//		}
//		if (tmpRow != row || tmpCol != col) {
//			return false;
//		}
//		
//		seatNoCol[row - 'A'][col - '1'] = col;
//		
//		return true;
//	}
	
	public int cancelSeat(String title, char row, char col) {
		
		int result = 0;
		
		if (!(tmpTitle.equals(title))) {
			return result;
		}
		if (ms.getTmpCol() != col || ms.getTmpRow() != row) {
			return result;
		} else {
		ms.getTmpSeat().remove(row);
		seatNoCol[row - 'A'][col - '1'] = col;
		result++;
		}
		
		return result;
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












