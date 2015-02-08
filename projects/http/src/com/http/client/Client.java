package com.http.client;

import com.http.bean.HttpParamter;
import com.http.core.MyHttpClient;

public class Client {

	public static void main(String[] args) {
		MyHttpClient c = new MyHttpClient();
		/*String s = c.doGet("http://m.weather.com.cn/data/101010100.html", 
				new HttpParamter[] {new HttpParamter("uid","1111"),new HttpParamter("topicid","test-W10000012"),
				new HttpParamter("creator","3349258552")});*/
		String s = c.doPost("https://mail.163.com/entry/cgi/ntesdoor?df=mail163_letter&from=web&funcid=loginone&iframe=1&language=-1&passtype=1&product=mail163&net=t&style=-1&race=102_86_99_gz&uid=x_qiang_d@163.com", 
				new HttpParamter[] {new HttpParamter("username","x_qiang_d@163.com"),
				new HttpParamter("password","qiangdao")});
//		String b = c.doGet("http://mail.163.com/js6/main.jsp?sid=qAkoQyvvLYSUxOyDeGvvZATRCMsSPeoO&df=mail163_letter", null);
		
//		System.out.println(s);
		//logType=; starttime=1423408056584
	}
	
}
