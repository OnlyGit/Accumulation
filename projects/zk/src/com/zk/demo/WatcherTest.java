package com.zk.demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class WatcherTest implements Watcher {
	
	private ZooKeeper zk;
	private String node;

	@Override
	public void process(WatchedEvent event) {
		try {
//			this.zk.exists(node, true);
			System.out.println(event.toString());
		} catch (Exception e) {
		}
	}
	
	public WatcherTest(String address,String nodePath) {
		try {
			zk = new ZooKeeper(address, 3000, this);
			this.node = nodePath;
			if(zk.exists(node, true) != null) {
				System.out.println("node already exists!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setWatcher() {
		try {
			Stat s = zk.exists(node, false);
			System.out.println(s.getDataLength());;
			if(s != null) {
				zk.getData(node, false, s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void triggerWatcher() {
		try {
			Stat s = zk.exists(node, false);
			zk.setData(node, "demoTest".getBytes(), s.getVersion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConn() {
		try {
			if(zk != null) {
				zk.close();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		WatcherTest wt = new WatcherTest("localhost:2181","/demo");
		wt.setWatcher();
		wt.triggerWatcher();
		wt.triggerWatcher();
		wt.triggerWatcher();
		wt.setWatcher();
		wt.closeConn();
	}
}
