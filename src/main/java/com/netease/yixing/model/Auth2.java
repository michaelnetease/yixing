package com.netease.yixing.model;

public class Auth2 {
	private int id;
	private  String uid;
	private  String rnd;
	private String accessToken;
	private String  validTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getRnd() {
		return rnd;
	}
	public void setRnd(String rnd) {
		this.rnd = rnd;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getValidTime() {
		return validTime;
	}
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	
}
