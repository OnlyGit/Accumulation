package com.test.mybatis.inter;

import com.test.mybatis.model.Student;

public interface IStudentInterface {

	/**
	 * 方法名与Student.xml中的查询语句的id相同
	 * @param id
	 * @return
	 */
	public Student selectStudentByID(int id);
}
