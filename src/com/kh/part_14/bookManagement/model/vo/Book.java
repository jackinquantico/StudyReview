package com.kh.part_14.bookManagement.model.vo;

// mode.vo
public class Book {

	// 鞘靛何
	private int bNo;
	private String title;
	private int category;
	private String author;
	
	// 积己磊何
	public Book() {
		
	}
	
	public Book(String title, int category, String author) {
		this.title = title;
		this.category = category;
		this.author = author;
	}

	// 皋家靛何
	public int getbNo() {
		return bNo;
	}
	
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getCategory() {
		return category;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "[Book No : "+bNo+", title : "+title+", author : "+author+", category : " + category+ "]";
	}
	
}
