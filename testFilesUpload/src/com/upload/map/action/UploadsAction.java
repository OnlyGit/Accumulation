package com.upload.map.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.opensymphony.xwork2.ActionSupport;


@Namespace("/NSM")
public class UploadsAction extends ActionSupport {

	private Map<String, String> uploads;
	private Map<String, UpFile> files;

	//在添加get方法之前，前台传回的参数，被设置到uploads中，但里面只有一个entity，就是最后一个参数。
	public Map<String, String> getUploads() {
		return uploads;
	}

	public void setUploads(Map<String, String> uploads) {
		this.uploads = uploads;
	}

	public Map<String, UpFile> getFiles() {
		return files;
	}

	public void setFiles(Map<String, UpFile> files) {
		this.files = files;
	}

	@Action("mapUpload")//map中的属性为一般对象
	public void mapUpload() {
		System.out.println("123");
	}
	
	@Action("mapEntityUpload")//map中的value为实体类
	public void upload() {
		System.out.println("234");
	}

}
