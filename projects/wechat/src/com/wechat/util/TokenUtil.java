package com.wechat.util;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.wechat.http.Param;
import com.wechat.http.WechatHttpClient;
import com.wechat.servlet.SHA;

public class TokenUtil {
	
	public static void main(String[] args) {
		getToken();
	}

	public static String getToken() {
		WechatHttpClient w = new WechatHttpClient();
		String token = "";
		StringBuffer result = w.get("https://api.weixin.qq.com/cgi-bin/token", new Param[] {
				new Param("grant_type", "client_credential"), new Param("appid", "wx2488fcda19ff3c71"),
				new Param("secret", "181562233a30754ad227298ccf9cc7b7") });
		try {
			System.out.println(result.toString());
			JSONObject obj = JSONObject.parseObject(result.toString());
			token = obj.get("access_token").toString();//token
			System.out.println(obj.get("expires_in"));//生效时间
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
	/**
	 * 根据tooken获取ticket
	 * @param token
	 */
	public static String getTicket(String token) {
		WechatHttpClient w = new WechatHttpClient();
		StringBuffer result = w.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket", new Param[] {
				new Param("access_token", token), new Param("type", "jsapi")});
		try {
			System.out.println(result.toString());
			JSONObject obj = JSONObject.parseObject(result.toString());
			
			String ticket = obj.get("ticket").toString();//token
			System.out.println(obj.get("expires_in"));//生效时间
			return ticket;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 并算出签名
	 */
	private static void getSignature(String token) {
		String ticket = getTicket(token);
		String[] str = {"noncestr=kBVBcqDqzOuD98Lo", "timestamp=1422609033", "jsapi_ticket="+ticket,"url=http://joepeanut.xicp.net:57156/wechatjs/demo.html"};
		Arrays.sort(str); // 字典序排序
		StringBuffer sb = new StringBuffer(str[0]+"&"+str[1]+"&"+str[2]+"&"+str[3]);
		System.out.println(sb);
		System.out.println(SHA.encode(sb.toString()));
	}
}
