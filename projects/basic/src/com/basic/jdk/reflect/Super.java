package com.basic.jdk.reflect;

public class Super extends SuperSuper{

	private int privateSuper = 12;
	public int publicSuper;
	protected int protectedSuper;


	public int getPrivateSuper() {
		return privateSuper;
	}

	public void setPrivateSuper(int privateSuper) {
		this.privateSuper = privateSuper;
	}

	public int getPublicSuper() {
		return publicSuper;
	}

	public void setPublicSuper(int publicSuper) {
		this.publicSuper = publicSuper;
	}

	public int getProtectedSuper() {
		return protectedSuper;
	}

	public void setProtectedSuper(int protectedSuper) {
		this.protectedSuper = protectedSuper;
	}


	private void superPrivateMethod() {
		
	}
	
}
