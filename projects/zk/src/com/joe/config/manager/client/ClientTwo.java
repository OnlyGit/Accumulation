package com.joe.config.manager.client;

import com.joe.config.manager.util.ZKWatchers;

public class ClientTwo {

	public static void main(String[] args) {
		Thread thread = new Thread(new ZKWatchers("localhost:2181","/demo", "client2Cfg"));
		thread.start();
	}
}
