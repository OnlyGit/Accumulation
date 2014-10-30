package com.ann;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Transaction;
import org.hibernate.Session;

public class Client {

	public static void main(String[] args) {
		delete();
	}
	
	
	private static void save1() {
		Session session = Util.getSession();
		Transaction	tx = null;
		try {
			tx = session.beginTransaction();
			
			Student stu1 = new Student();
			stu1.setStudentName("123");
			session.save(stu1);
			
			Student stu2 = new Student();
			stu2.setStudentName("223");
			session.save(stu2);
			
			
			
			Classes classes = new Classes();
			classes.setClassName("class1");
			
			Set<Student> student = new HashSet<Student>();
			student.add(stu1);
			student.add(stu2);
			
			
			classes.setStudent(student);
			
			session.save(classes);
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.closeSession(session);
		}
	}
	
	private static void delete() {
		Session session = Util.getSession();
		Transaction	tx = null;
		try {
			tx = session.beginTransaction();
			
			Classes classes = (Classes)session.load(Classes.class, 3);
			Set<Student> students = classes.getStudent();
			
			Student student = (Student)session.load(Student.class, 7);
			
			students.remove(student);
			
			classes.setStudent(students);
			session.save(classes);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Util.closeSession(session);
		}
	}
	
}
