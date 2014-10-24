package com.upload.map.action;

import java.io.File;

public class UpFile {

	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	public File getUpload() {
		return upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
}
