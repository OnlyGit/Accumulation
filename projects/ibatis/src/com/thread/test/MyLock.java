package com.thread.test;

public class MyLock {

	private static int i = 0;
	
	/**
	 * 私有对象(锁)
	 */
	private Object obj = new Object();
	
	/**
	 * 无锁方法
	 * @param threadID
	 * @param thread
	 */
	public void noSynMethod(long threadID, MyThread thread) {
		System.out.println("nosyn: class obj is " + thread + ", threadId is"
				+ threadID);
	}
	
	/**
	 * 在方法上加锁
	 */
	public synchronized void synOnMethod() {
		System.out.println("synOnMethod begins" + ", time = "+ System.currentTimeMillis() + "ms");
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("synOnMethod ends");
	}
	
	/**
	 * 代码段加锁
	 */
	public void synInMethod() {
		synchronized (this) {
			System.out.println("synInMethod begins" + ", time = "
					+ System.currentTimeMillis() + "ms");
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("synInMethod ends");
		}
	}
	
	/**
	 * 给私有对象加锁
	 */
	public void synMethodWithObj() {
		synchronized (obj) {
			System.out.println("synMethodWithObj begins" + ", time = "
					+ System.currentTimeMillis() + "ms");
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("synMethodWithObj ends");
		}
	}
	
	/**
	 * 类锁
	 */
	public static synchronized void increament() {
		System.out.println("class synchronized. i = " + i + ", time = "
				+ System.currentTimeMillis() + "ms");
		i++;
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 System.out.println("class synchronized ends.");
	}
	
	/**
	 * 另一个静态同步方法
	 */
	public static synchronized void add() {
		System.out.println("add");
	}
}
