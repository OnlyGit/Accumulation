package com.extend;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.test.Util;

public class Client {
	
	public static void main(String[] args) {
//		save1();
//		load1();
//		query1();
//		saveCat1();
		
		
//		Animal a = new Animal();
//		Animal d = (Animal)new Dog();
//		
//		System.out.println(a instanceof Animal);
//		System.out.println(a instanceof Dog);
//		
//		System.out.println(d instanceof Animal);
//		System.out.println(d instanceof Dog);
		
		
		try {
			Class clas = Class.forName("com.extend.Cat");
			Field[] fields = clas.getDeclaredFields();
			for(int i = 0 ; i < fields.length; i++){ 
				System.out.println(fields[i].getName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void save1() {
		Session session = Util.getSession();
		Transaction	tx = null;
		try {
			tx = session.beginTransaction();
			
			Animal animal = new Animal();
			animal.setName("animal");
			
			Dog dog = new Dog();
			dog.setHeight(12);
			dog.setName("dog");
			
			Pig pig = new Pig();
			pig.setWeight(12);
			pig.setName("dog");
			
			session.save(dog);
			session.save(pig);
			session.save(animal);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.closeSession(session);
		}
	}
	
	private static void load1() {
		Session session = Util.getSession();
		Transaction	tx = null;
		try {
			tx = session.beginTransaction();
			
			Animal animal = (Animal)session.load(Dog.class, 3);
			System.out.println(animal instanceof Animal);
			System.out.println(animal instanceof Dog);
			System.out.println(animal instanceof Pig);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.closeSession(session);
		}
	}
	
	private static void query1() {
		Session session = Util.getSession();
		Transaction	tx = null;
		try {
			tx = session.beginTransaction();
			
			Query query=session.createQuery("from Animal");//测试HQL  
            List list = query.list();  
             
           for (int i = 0; i < list.size(); i++) {  
               Object object = list.get(i);  
               if (object instanceof Dog) {  
            	   Dog s = (Dog) object;  
                   System.out.println(s.getName() + ":" + s.getAminalId());  
               } else if (object instanceof Pig) {  
            	   Pig t = (Pig) object;  
                   System.out.println(t.getName() + ":" + t.getAminalId());  
               }else System.out.println("Animal");  
           }  
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.closeSession(session);
		}
	}
	
	/**
	 * hibernate.cfg.xml中去掉Animal的配置文件，加入Cat的配置文件
	 */
	private static void saveCat1() {
		Session session = Util.getSession();
		Transaction	tx = null;
		try {
			tx = session.beginTransaction();
			
			Cat cat = new Cat();
			cat.setName("cat");
			cat.setType("cat");
			cat.setMiao("miao");
			
			session.save(cat);
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.closeSession(session);
		}
	}
}
