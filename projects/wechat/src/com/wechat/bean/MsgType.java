package com.wechat.bean;

public enum MsgType {

	TEXT("text"),
	MUSIC("music");
	
	private String type;

	MsgType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
