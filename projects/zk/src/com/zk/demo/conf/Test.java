package com.zk.demo.conf;

public class Test {

	public static void main(String[] args) {
		ConfigCenter cc = new ConfigCenter("localhost:2181","/root1");  
        cc.updateConfig("f");  
	} 
}
