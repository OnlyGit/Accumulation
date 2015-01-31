package com.wechat.http;

import java.net.URLEncoder;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.wechat.servlet.SHA;
import com.wechat.servlet.XmlUtil;

public class WechatHttpClient {

	DefaultHttpClient httpClient = new DefaultHttpClient();

	public static void main(String[] args) {
		String[] str = {"noncestr=kBVBcqDqzOuD98Lo", "timestamp=1422609033", "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VIri8ngOTKUVLRMPFk1sorui62PPhjarsg4vkja_IMb8dbaHD4rogBE9KviwbdetaQ","url=http://joepeanut.xicp.net:57156/wechatjs/demo.html"};
        Arrays.sort(str); // 字典序排序
        
        StringBuffer sb = new StringBuffer(str[0]+"&"+str[1]+"&"+str[2]+"&"+str[3]);
        System.out.println(sb);
        
        System.out.println(SHA.encode(sb.toString())); 
	}

	private static void getToken() {
		WechatHttpClient w = new WechatHttpClient();
		StringBuffer result = w.get("https://api.weixin.qq.com/cgi-bin/token", new Param[] {
				new Param("grant_type", "client_credential"), new Param("appid", "wx2488fcda19ff3c71"),
				new Param("secret", "181562233a30754ad227298ccf9cc7b7") });
		
		try {
			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getMusic() {
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
