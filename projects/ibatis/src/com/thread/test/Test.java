package com.thread.test;

public class Test {

	public static void main(String[] args) {
		System.out.println("start time = " + System.currentTimeMillis()+"ms");
		MyLock lock = new MyLock();
		for (int i = 0; i < 3; i++) {
			Thread thread = new MyThread(lock, i);
			thread.start();
		}
	}
	
}
