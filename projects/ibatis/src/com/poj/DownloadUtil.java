package com.poj;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.RandomAccess;

/**
 * 断点下载
 * @author QJF
 *
 */
public class DownloadUtil {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost:8081/uploadTempFiles/KTSH.CTXB.2R02_guangdong_ota_full_0928.zip");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			long downloadSize = conn.getContentLength();
			System.out.println(downloadSize);

			File file = new File("E:/2.zip");
			if(!file.exists()) {
				file.createNewFile();
			}
			long length = file.length();
			FileOutputStream fs = new FileOutputStream(file);
			
			while(length < downloadSize) {
				// 设置断点续传的开始位置   
				// 设置 User-Agent   
				conn = (HttpURLConnection)url.openConnection();
				conn.setRequestProperty("User-Agent","NetFox");   
				conn.setRequestProperty("Range","bytes="+length+"-"+(length+102400)+"");   
				// 获得输入流   
				InputStream input = conn.getInputStream();
				byte[] buffer = new byte[102400];
				int byteread = 0;
				while ((byteread = input.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				length = file.length();
				conn.disconnect();
			}
			
			System.out.println(length);
			
            fs.close();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
