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
	
	public ArrayList<Movie> showMovieList() {
	
		return list;
	}
	
}
