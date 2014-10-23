package com.sa.poj;

import java.util.HashSet;
import java.util.Set;

public class SpellChecker {
	
	private static Set<String> hashSet = new HashSet<String>();

	public static void main(String[] args) {
		String ori = "hello";
		String result = "h4l4o";
		
		
		char[] oriArr = ori.toCharArray();
		char[] resultArr = result.toCharArray();
		
		if(ori.length() == result.length()) {
			//替换
			for(int i = 0; i < oriArr.length; i++) {
				replace(result, oriArr[i], i);
			}
		}
		if(ori.length() - result.length() == -1) {
			//删除
			for(int i = 0; i < resultArr.length; i++) {
				delete(result, i);
			}
		}
		if(ori.length() - result.length() == 1) {
			//插入
			for(int i = 0; i < oriArr.length; i++) {
				insert(result, oriArr[i], i);
			}
		}
	}
	
	/**
	 * 替换一个字符
	 */
	private static void replace(String ori, char target, int index) {
		StringBuffer sb = new StringBuffer(ori.substring(0,index));
		sb.append(target);
		sb.append(ori.substring(index+1));
		System.out.println(sb);
	}
	
	/**
	 * 删除一个字符
	 */
	private static void delete(String ori, int index) {
		StringBuffer sb = new StringBuffer(ori.substring(0,index));
		sb.append(ori.substring(index+1));
		System.out.println(sb);
	}
	
	/**
	 * 插入一个字符
	 */
	private static String insert(String ori, char target, int index) {
		StringBuffer sb = new StringBuffer();
		char[] oriArr = ori.toCharArray();
		if(index == oriArr.length) {
			System.out.println(ori+target);
			return ori+target;
		}
		for(int i = 0; i < oriArr.length; i++) {
			if(i == index) {
				sb.append(target);
			}
			sb.append(oriArr[i]);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}
