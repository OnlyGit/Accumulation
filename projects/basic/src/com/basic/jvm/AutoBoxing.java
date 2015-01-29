package com.basic.jvm;

/**
 * 自动装箱
 * 
 * 包装类的"=="运算在不遇到算术运算的情况下不会自动拆箱，equals方法不处理类型转换关系
 */
public class AutoBoxing {

	public static void main(String[] args) {
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3l;
		
		System.out.println(c == d);// true
		System.out.println(e == f);// false
		System.out.println(c == (a + b));// true
		System.out.println(c.equals(a + b));// true
		System.out.println(g == (a + b));// true
		System.out.println(g.equals(a + b));// false
	}
}
