package com.kh.part_14.depositSystem.model.vo;

public class Account {

	private String owner;
	private String accountNum;
	private int money;
	
	public Account() {
		
	}
	
	public Account(String owner, String accountNum) {
		this.owner = owner;
		this.accountNum = accountNum;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
