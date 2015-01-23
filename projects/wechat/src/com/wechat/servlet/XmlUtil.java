package com.wechat.servlet;

import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.wechat.bean.Music;
import com.wechat.bean.MusicMsg;
import com.wechat.bean.SendMsg;
import com.wechat.bean.WechatMsg;

public class XmlUtil {

	/**
	 * 将xml格式的输入流，转换为classe对应的对象
	 * @param in
	 * @param classes
	 * @return
	 * @throws Exception
	 */
	public static <T> T parseXml(InputStream in, Class<T> classes) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(in);
		
		T obj = classes.newInstance();
		
		Element root = doc.getRootElement();
		List<Element> list = root.elements();

		for(Element ele : list) {
//			System.out.println(ele.getName() + "-" + ele.getTextTrim());
			String eleName = ele.getName();
			Method method = classes.getMethod("set"+eleName, String.class);
			method.invoke(obj, ele.getTextTrim());
		}
		return obj;
	}
	
	/**
	 * 将WechatMsg对象转换为xml格式的字符串
	 * @param msg
	 * @return
	 * @throws Exception 
	 */
	public static String getXml(WechatMsg msg) throws Exception {
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		
		Element root = doc.addElement("xml");
		Class classes = WechatMsg.class;
		XMLWriter xw;
		StringWriter sw = new StringWriter();
		OutputFormat opf;
		Field[] fields = classes.getDeclaredFields();
		
		for(Field field : fields) {
			
			//Class com.wechat.servlet.XmlUtil can not access a member of class com.wechat.bean.WechatMsg with modifiers "private"
			//ele.setText(field.get(msg).toString());
			
			Method method = classes.getMethod("get"+field.getName(), null);
			String result = (String) method.invoke(msg, null);
			
			if(result == null) {
				continue;
			} else {
				Element ele = root.addElement(field.getName());
				ele.setText(result);
			}
		}
		
		msg.getAggregationItem(root);
		
		opf = OutputFormat.createCompactFormat();
		opf.setEncoding("UTF-8");
		xw = new XMLWriter(sw);
		xw.write(doc);
		xw.close();
		sw.close();
		return sw.toString();
	}
	
	/**
	 * 根据对象创建xml节点
	 * @param parent
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T> Element getElementByBean(Element parent, T obj) throws Exception {
		Class classes = obj.getClass();
		Field[] fields = classes.getDeclaredFields();
		
		String className = classes.getSimpleName();
		Element item = parent.addElement(className);

		for (Field field : fields) {
			Method method = classes.getMethod("get" + field.getName(), null);
			String result = (String) method.invoke(obj, null);
			
			if(result == null) {
				continue;
			} else {
				Element ele = item.addElement(field.getName());
				ele.setText(result);
			}
		}
		return item;
	}
	
	
	/**
	 * 解析百度音乐播放地址
	 * @param buff
	 * @return
	 * @throws Exception
	 */
	public static String getMusicResult(String buff) throws Exception {
		long start = System.currentTimeMillis();
		Document doc = DocumentHelper.parseText(buff);
		Element root = doc.getRootElement();
		
		Element urlEle = root.element("url");
		StringBuffer encode = new StringBuffer(urlEle.element("encode").getTextTrim());
		StringBuffer decode = new StringBuffer(urlEle.element("decode").getTextTrim());
		
		System.out.println(encode.substring(0, encode.lastIndexOf("/") + 1));
		System.out.println(decode.substring(0, decode.lastIndexOf("&")));
		System.out.println(System.currentTimeMillis() - start);
		return "";
	}
	
	public static void main(String[] args) {
		try {
			MusicMsg msg = new MusicMsg();
			Music music = new Music();
			
			msg.setContent("content");
			msg.setCreateTime("123123");
			msg.setEncrypt("密文");
			msg.setFromUserName("qjf");
			msg.setToUserName("wp8");
			msg.setMsgType("music");
			msg.setMusic(music);
			
			music.setDescription("喜剧之王");
			music.setHQMusicUrl("http://");
			music.setMusicUrl("-----");
			music.setTitle("123");
			System.out.println(XmlUtil.getXml(msg));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
