package com.wechat.http;

import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.wechat.servlet.XmlUtil;

public class WechatHttpClient {

	DefaultHttpClient httpClient = new DefaultHttpClient();

	public static void main(String[] args) {
		WechatHttpClient w = new WechatHttpClient();
		StringBuffer result = w.get("http://box.zhangmen.baidu.com/x", new Param[] {
				new Param("op", "12"), new Param("count", "1"),
				new Param("title", "怒放的生命$$汪峰$$$$") });
		
		try {
			result.substring("<?xml version=\"1.0\" encoding=\"gb2312\" ?>".length());
			XmlUtil.getMusicResult(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StringBuffer httpRequest(String url, HttpRequestBase method) {
		StringBuffer resData = null;
		try {
			HttpResponse result = httpClient.execute(method);
			long start = System.currentTimeMillis();
			resData = new StringBuffer(EntityUtils.toString(result.getEntity()));
			System.out.println("获取字符串："+(System.currentTimeMillis() - start));
			System.out.println(resData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resData;
	}

	private StringBuffer get(String url, Param[] params) {
		if (null != params && params.length > 0) {
			String encodedParams = this.encodeParameters(params);
			if (-1 == url.indexOf("?")) {
				url += "?" + encodedParams;
			} else {
				url += "&" + encodedParams;
			}
		}
		HttpGet method = new HttpGet(url);
		return this.httpRequest(url, method);
	}

	private StringBuffer post(String url, Param[] params) {
		HttpPost method = new HttpPost(url);
		return this.httpRequest(url, method);
	}

	private String encodeParameters(Param[] params) {
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < params.length; j++) {
			if (j != 0) {
				buf.append("&");
			}
			try {
				buf.append(URLEncoder.encode(params[j].getName(), "UTF-8"))
						.append("=")
						.append(URLEncoder.encode(
								params[j].getValue() == null ? "" : params[j]
										.getValue(), "UTF-8"));
			} catch (java.io.UnsupportedEncodingException neverHappen) {
			}
		}
		return buf.toString();
	}
	
	private String getResult(StringBuffer buff) {
		return "";
	}
}
