package com.http.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.xpath.XPathAPI;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class BaiduClient {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://baike.baidu.com/search/word?word=%E8%B0%B7%E6%AD%8C&pic=1&sug=1&oq=%E8%B0%B7%E6%AD%8C&rsp=[object%20Object]");
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setFollowRedirects(true);
			
			InputStream in = conn.getInputStream();
			
			/*BufferedReader br = new BufferedReader(new InputStreamReader(in)); 
			
			StringBuffer buff = new StringBuffer();
			while(br.readLine() != null) {
				buff.append(br.readLine());
			}
			System.out.println(buff);*/
			
			getContent(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void getContent(InputStream in) throws Exception {
		DOMParser domParser = new DOMParser();
		String xpathForAnchor = "//DIV[@class='card-summary-content']/DIV[@class='para']";
		String charset = "UTF-8";
		InputSource source = new InputSource(in);
		
		source.setEncoding(charset);
		domParser.setFeature("http://xml.org/sax/features/namespaces", false);
		
		domParser.parse(source);
		Document dom = domParser.getDocument();
		if (dom != null)
			dom.normalize();
		
		Node rootNode = dom.getDocumentElement();
		
		NodeList anchorNodes = XPathAPI.selectNodeList(rootNode, xpathForAnchor);
		
		StringBuffer buff = new StringBuffer();
		System.out.println(anchorNodes.getLength());
		for (int j = 0; j < anchorNodes.getLength(); j++) {
			Node anchorNode = anchorNodes.item(j);
			buff.append(anchorNode.getTextContent());
			buff.append("\r\n");
		}
		
		createFile(buff);
	}
	
	private static void createFile(StringBuffer buff) throws Exception {
		byte[] bytes = buff.toString().getBytes();
		File file = new File("G:/1.txt");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes);
		fos.close();
	}
}
