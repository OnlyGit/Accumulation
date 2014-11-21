package com.joe.distribute.lock;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class DistributedClient {

	private static final int SESSION_OUTTIME = 5000;
	private String host = "localhost:2181";
	private String groupNode = "demo";
	private String subNode = "sub";
	private ZooKeeper zk;
	private String thisPath;
	private String waitPath;
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	public void ConnectZk() throws Exception {
		this.zk = new ZooKeeper(host, SESSION_OUTTIME, new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				try {
					if(event.getState() == KeeperState.SyncConnected) {
						latch.countDown();
					}
					if(event.getType() == EventType.NodeDeleted && event.getPath().equals(waitPath)) {
						doSomething();
					}
				} catch (Exception e) {
				}
			}
			
		});
		
		latch.await();
		
		List<String> children = this.zk.getChildren("/" + groupNode, false);
		System.out.println(this.thisPath+"-begin-"+children.size());
		thisPath = zk.create("/" + groupNode + "/" + subNode, null, Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
		
//		Thread.sleep(10);
		
		children = this.zk.getChildren("/" + groupNode, false);
		System.out.println(this.thisPath+"-end-"+children.size());
		
		if(children.size() == 1) {
			doSomething();
		} else {
			String thisNode = thisPath.substring(("/" + groupNode + "/").length());
			Collections.sort(children);
			int index = children.indexOf(thisNode);
//			System.out.println(this.thisPath+"--"+index);
			if(index == -1) {
			} else if(index == 0) {
				doSomething();
			} else {
				this.waitPath = "/" + groupNode + "/" + children.get(index - 1);
				zk.getData(waitPath, true, new Stat());
			}
		}
	}
	
	private void doSomething() throws Exception {
		try {
            System.out.println("gain lock: " + thisPath);  
            Thread.sleep(2000);  
            // do something  
        } finally {
            System.out.println("finished: " + thisPath);  
            // 将thisPath删除, 监听thisPath的client将获得通知  
            // 相当于释放锁  
            zk.delete(this.thisPath, -1);  
        } 
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					try {
						DistributedClient dl = new DistributedClient();
						dl.ConnectZk();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
