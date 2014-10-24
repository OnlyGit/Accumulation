package com.test;

import org.apache.log4j.Logger;

public class Client {

	public static final int a = 3;
	static {
		System.out.println("asdfasdf");
	}
	public static void main(String[] args) {
//		Logger managerLogger = Logger.getLogger("managerLogger");
//		managerLogger.info("1213---------");
		
		
		System.out.println(String.format("%010d", 123456));
	}
}
