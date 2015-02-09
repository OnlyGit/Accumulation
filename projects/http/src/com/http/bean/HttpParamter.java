package com.http.bean;

/**
 * http 参数
 * @author qjf
 */
public class HttpParamter {

	private String name;
	private String value;
	
	private HttpParamter() {}
	
	public HttpParamter(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
