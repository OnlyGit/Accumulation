package com.zk.demo;

import java.util.Arrays;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 对zookeeper接口的封装
 * @Author QJF0----
 * @Desception 
 * @Date 2014年11月18日下午5:17:19
 */
public class Demo implements Watcher{

	public ZooKeeper zooKeeper;
	private static final int SESSION_TIME_OUT = 2000;
	@Override
	public void process(WatchedEvent event) {
//		this.zooKeeper.exists(znode, true);
		System.out.println("process-------"+event.getType()+"---");
	}
	
	public void connectZooKeeper(String host) throws Exception {
		this.zooKeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
		System.out.println("zookeeper connect ok");
	}
	
	public List<String> getChildren(String path) throws Exception {
		return this.zooKeeper.getChildren(path, false);
	}
	
	public void createNode(String path, byte[] data) throws Exception {
		this.zooKeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	
	public void setData(String path, byte[] data, int version) throws Exception {
		this.zooKeeper.setData(path, data, version);
	}
	
	public byte[] getData(String path) throws Exception {
		return this.zooKeeper.getData(path, false, null);
	}
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		try {
			demo.connectZooKeeper("localhost:2181");
			
			byte[] data = {2,2,3,4};
//			demo.createNode("/demo", data);
			List<String> list = demo.getChildren("/dubbo");
			for(String item : list) {
				System.out.println(item);
			}
			System.out.println(Arrays.toString(demo.getData("/demo")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
