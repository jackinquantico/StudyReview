package com.kh.part_14.beforeJDBC.controller;

import java.util.ArrayList;

import com.kh.part_14.beforeJDBC.model.vo.Member;


public class MemberController {

	private ArrayList<Member> memberlist = new ArrayList<Member>();
	
	public int idCheck(String userId) {
		
		int result = 0;
		
		for (int i=0; i<memberlist.size(); i++) {
			if (memberlist.get(i).getUserId().equals(userId)) {
				result++;
			}
		}
		
		return result;
	}
	
	public int insertMember(String userId, String userPwd, String userName, int age, char gender, String email, String phone) {
		
		int insertResult = 0;
		
		int userNo = 0;
				
		try {
			userNo = memberlist.get(memberlist.size() - 1).getUserNo() + 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			userNo = 1;
		}
		
		memberlist.add(new Member(userNo, userId, userPwd, userName, age, gender, email, phone));
		insertResult++;
		
		return insertResult;
	}
	
	public ArrayList<Member> selectMemberList() {
		
		return memberlist;
	}
	
	public Member searchMemberById(String userId) {
		
		Member user = null;
		
		for (int i=0; i<memberlist.size(); i++) {
			if (memberlist.get(i).getUserId().equals(userId)) {
				user = memberlist.get(i);
			}
		}
		
		return user;
	}

	public ArrayList<Member> searchMemberbyName(String keyword) {
		
		ArrayList<Member> searched = new ArrayList<Member>();
		
		for (int i=0; i<memberlist.size(); i++) {
			if (memberlist.get(i).getUserName().contains(keyword)) {
				searched.add(memberlist.get(i));
			}
		}
		
		return searched;
	}
	
	public int initMemberPwd(String userId) {
		
		int result = 0;
		
		for (int i=0; i<memberlist.size(); i++) {
			if (memberlist.get(i).getUserId().equals(userId)) {
				memberlist.get(i).setUserPwd("passwordKH@123");
				result++;
			}
		}
		
		return result;
	}
	
	public int deleteMember(String userId) {
		
		int result = 0;
		
		for (int i=0; i<memberlist.size(); i++) {
			if (memberlist.get(i).getUserId().equals(userId)) {
				memberlist.remove(i);
				result++;
			}
		}
		
		return result;
	}
}
