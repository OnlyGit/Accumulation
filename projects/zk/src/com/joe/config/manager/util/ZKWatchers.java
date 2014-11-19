package com.joe.config.manager.util;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZKWatchers implements Watcher, Runnable{

	private ZooKeeper zooKeeper;
	private String node;
	private String proKey;
	
	public ZKWatchers() {
		
	}
	
	public ZKWatchers(String address, String node, String proKey) {
		try {
			this.zooKeeper = new ZooKeeper(address, 5000, this);
			this.node = node;
			this.proKey = proKey;
			if(this.zooKeeper.exists(node, false) == null) {
				this.zooKeeper.create(node, "aa".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		try {
			synchronized (this) {
				while(true) {
					wait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(WatchedEvent event) {
		try {
			this.zooKeeper.exists(node, true);
			if(event.getType() == EventType.NodeDataChanged) {
				String data = new String(this.zooKeeper.getData(node, false, null));
				String path = PropertiesUtil.getValue(proKey);
				FileUtils.writeContent(path, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
