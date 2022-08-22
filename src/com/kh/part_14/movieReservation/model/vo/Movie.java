package com.kh.part_14.movieReservation.model.vo;

public class Movie {
	
	private String title;
	private String director;
	private String genre;
	private int runtime;

	public Movie() {
		
	}
	
	public Movie(String title, String director, String genre, int runtime) {
		this.title = title;
		this.director = director;
		this.genre = genre;
		this.runtime = runtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	@Override
	public String toString() {
		return "[영화 제목 : " + title + ", 감독 : " + director + ", 장르 : " + genre + ", 상영 시간 : " + runtime + " 분]";
	}
	
	
	
}
