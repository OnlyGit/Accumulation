package com.test.mybatis.inter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.mybatis.model.Student;

public interface IStudentInterface {

	/**
	 * 方法名与Student.xml中的查询语句的id相同
	 * #方式传入参数
	 * @param id
	 * @return
	 */
	public Student selectStudentByID(int id);
	
	/**
	 * 方法名与Student.xml中的查询语句的id相同
	 * $方式传入参数
	 * @param id
	 * @return
	 */
	public Student getStudentById(@Param("id")int id);
	
	/**
	 * 根据studentNo查找
	 * #方式传参
	 * @param no
	 * @return
	 */
	public Student getStudentByNo(String no);
	
	/**
	 * 根据studentNo查找
	 * $方式传参
	 * @param no
	 * @return
	 */
	public Student getStudentByNo1(@Param("no")String no);
	
	/**
	 * 得到结果集----array
	 * @return
	 */
	public List<Student> getStudents(int[] ids);
	
	/**
	 * 得到结果集----list
	 * @param list
	 * @return
	 */
	public List<Student> getStudents1(List<String> list);
	
	/**
	 * 获取结果集----map
	 * @param map
	 * @return
	 */
	public List<Student> getStudents2(Map<String,List<String>> map);
}
