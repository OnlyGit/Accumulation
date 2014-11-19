package com.joe.config.manager.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {

	private static Properties prop = new Properties();
	
	private static Map<String, String> map = new HashMap<String, String>();
	
	public static String getValue(String key) {
		InputStream in = PropertiesUtil.class.getResourceAsStream("../conf/cfg.properties");
		String result = "";
		try {
			prop.load(in);
			result = (String)prop.get(key);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static void saveValue() {
		try {
//			System.out.println(System.getProperty("user.dir"));
			//此时的相对路径(以user.dir为基路径的路径)为"src/com/joe/config/manager/conf/cfg.properties"
			System.out.println("savevalue----------------");
			OutputStream out = new FileOutputStream("src/com/joe/config/manager/conf/cfg.properties");
			Set<Entry<String, String>> set = map.entrySet();
			for(Iterator<Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Entry<String, String> entry = it.next();
				prop.setProperty(entry.getKey(), entry.getValue());
			}
			prop.store(out, "");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateValue(String key, String value) {
		map.put(key, value);
	}
	
	public static void main(String[] args) {
		PropertiesUtil.getValue("client1Cfg");
	}
}
