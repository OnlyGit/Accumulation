package com.zk.demo.conf;

public class ConfigManagerDemo {

	public static void main(String[] args) {
		try {
			ZKWatcher zw1 = new ZKWatcher();  
	        zw1.connect("localhost:2181", "/root1");  
	        ZKWatcher zw2 = new ZKWatcher();  
	        zw2.connect("localhost:2181", "/root1");  
	        new Thread(zw1).start();  
	        new Thread(zw2).start();  
	        ConfigCenter cc = new ConfigCenter("localhost:2181","/root1");  
	        cc.updateConfig("a");  
	        cc.updateConfig("b");  
	        cc.updateConfig("c");  
	        cc.updateConfig("d");
		} catch (Exception e) {
			
		}
	}
}
