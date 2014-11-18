package com.joe.config.manager.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtil {

	private static Properties prop = new Properties();
	
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
	
	public static void setValue(String key, String value) {
		try {
//			System.out.println(System.getProperty("user.dir"));
			//此时的相对路径(以user.dir为基路径的路径)为"src/com/joe/config/manager/conf/cfg.properties"
			OutputStream out = new FileOutputStream("src/com/joe/config/manager/conf/cfg.properties");
			prop.setProperty(key, value);
			prop.store(out, "");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PropertiesUtil.getValue("client1Cfg");
	}
}
