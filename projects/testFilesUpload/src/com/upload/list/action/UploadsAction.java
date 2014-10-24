package com.upload.list.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/NSL")
public class UploadsAction extends ActionSupport {

	private List<String> list;
	private List<String> listContentType;	 //保存上传的文件类型
	private List<String> listFileName; 

	public void setList(List<String> list) {
		this.list = list;
	}
	
	public void setListContentType(List<String> listContentType) {
		this.listContentType = listContentType;
	}

	public void setListFileName(List<String> listFileName) {
		this.listFileName = listFileName;
	}

	@Action("listUpload")
	public void listUpload() {
		System.out.println("test List ");
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().println("<script> alert(123);window.history.back();</script>");
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
