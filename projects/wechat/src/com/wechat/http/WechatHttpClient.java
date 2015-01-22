package com.wechat.http;

import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WechatHttpClient {

	DefaultHttpClient httpClient = new DefaultHttpClient();

	public static void main(String[] args) {
		WechatHttpClient w = new WechatHttpClient();
		w.get("http://box.zhangmen.baidu.com/x", new Param[] {
				new Param("op", "12"), new Param("count", "1"),
				new Param("title", "怒放的生命$$汪峰$$$$") });
	}

	public String httpRequest(String url, HttpRequestBase method) {
		try {
			HttpResponse result = httpClient.execute(method);
			String resData = EntityUtils.toString(result.getEntity());
			System.out.println(resData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private String get(String url, Param[] params) {
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

	private String post(String url, Param[] params) {
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
}
