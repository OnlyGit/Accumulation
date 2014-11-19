package com.joe.config.manager.util;

import java.io.IOException;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ConfigManager{

	private ZooKeeper zookeeper;
	private String node;
	
	ConfigManager(String address, String node) {
		try {
			this.zookeeper = new ZooKeeper(address, 5000, null);
			this.node = node;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新配置信息
	 * @param data
	 */
	public void updateConfig(byte[] data) {
		Stat stat;
		try {
			stat = this.zookeeper.exists(node, false);
			if(stat != null) {
				this.zookeeper.setData(node, data, stat.getVersion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ConfigManager cm = new ConfigManager("localhost:2181","/demo");
		cm.updateConfig("configggg".getBytes());
	}
}
