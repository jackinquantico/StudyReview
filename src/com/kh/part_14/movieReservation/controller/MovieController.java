package com.kh.part_14.movieReservation.controller;

import java.util.ArrayList;

import com.kh.part_14.movieReservation.model.vo.Movie;

public class MovieController {

	ArrayList<Movie> list = new ArrayList<Movie>();
	{
		list.add(new Movie("아가씨", "박찬욱", "로맨스", 180));
		list.add(new Movie("기생충", "봉준호", "스릴", 183));
		list.add(new Movie("살인의 추억", "봉준호", "추리", 134));
	}
	
	public ArrayList<Movie> showMovieList() {
	
		return list;
	}
	
}
