package com.test.mybatis.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.test.mybatis.inter.IStudentInterface;
import com.test.mybatis.model.Student;
import com.test.mybatis.utils.MyBatisUtils;

public class StudentClient {

	public static void main(String[] args) {
//		typeTwo();
		getStudentByNo();
	}
	
	//
	public static void typeOne() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		
		Student student = (Student)session.selectOne("com.test.mybatis.model.Student.selectStudentByID",1);
		
		System.out.println(student.getStudentName());
		
		session.close();
	}
	
	/**
	 * 以接口方式调用---根据id查找单个结果
	 * 以#方式传参
	 * 输出的sql为select * from student where student_id = ?
	 */
	public static void typeTwo() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		IStudentInterface stuInter = session.getMapper(IStudentInterface.class);
		Student student = stuInter.selectStudentByID(1);
		System.out.println(student.getStudentNo());
		session.close();
	}
	
	/**
	 * 以$方式传参
	 * 输出的sql为select * from student where student_id = 1
	 */
	public static void getSudentById() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		IStudentInterface stuInter = session.getMapper(IStudentInterface.class);
		Student student = stuInter.getStudentById(1);
		System.out.println(student.getStudentNo());
		session.close();
	}
	
	/**
	 * 根据学号获取信息
	 * #方式传参
	 * 输出sql:select * from student where student_no = ?
	 */
	public static void getStudentByNo() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		IStudentInterface stuInter = session.getMapper(IStudentInterface.class);
		Student student = stuInter.getStudentByNo("abc");
		System.out.println(student.getStudentNo());
		session.close();
	}
	
	/**
	 * $方式传参
	 * 输出sql:select * from student where student_no = 'abc' 
	 * 
	 * 如果实参的值为  abc,则输出select * from student where student_no = abc，报错 
	 */
	public static void getStudentByNo1() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		IStudentInterface stuInter = session.getMapper(IStudentInterface.class);
		Student student = stuInter.getStudentByNo1("'abc'");
		System.out.println(student.getStudentNo());
		session.close();
	}
	
	/**
	 * 获取结果集---array
	 */
	public static void getStudents() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		IStudentInterface stuInter = session.getMapper(IStudentInterface.class);
		int[] ids = {1,2,3,4};
		List<Student> list = (List<Student>)stuInter.getStudents(ids);
		for(Student stu : list) {
			System.out.println(stu.getStudentNo());
		}
		session.close();
	}
	
	/**
	 * 获取结果集---list
	 */
	public static void getStudents1() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		IStudentInterface stuInter = session.getMapper(IStudentInterface.class);
		List<String> ids = new ArrayList<String>();
		ids.add("1");
		ids.add("2");
		ids.add("3");
		List<Student> list = (List<Student>)stuInter.getStudents1(ids);
		for(Student stu : list) {
			System.out.println(stu.getStudentNo());
		}
		session.close();
	}
	
	/**
	 * 获取结果集----map
	 */
	public static void getStudents2() {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		IStudentInterface stuInter = session.getMapper(IStudentInterface.class);
		List<String> ids = new ArrayList<String>();
		ids.add("1");
		ids.add("2");
		ids.add("3");
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("ids", ids);
		List<Student> list = (List<Student>)stuInter.getStudents2(map);
		for(Student stu : list) {
			System.out.println(stu.getStudentNo());
		}
		session.close();
	}
}
