package com.basic.jvm;

interface Parent {
	String parent = "parent";
	int dog = Dog.getDog();
}

class Dog {
	public static final String dog = "dog";
	
	static {
		System.out.println("Dog is initialed");
	}
	public static int getDog() {
		return 2;
	}
}

public class InitTest {

	public static void main(String[] args) {
		System.out.println(Dog.dog);
		System.out.println(Parent.parent);
	}

}