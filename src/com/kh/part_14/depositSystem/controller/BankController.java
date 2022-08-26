package com.kh.part_14.depositSystem.controller;

import java.util.ArrayList;

import com.kh.part_14.depositSystem.model.vo.Account;

public class BankController {
	
	
	ArrayList<Account> acc = new ArrayList<Account>();
	{
		acc.add(new Account("ȫ�浿", "123123123"));
	}
	
	public int loginAccount(String owner, String accountNum) {
		
		int result = 0;
		
		for (int i=0; i<acc.size(); i++) {
			if (acc.get(i).getOwner().equals(owner) && acc.get(i).getAccountNum().equals(accountNum)) {
				result++;
			}
		}
		
		return result;
	}
	
	public int getAccount(String owner, String accountNum) {
		
		int money = -1;
		
		for (int i=0; i<acc.size(); i++) {
			if (acc.get(i).getOwner().equals(owner) && acc.get(i).getAccountNum().equals(accountNum)) {
				money = acc.get(i).getMoney();				
			}
		}
		
		return money;
	}
	
	public int depositMoney(String owner, String accountNum, int deposit) {
		
		int result = 0;
		
		for (int i=0; i<acc.size(); i++) {
			if (acc.get(i).getOwner().equals(owner) && acc.get(i).getAccountNum().equals(accountNum)) {
				acc.get(i).setMoney((acc.get(i).getMoney() + deposit));
				result++;
			}
		}
		
		return result;
	}
	
	public int showMoney(String owner, String accountNum) {
		
		int money = -1; 
		
		money = getAccount(owner, accountNum);
		
		return money;
	}
	
	public int withdrawMoney(String owner, String accountNum, int withdraw) {
		
		int result = 0;
		
		for (int i=0; i<acc.size(); i++) {
			if (acc.get(i).getOwner().equals(owner) && acc.get(i).getAccountNum().equals(accountNum)) {
				acc.get(i).setMoney(acc.get(i).getMoney() - withdraw);
				result++;
			}
		}
		
		return result;
	}
}
