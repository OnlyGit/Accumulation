package com.http.client;

import com.http.bean.HttpParamter;
import com.http.core.MyHttpClient;

public class Client163 {

	public static void main(String[] args) {
		MyHttpClient c = new MyHttpClient();
		String result = c.doPost("https://mail.163.com/entry/cgi/ntesdoor?df=mail163_letter&from=web&funcid=loginone&iframe=1&language=-1&passtype=1&product=mail163&net=t&style=-1&race=102_86_99_gz&uid=x_qiang_d@163.com", 
				new HttpParamter[] {new HttpParamter("username","x_qiang_d@163.com"),
				new HttpParamter("password","qiangdao")}, "logType=; starttime="+System.currentTimeMillis());
		String url = result.substring(result.indexOf("http://mail.163.com"), result.indexOf("mail163_letter") + "mail163_letter".length());
//		System.out.println(c.getCookies());
		String b = c.doGet(url, null, c.getCookies().toString());
	}
}
