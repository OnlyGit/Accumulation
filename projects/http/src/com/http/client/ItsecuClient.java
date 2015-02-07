package com.http.client;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.http.bean.HttpParamter;
import com.http.core.MyHttpClient;

/**
 * 用于测试dubbox的http请求
 * @author qjf
 */
public class ItsecuClient {


	public static void main(String[] args) {
		Properties pro = new Properties();
		try {
			pro.load(ItsecuClient.class.getResourceAsStream("itsecu.properties"));
			StringBuffer buf = new StringBuffer();
			buf.append(pro.getProperty("baseUrl"));
			buf.append(pro.getProperty("interfacePath"));
			buf.append("/");
			buf.append(pro.getProperty("methodPath"));
			
			String paramNames = pro.getProperty("paramNames");
			String paramValues = pro.getProperty("paramValues");
			
			if(paramValues == null || paramNames.equals("")) {
				new MyHttpClient().doGet(buf.toString(), null);
			} else {
				String[] paramNamesArr = paramNames.split("&");
				String[] paramValuesArr = paramValues.split("&");
				
				if(paramNamesArr.length != paramValuesArr.length) {
					throw new RuntimeException("参数名称与参数值不匹配！");
				}
				
				HttpParamter[] params = new HttpParamter[paramNamesArr.length];
				for(int i = 0; i < paramNamesArr.length; i++) {
					if(paramNamesArr[i].equals("")) {
						continue;
					}
					params[i] = new HttpParamter(paramNamesArr[i], paramValuesArr[i]);
				}
				new MyHttpClient().doGet(buf.toString(), params);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
