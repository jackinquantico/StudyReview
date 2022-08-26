package com.kh.part_14.depositSystem.model.vo;

import java.util.ArrayList;

public class Bank {

	ArrayList<Account> acc = new ArrayList<Account>();
	{
		acc.add(new Account("ȫ�浿", "123123123"));
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
}
