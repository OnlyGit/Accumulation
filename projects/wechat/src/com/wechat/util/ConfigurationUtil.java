package com.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 配置文件读取工具
 * @author CL-XIE
 */
public class ConfigurationUtil {

	private static Properties WECHAT_CONFIG = null;
	
	static {
		WECHAT_CONFIG = new Properties();
		InputStream inStream = ConfigurationUtil.class.getResourceAsStream("/conf/wechat.properties");
		try {
			WECHAT_CONFIG.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getConfig(String key) {
		return (String) WECHAT_CONFIG.get(key);
	}
	
}
