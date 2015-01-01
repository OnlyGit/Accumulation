package com.basic.jdk.reflect;

public class Stub extends Super implements Interface {

	private int privateStub;
	public int publicStub = 12;
	
	public static final int finalParam = 2;
	
	private void stubPrivateMethod() {
		System.out.println("stubPrivateMethod");
	}
	
	void stubDefaultMethod() {
		
	}

	@Override
	public void interfaceMethod() {
		
	}
	
	public class StubInnerClass {
		
	}
}
