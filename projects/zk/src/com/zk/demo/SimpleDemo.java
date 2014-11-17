package com.zk.demo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class SimpleDemo {

	public static void main(String[] args) {
		try {
			ZooKeeper zk = new ZooKeeper("172.16.72.101:2181", 500000, new Watcher() {
				
				public void process(WatchedEvent event) {
					System.out.println("event-------");
				}
				
			});
			zk.create("/root", "myData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/root/childone","childone".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			zk.getChildren("/root",true);
			System.out.println(zk.getData("/root/childone", true, null));
			zk.setData("/root/childone","childonemodify".getBytes(), -1);
			zk.delete("/root/childone", -1);
			zk.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
