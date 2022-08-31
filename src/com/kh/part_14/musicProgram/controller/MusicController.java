package com.kh.part_14.musicProgram.controller;

import java.util.ArrayList;

import com.kh.part_14.musicProgram.model.vo.Music;

public class MusicController {
	
	private ArrayList<Music> list = new ArrayList<Music>();
	private String userId = "asdf";
	private String userPwd = "asdf";
	
	public int loginMenu(String userId, String userPwd) {
		
		int result = 0;
		
		if (this.userId.equals(userId) && this.userPwd.equals(userPwd)) {
			result++;
		}
		
		return result;
	}
	
	public void signupMenu(String userId, String userPwd) {
		
		this.userId = userId;
		this.userPwd = userPwd;
	}

	public int insertMusic(String title, String singer, String genre) {
		
		int result = 0;
		
		list.add(new Music(title, singer, genre));
		result++;
		
		return result;
	}
	
	public int deleteMusic(String title, String singer) {
		
		int result = 0;
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getTitle().equals(title) && list.get(i).getSinger().equals(singer)) {
				list.remove(i);
				result++;
			}
		}
		
		return result;
	}
	
	public int updateMusic(String title, String singer, String uptitle, String upsinger, String upgenre) {
		
		int result = 0;
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getTitle().equals(title) && list.get(i).getSinger().equals(singer)) {
				list.get(i).setTitle(uptitle);
				list.get(i).setSinger(upsinger);
				list.get(i).setGenre(upgenre);
			}
		}
		
		return result;
	}
	
	public ArrayList<Music> selectAll() {
		
		return list;
	}
}
