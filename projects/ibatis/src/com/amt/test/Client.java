package com.amt.test;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class Client {
	
	private static SqlMapClient sqlMapClient = null;

	static {
		try {
			Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean addStudent(Student student) {
		Object object = null;
		boolean flag = false;
		try {
			object = sqlMapClient.insert("student.addStudent", student);
			System.out.println("添加学生信息的返回值：" + object);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}

	public boolean deleteStudentById(int id) {
		boolean flag = false;
		Object object = null;
		try {
			object = sqlMapClient.delete("student.deleteStudentById", id);
			System.out.println("删除学生信息的返回值：" + object + "，这里返回的是影响的行数");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}

	public boolean updateStudent(Student student) {
		boolean flag = false;
		Object object = false;
		try {
			object = sqlMapClient.update("student.updateStudent", 1);
			System.out.println("更新学生信息的返回值：" + object + "，返回影响的行数");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}

	public List<Student> selectAllStudent() {
		List<Student> students = null;
		try {
			students = sqlMapClient.queryForList("student.selectAllStudent");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public List<Student> selectStudentByName(String name) {
		List<Student> students = null;
		try {
			students = sqlMapClient.queryForList("student.selectStudentByName", name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public Student selectStudentById(int id) {
		Student student = null;
		try {
			student = (Student) sqlMapClient.queryForObject("student.selectStudentById", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	public Student getStudentByName(String name) {
		Student student = null;
		student = new Student();
//		student.setStudentId(10);
		student.setStudentName(name);
		try {
			student = (Student)sqlMapClient.queryForObject("student.getStudentByName",student);
			System.out.println(student.getStudentId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	public List<Student> dgnamicStudent(int id) {
		List<Student> students = null;
		try {
			students = sqlMapClient.queryForList("student.dynamicSql",id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	/**
	 * 事务管理测试
	 */
	private static void testTransaction() {
		Student student = new Student();
		student.setStudentName("909");
		try {
			sqlMapClient.startTransaction();
			Object object = false;
			object = sqlMapClient.update("student.updateStudent", 3);
			object = sqlMapClient.update("student.updateStudent", 6);
			
			sqlMapClient.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		/**/List<Student> students = client.selectAllStudent();
		for(Student student : students) {
			System.out.println(student.getStudentName());
		}
		System.out.println("------主鍵查询--------");
		Student student = client.selectStudentById(10);
		System.out.println(student.getStudentName());
		System.out.println("------模糊查询--------");
		students = client.selectStudentByName("1");
		for(Student stu : students) {
			System.out.println(stu.getStudentName());
			System.out.println(student.getClasses().getClassName());
		}
		System.out.println("------bean注入参数--------");
		client.getStudentByName("%7%");
		
		System.out.println("------动态sql--------");
		students = client.dgnamicStudent(1);
		for(Student stu : students) {
			System.out.println(stu.getStudentName());
		}
//		testTransaction();
		
		/*Student student = new Student();
		student.setStudentName("cff");
		Classes classes = new Classes();
		classes.setClassId(1);
		student.setClasses(classes);
		client.addStudent(student);*/
		
		/*Student student = new Student();
		student.setStudentName("cff");
		Classes classes = new Classes();
		classes.setClassId(1);
		student.setClasses(classes);
		client.updateStudent(student);*/
	}
}
