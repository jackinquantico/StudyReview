package com.kh.part_14.movieReservation.model.vo;

public class Theater {
	
	// ±ØÀå Á÷¿ø id, pw
	private String sysId = "sysid";
	private String sysPw = "syspw";
	
	// ¼Õ´Ô id, pw
	private String cosId = "";
	private String cosPw = "";
	
	public Theater() {
		
	}
	
	public Theater (String cosId, String cosPw) {
		this.cosId = cosId;
		this.cosPw = cosPw;
	}

	public String getSysId() {
		return sysId;
	}

	public String getSysPw() {
		return sysPw;
	}

	public String getCosId() {
		return cosId;
	}

	public void setCosId(String cosId) {
		this.cosId = cosId;
	}

	public String getCosPw() {
		return cosPw;
	}

	public void setCosPw(String cosPw) {
		this.cosPw = cosPw;
	}

}
