package com.basic.jdk.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class Client {

	public static void main(String[] args) {
		try {
			accessSuperPrivate(new Stub());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//访问父类的private成员，递归的setAccessible
	private static void accessSuperPrivate(Object obj) throws Exception {
		Class stubClass = obj.getClass();
		String fieldName = "superSuper";
		
		Field f = null;
		
		for(; stubClass != Object.class; stubClass = stubClass.getSuperclass()) {
			try {
				f = stubClass.getDeclaredField(fieldName);
				f.setAccessible(true);
				System.out.println(f.getInt(obj));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取父类及接口信息
	 * @throws ClassNotFoundException
	 */
	private static void getSupers() throws ClassNotFoundException {
		Class stubClass = Class.forName("com.basic.jdk.reflect.Stub");
		
		Type[] types = stubClass.getGenericInterfaces();
		for(Type t : types) {
			System.out.println(t.toString());
		}
		
		Type type = stubClass.getGenericSuperclass();
		System.out.println(type.toString());
		
		
		Class parent = stubClass.getSuperclass();
		System.out.println(parent.getSimpleName());
		
		Class[] interfaces = stubClass.getInterfaces();
		for(Class i : interfaces) {
			System.out.println(i.getSimpleName());
		}
	}

	/**
	 * 访问属性
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static void fieldAccess() throws ClassNotFoundException,
			NoSuchFieldException, InstantiationException,
			IllegalAccessException {
		Class stubClass = Class.forName("com.basic.jdk.reflect.Stub");
		
		//public param
		Field f = stubClass.getField("publicStub");
		
		Object obj = stubClass.newInstance();
		
		System.out.println(f.getInt(obj));
		
		f.setInt(obj, 123);
		
		System.out.println(f.getInt(obj));
		
		//private param 访问private属性时，要setAccessible(true)
		Field privateField = stubClass.getDeclaredField("privateStub");
		privateField.setAccessible(true);
		
		System.out.println(privateField.getInt(obj));
		
		//public(1) static(8) final(16) field  return 1+8+16
		
		Field finalField = stubClass.getField("finalParam");
		System.out.println(finalField.getModifiers() + "---getModifiers");
	}

	/**
	 * 调用方法
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void methodInvoke() throws ClassNotFoundException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		Class stubClass = Class.forName("com.basic.jdk.reflect.Stub");
		
		// public method
		Method getMethod = stubClass.getMethod("getPrivateSuper", null);
		
		Object obj = stubClass.newInstance();
		
		
		System.out.println(getMethod.invoke(obj, null));;
		
		
		Method setMethod = stubClass.getMethod("setPrivateSuper", int.class);
		setMethod.invoke(obj, 1234);
		
		System.out.println(getMethod.invoke(obj, null));
		
		// private method
		Method privateMethod = stubClass.getDeclaredMethod("stubPrivateMethod", null);
		privateMethod.setAccessible(true);
		privateMethod.invoke(obj, null);
	}

	/**
	 * 属性测试
	 * @throws ClassNotFoundException
	 */
	private static void fields() throws ClassNotFoundException {
		Class stubClass = Class.forName("com.basic.jdk.reflect.Stub");
		Field[] fields = stubClass.getFields();
		fields = stubClass.getDeclaredFields();
		for(Field f : fields) {
			System.out.println(f.getName());
		}
	}

	/**
	 * 方法测试
	 * @throws ClassNotFoundException
	 */
	private static void methods() throws ClassNotFoundException {
		Class stubClass = Class.forName("com.basic.jdk.reflect.Stub");
		Method[] methods = stubClass.getMethods();
		methods = stubClass.getDeclaredMethods();//类自己声明的方法
		for(Method met : methods) {
			System.out.println(met.getName()+"-"+met.getModifiers());
		}
	}
	
	
	private static void test() {
		for(int i = 0; i < 10; i++) {
			System.out.println(i+"--------");
			
			try {
				Class c = Class.forName("a");
			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
			}
		}
	}
}
