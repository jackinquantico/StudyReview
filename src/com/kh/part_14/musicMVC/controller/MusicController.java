package com.kh.part_14.musicMVC.controller;

import java.util.ArrayList;

import com.kh.part_14.musicMVC.model.vo.Music;

public class MusicController {
	
	ArrayList<Music> list = new ArrayList<Music>();
	
	public int insertMusic(String title, String artist) {
		
		int before = list.size();
		
		list.add(new Music(title, artist));
		
		return list.size() - before;
	}
	
	public ArrayList<Music> selectMusicList() {
		
		return list;
	}
	
	public ArrayList<Music> searchMusic(String keyword) {
		
		ArrayList<Music> searched = new ArrayList<Music>();
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getTitle().contains(keyword)) {
				searched.add(list.get(i));
			}
		}
		
		return searched; 
	}
	
	public int deleteMusic(String title) {
		
		int result = 0;
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getTitle().equals(title)) {
				list.remove(i--);
				result++;
			}
		}
		
		return result;
	}
	
	public int updateMusic(String title, String upTitle, String upArtist) {
		
		int result = 0;
		
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getTitle().equals(title)) {
				list.get(i).setTitle(upTitle);
				list.get(i).setArtist(upArtist);
				result++;
			}
		}
		
		return result;
	}
	
}
