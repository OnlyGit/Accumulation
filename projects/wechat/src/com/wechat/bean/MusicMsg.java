package com.wechat.bean;

import org.dom4j.Element;

import com.wechat.servlet.XmlUtil;

public class MusicMsg extends WechatMsg {

	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

	@Override
	public void getAggregationItem(Element parent) {
		try {
			XmlUtil.getElementByBean(parent, this.getMusic());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
