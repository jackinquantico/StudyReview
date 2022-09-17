package com.kh.part_15.jdbc.properties.model.vo;

import java.sql.Date;

public class Board {

	private int bNo;
	private String title;
	private String content;
	private Date createDate;
	private String writer;
	private String deleteYN;
	
	public Board() {
		
	}
	
	public Board(int bNo, String title, String content, Date createDate, String writer) {
		super();
		this.bNo = bNo;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.writer = writer;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}

	@Override
	public String toString() {
		return "글번호 : " + bNo + " | 글 제목 : " + title + " | 작성일자 : " + createDate + " | 작성자 : " + writer;
	}
	
	
}
