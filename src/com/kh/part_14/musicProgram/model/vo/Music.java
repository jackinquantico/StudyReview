package com.kh.part_14.musicProgram.model.vo;

public class Music {
	
	private String title;
	private String singer;
	private String genre;
	
	public Music() {
		
	}
	
	public Music(String title, String singer, String genre) {
		this.title = title;
		this.singer = singer;
		this.genre = genre;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setSinger(String singer) {
		this.singer = singer;
	}
	
	public String getSinger() {
		return singer;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getGenre() {
		return genre;
	}
	
	@Override
	public String toString() {
		return "Music title : "+title+", singer : "+singer+", genre : "+genre;
	}

}
