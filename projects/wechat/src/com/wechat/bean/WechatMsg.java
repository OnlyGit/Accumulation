package com.wechat.bean;

import org.dom4j.Element;

public class WechatMsg {
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String Content;
	private String Encrypt;
	private String Event;
	
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEncrypt() {
		return Encrypt;
	}
	public void setEncrypt(String encrypt) {
		Encrypt = encrypt;
	}
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
	/**
	 * 在生成xml格式时，如果类中有聚合的其他类，会通过此方法实现
	 * (MusicMsg 中有例子)
	 * @param parent
	 */
	public void getAggregationItem(Element parent) {
		
	}
}
