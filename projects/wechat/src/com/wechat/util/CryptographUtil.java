package com.wechat.util;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 密文工具类
 * @author CL-XIE
 */
public class CryptographUtil {

	private String encodingAESKey = ConfigurationUtil.getConfig("encodingAESKey");//消息加密密钥
	
	/*-----------Base64 加密解密------------*/
	/*public static String getBase64(String str) {
		byte[] b = null;
		String result = "";
		try {
			b = str.getBytes("utf-8");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(b != null) {
			//使用时要将 jre改为alternate jre (http://fableking.iteye.com/blog/1426410)
			result = new BASE64Encoder().encode(b);
		}
		return result;
	}*/
	
	/*public static String getFromBase64(String str) {
		byte[] b = null;
		String result = "";
		if(str != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(str);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}*/
	
	/*-----------AES 加密解密------------*/
	public static byte[] encrypt(String content,String password) {
		byte[] result = null;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			result = cipher.doFinal(byteContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static byte[] decrypt(String content,byte[] password) {
		byte[] result = null;
		try {
			/*KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			result = cipher.doFinal(content.getBytes());*/
			
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec key_spec = new SecretKeySpec(password, "AES");
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(password, 0, 16));
			cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

			// 使用BASE64对密文进行解码
			byte[] encrypted = Base64.decodeBase64(content);
			
			result = cipher.doFinal(encrypted);
			
			System.out.println(new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		String ss = "TsRdx7OdAxe1QNWk6yEh4FWXKzClfMdFmMAxII7HZV+tgX+/vHYN+QvA/GIGz+TaLMaMEjKW4G0qoQ7gTP9vNId08ZApzpR5iy1hHJ6ToJ4pyDqtJv/OGjzewwD0GTlenCNnktrJPrgUkJRbFtb+PTggs2lOxJSHt8ilo5Kz/HR3EF/uF1+r3Ibvym8KkcsWAHfwAPPY2rU4HCrCuJ8Pr8SsE0DUlA3atpT1AiZxSXGbfUdY4+Pgbu6N2xXu8++qBGfP3Iuaatxx2VYFZC4sp28H4IByywD3aRImKyAU0Kk2pEmbYtN2xSt1vC7mHA+Bca0VAacnzfeRSzkQ6DjvAtxRMhYr958WNJqOGSi6QG9YSlYwMsncgH1dxI98QhVJ9NzjJF1/4t315I9YYZIJiJwuDlOHP+cOiew9CG/0RzQ=";
		
		String EncodingAESKey = "o2uMw4mCMwaHkfZNfLHpsxLaNbf2UhFtIA0qeRbvcsf";
//		String password = getFromBase64(EncodingAESKey + "=");
//		System.out.println("aesKey = "+password);
		
		byte[] aseKey = Base64.decodeBase64(EncodingAESKey + "=");
		System.out.println(new String(aseKey));
		
		System.out.println(decrypt(ss, aseKey));
	}
}
