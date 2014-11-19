package com.joe.config.manager.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileUtils {

	/*
	 * 追加内容
	 */
	public static void writeContent(String path,String content) throws Exception{
		File file = new File(path);
		if(!file.exists()) {
			file.createNewFile();
		}
		/*
		 * BufferedWriter bw = new BufferedWriter(new FileWriter(file)); 
		 * 用这个方法时，每次写入内容都是覆盖，下面的方法为追加
		 */
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		bw.newLine();
		bw.write(content);
		bw.close();
	}
}
