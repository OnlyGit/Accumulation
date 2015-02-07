package com.http.core;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.http.bean.HttpParamter;

/**
 * http请求入口
 * @author qjf
 */
public class MyHttpClient {

	DefaultHttpClient httpClient = null;
	
	public MyHttpClient() {
		this.init(false);
	}
	
	public MyHttpClient(boolean usePool) {
		this.init(true);
	}
	
	/**
	 * 初始化
	 * @param usePool
	 */
	private void init(boolean usePool) {
		if(usePool) {
			httpClient = HttpConnectionManager.getHttpClient();
		} else {
			httpClient = HttpConnectionManager.getSingleHttpClient();
		}
	}
	
	/**
	 * get请求
	 * @param url
	 * @param params
	 * @return
	 */
	public String doGet(String url, HttpParamter[] params) {
		String result = "";
		List<NameValuePair> formparams = this.getParams(params);
		try {
			if(formparams.size() > 0) {
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(formparams, "UTF-8"));
			}
			HttpGet method = new HttpGet(url);
			result = this.httpRequest(method);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public String doPost(String url, HttpParamter[] params) {
		String result = "";
		HttpPost method = new HttpPost(url);
		
		List<NameValuePair> formparams = this.getParams(params);
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
			method.setEntity(entity);
			this.httpRequest(method);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 执行http请求
	 * @param method
	 * @return
	 * @throws IOException
	 */
	private String httpRequest(HttpRequestBase method) throws IOException {
		System.out.println(method.getURI().toString());
		StringBuffer result = new StringBuffer();
		HttpResponse resData = httpClient.execute(method);
		
		int statusCode = resData.getStatusLine().getStatusCode();
		System.out.println("状态码为："+statusCode);
		
		HttpEntity entity = resData.getEntity();
		BufferedInputStream in = new BufferedInputStream(entity.getContent());
		byte[] buf = new byte[1024];
		int length = 0;
		while((length = in.read(buf)) != -1) {
			String str = new String(buf, 0, length);
			result.append(str);
		}
		return result.toString();
	}
	
	/**
	 * 获取请求参数
	 * @param params
	 * @return
	 */
	private List<NameValuePair> getParams(HttpParamter[] params) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if(params != null && params.length > 0) {
			for(HttpParamter param : params) {
				formparams.add(new BasicNameValuePair(param.getName(), param.getValue()));
			}
		}
		return formparams;
	}
	
}
