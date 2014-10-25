/**
 * 
 */
package com.sa.poj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * poj 3349
 * 
 * 每片雪花六个瓣，给出序列(序列个数<100000)，求是否存在相同的两片雪花
 * 序列中的花瓣，可能是顺时针给出，也可能是逆时针给出
 * 
 * 如 123456 432165
 * 则这两个序列对应一个雪花
 * @author QJF
 */
public class Snowflake {

	private static Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
	public static void main(String[] args) {
		String a = "123456";
		String b = "432165";
		begin(a);
		begin(b);
	}

	/**
	 * 初始化hash表，并将key指相同的进行对比
	 * @param target
	 */
	private static void begin(String target) {
		int key = getHashKey(target);
		boolean flag = false;
		if(map.get(key) != null) {
			List<String> sequences = (List<String>) map.get(key);
			for(String sequence : sequences) {
				flag = check(sequence, target);
				if(flag) {
					System.out.println("存在！");
					return;
				}
			}
			map.get(key).add(target);
		} else {
			List<String> list = new ArrayList<String>();
			list.add(target);
			map.put(key, list);
		}
	}
	
	/**
	 * 用序列的和%4999(质数) 作为key
	 * @param sequence
	 * @return
	 */
	private static int getHashKey(String sequence) {
		char[] arr = sequence.toCharArray();
		long sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i] - '0';
		}
		return (int)sum % 4999;
	}
	
	/**
	 * 检查是否相同
	 * @param ori
	 * @param target
	 * @return
	 */
	private static boolean check(String ori, String target) {
		String all = ori + ori;
		boolean flag = false;
		
		flag = all.indexOf(target) != -1;
		if(!flag) {
			flag = all.indexOf(reverse(target)) != -1;
		}
		return flag;
	}
	
	/**
	 * 将字符串逆序
	 * @param ori
	 * @return
	 */
	private static String reverse(String ori) {
		char[] arr = ori.toCharArray();
		
		int begin = 0;
		int end = arr.length - 1;
		while(begin < end) {
			char tem = arr[begin];
			arr[begin] = arr[end];
			arr[end] = tem;
			
			begin++;
			end--;
		}
		
		return String.valueOf(arr);
	}
}
