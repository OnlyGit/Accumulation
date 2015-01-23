package weibo4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

public class Mass extends Weibo {

	public Mass(String access_token) {
		this.access_token = access_token;
	}

	public String mass(String uid) {
		String url = WeiboConfig.getValue("baseURL") + "comments/show.json";
		// client.post(url, params, access_token);
		return null;
	}

	public static void main(String[] args) {
		/*String s = "{\"touser\": [\"uid1\"],\"text\": {\"content\": \"CONTENT\"},\"msgtype\": \"text\"}";
		s = "{\"loginname\": \"admin\",\"pwd\": \"admin\"}";
		StringBuilder sb = new StringBuilder(s);
		String param = sb.toString().replace("\"", "%22")  
		        .replace("{", "%7b").replace("}", "%7d");
		String url = "https://m.api.weibo.com/2/messages/sendall.json?access_token=2.00yfXRjC24okeCa30f37c1cfbXnRPC";
		url = "http://localhost:8080/user/login/login";
		try {
			JSONObject obj = new JSONObject(s);
			System.out.println(urlPostMethod(url,obj.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		HttpClient httpClient = new HttpClient();
		GetMethod method = new GetMethod("http://www.ithome.com/");
		try {
			long start = System.currentTimeMillis();
			httpClient.executeMethod(method);
			String responses = method.getResponseBodyAsString();
//			System.out.println(responses);
			System.out.println(System.currentTimeMillis() - start);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String urlPostMethod(String url1, String params) {
		System.out.println(params);
		HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod(url1);
		try {
			if (params != null && !params.trim().equals("")) {
				RequestEntity requestEntity = new StringRequestEntity(params,"application/json", "UTF-8");
				method.setRequestEntity(requestEntity);
			}
			method.releaseConnection();
			httpClient.executeMethod(method);
			String responses = method.getResponseBodyAsString();
			System.out.println(responses);
			
			/*JSONObject jsonRequest = new JSONObject();
			jsonRequest.put("id", "201405190002");
			jsonRequest.put("clientId", "F0-DE-F1-98-67-68");
			jsonRequest.put("systemNo", "01");
			jsonRequest.put("requestNo", "145678");
			jsonRequest.put("body", "你好服务器");
			jsonRequest.put("time", "201405191015");
			jsonRequest.put("signInfo", "10ACFAC685C18FB833641C50C9FE06D");
			RequestEntity entity = new StringRequestEntity(
					jsonRequest.toString(), "text/xml", "gbk");
			method.setRequestEntity(entity);
			HttpClient client = new HttpClient();
			client.executeMethod(method);*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
