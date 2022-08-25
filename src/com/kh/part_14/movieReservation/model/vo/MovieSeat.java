package com.kh.part_14.movieReservation.model.vo;

import java.util.HashMap;

public class MovieSeat {

	private char tmpRow;
	private char tmpCol;
	private HashMap<Character, Character> tmpSeat = new HashMap<>();
	
	public MovieSeat() {
		
	}

	public MovieSeat(char tmpRow, char tmpCol) {
		tmpSeat.put(tmpRow, tmpCol);
	}

	public char getTmpRow() {
		return tmpRow;
	}

	public void setTmpRow(char tmpRow) {
		this.tmpRow = tmpRow;
	}

	public char getTmpCol() {
		return tmpCol;
	}

	public void setTmpCol(char tmpCol) {
		this.tmpCol = tmpCol;
	}

	public HashMap<Character, Character> getTmpSeat() {
		return tmpSeat;
	}

	public void setTmpSeat(HashMap<Character, Character> tmpSeat) {
		this.tmpSeat = tmpSeat;
	}
	
}
