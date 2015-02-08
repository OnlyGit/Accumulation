package com.http.core;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
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
			
			method.setHeader("Cookie", "MailMasterPopupTips=1423408157931; logType=; starttime=1423408962538; nts_mail_user=x_qiang_d:-1:1; df=mail163_letter; JSESSIONID=CD726CCD56A1574C9A748975C02C69C4; SID=adc50c36-584f-43de-8c83-9fff9e5f4582; P_INFO=x_qiang_d@163.com|1423408851|0|mail163|00&99|bej&1423408335&mail163#bej&null#10#0#0|&0|mail163|x_qiang_d@163.com; S_INFO=1423408851|0|2&10##|x_qiang_d#maozexi520@126.com; NTES_SESS=OEEtDDtAW9ZVvMwAnE4.wkhXkieYK3CY4xw1Cvggr_UhRfAyoR3AD02GZJZAndJQXrLDQKTerVhx7rD5ODbwusGNPnEAdSnMsi7pLXe3hL4G21UmBDhG0YcHIVBnXcAVytwhIHsWfJQPn; mail_upx=c7bj.mail.163.com|c1bj.mail.163.com|c2bj.mail.163.com|c3bj.mail.163.com|c4bj.mail.163.com|c5bj.mail.163.com|c6bj.mail.163.com; mail_upx_nf=; mail_idc=\"\"; Coremail=1423408851580%qAkoQyvvLYSUxOyDeGvvZATRCMsSPeoO%g5a39.mail.163.com; MAIL_MISC=\"x_qiang_d#maozexi520@126.com\"; cm_last_info=\"dT14X3FpYW5nX2QlNDAxNjMuY29tJmQ9aHR0cCUzQSUyRiUyRm1haWwuMTYzLmNvbSUyRmpzNiUyRm1haW4uanNwJTNGc2lkJTNEcUFrb1F5dnZMWVNVeE95RGVHdnZaQVRSQ01zU1Blb08mcz1xQWtvUXl2dkxZU1V4T3lEZUd2dlpBVFJDTXNTUGVvTyZoPWh0dHAlM0ElMkYlMkZtYWlsLjE2My5jb20lMkZqczYlMkZtYWluLmpzcCUzRnNpZCUzRHFBa29ReXZ2TFlTVXhPeURlR3Z2WkFUUkNNc1NQZW9PJnc9bWFpbC4xNjMuY29tJmw9LTEmdD0tMSZuPXQ=\"; MAIL_SESS=OEEtDDtAW9ZVvMwAnE4.wkhXkieYK3CY4xw1Cvggr_UhRfAyoR3AD02GZJZAndJQXrLDQKTerVhx7rD5ODbwusGNPnEAdSnMsi7pLXe3hL4G21UmBDhG0YcHIVBnXcAVytwhIHsWfJQPn; MAIL_SINFO=\"1423408851|0|2&10##|x_qiang_d#maozexi520@126.com\"; MAIL_PINFO=\"x_qiang_d@163.com|1423408851|0|mail163|00&99|bej&1423408335&mail163#bej&null#10#0#0|&0|mail163|x_qiang_d@163.com\"; secu_info=0; mail_entry_sess=8451c33a60e7ec206a0a5aa54a4f25b4f247325c018c5cc09fa3a40444570d302c6ea8b7f90090c616eef683dbd8d0521834b059882f722e27550ef85fa1ec472df84404bf38337fd3657edb79ed956681e2a0a6eb111a2ebc8415115b35ad7b61af84cc947157ae7cc34bf99f56e262d50d2e9829d9263eb788154487b4720e4dd48e65c3d0b56d587ad1ad1772ba91ef9c9832a0ddd46828599c84a54c37e8949f8f238d6aad076dbad19e9461f4c688c70b195c0bf2f6c77189e679657a3f; locale=\"\"; Coremail.sid=qAkoQyvvLYSUxOyDeGvvZATRCMsSPeoO; mail_style=js6; mail_uid=x_qiang_d@163.com; mail_host=mail.163.com");
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
		
		method.addHeader("Cookie", "logType=; starttime="+System.currentTimeMillis());
		
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
		
		Header[] setCookie = resData.getHeaders("Set-Cookie");
		
		System.out.println("状态码为："+statusCode);

		for(Header heade : setCookie) {
			System.out.println(heade.getValue());
		}
		
		
		HttpEntity entity = resData.getEntity();
		BufferedInputStream in = new BufferedInputStream(entity.getContent());
		byte[] buf = new byte[1024];
		int length = 0;
		while((length = in.read(buf)) != -1) {
			String str = new String(buf, 0, length);
			System.out.println(str);
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
