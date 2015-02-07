package com.http.client;

import com.http.bean.HttpParamter;
import com.http.core.MyHttpClient;

public class Client {

	public static void main(String[] args) {
		MyHttpClient c = new MyHttpClient();
		/*String s = c.doGet("http://m.weather.com.cn/data/101010100.html", 
				new HttpParamter[] {new HttpParamter("uid","1111"),new HttpParamter("topicid","test-W10000012"),
				new HttpParamter("creator","3349258552")});*/
		String s = c.doGet("http://m.weather.com.cn/data/101010100.html", null);
		System.out.println(s);
	}
}
