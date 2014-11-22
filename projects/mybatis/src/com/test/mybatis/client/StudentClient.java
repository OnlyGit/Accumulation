package com.test.mybatis.client;

import org.apache.ibatis.session.SqlSession;

import com.test.mybatis.inter.IStudentInterface;
import com.test.mybatis.model.Student;
import com.test.mybatis.utils.MyBatisUtils;

public class StudentClient {

	public static void main(String[] args) {
		typeTwo();
	}
	
	//
	public static void typeOne() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		
		Student student = (Student)session.selectOne("com.test.mybatis.model.Student.selectStudentByID",1);
		
		System.out.println(student.getStudentName());
		
		session.close();
	}
	
	/**
	 * 以接口方式调用
	 */
	public static void typeTwo() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		IStudentInterface stuInter = session.getMapper(IStudentInterface.class);
		Student student = stuInter.selectStudentByID(1);
		System.out.println(student.getStudentNo());
		session.close();
	}
}
