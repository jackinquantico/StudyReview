package com.kh.part_14.bookManagement.model.vo;

// mode.vo
public class Book {

	// �ʵ��
	private int bNo;
	private String title;
	private int category;
	private String author;
	
	// �����ں�
	public Book() {
		
	}
	
	public Book(String title, int category, String author) {
		this.title = title;
		this.category = category;
		this.author = author;
	}

	// �޼ҵ��
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
		
		String category = "";
		switch (this.category) {
		case 1:
			category = "�ι�";
			break;
		case 2:
			category = "�ڿ�����";
			break;
		case 3 :
			category = "�Ƿ�";
		case 4 :
			category = "��Ÿ";
			break;
		}
		
		return "[Book No : "+bNo+", title : "+title+", author : "+author+", category : " + category+ "]";
	}
	
}
