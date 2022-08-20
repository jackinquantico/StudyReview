package com.kh.part_14.bookManagement.controller;

import java.util.ArrayList;

import com.kh.part_14.bookManagement.model.vo.Book;

// controller
public class BookManager {
	
	private ArrayList<Book> bookList = new ArrayList<>();

	public BookManager() {
		
	}
	
	public int insertBook(Book book) {
		
		// ����Ʈ�� ������ ��ȣ ���� ����
		int lastNo = 0;
		int result = 0;
		
		try {
			// ���� ������ ���� ��ȣ + 1
			lastNo = bookList.get(bookList.size()-1).getbNo() + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			lastNo = 1;
		} finally {
			result++;
		}
		
		// ������ȣ ����
		book.setbNo(lastNo);
		bookList.add(book);
		
		return result;
	}
	
	public int deleteBook(int bNo) {
		
		int result = 0;
		
		for (int i=0; i<bookList.size(); i++) {
			if (bookList.get(i).getbNo() == bNo) {
				bookList.remove(i--);
				result++;
			}
		}
		
		return result;
	}
	
	public ArrayList<Book> searchBook(String title) {
		
		ArrayList<Book> searchList = new ArrayList<>();
		
		for (int i=0; i<bookList.size(); i++) {
			if (bookList.get(i).getTitle().contains(title)) {
				searchList.add(bookList.get(i));
			}
		}
		
		return searchList;
	}
	
	public ArrayList<Book> selectList() {
		
		return bookList;
	}
}
