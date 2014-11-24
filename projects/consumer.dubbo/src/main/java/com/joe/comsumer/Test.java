package com.joe.comsumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.it.service.inter.WorldService;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationConsumer.xml" });
		
		context.start();

		WorldService service = (WorldService)context.getBean("worldService");
		System.out.println(service.service("args"));;
	}

}
