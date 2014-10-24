package com.sa.offer;

import java.util.Arrays;

/**
 * 字符串中的空格替换为   %20
 * @author qjf
 *
 */
public class Four {

	public static void main(String[] args) {
		test("hello world !!");
		test("2");
		test(null);
		test("");
	}
	
	private static void test(String ori) {
		if(ori == null || ori.length() == 0) {
			return;
		}
		int newStrLength = getNewStrLength(ori);
		if(newStrLength == ori.length())
			return;
		
		char[] arr = ori.toCharArray();
		char[] newArr = Arrays.copyOf(arr, newStrLength);
		
		int index1 = arr.length - 1;//指向远数组最后一个元素
		int index2 = newStrLength - 1;//指向新数组最后一个元素
		
		while(index2 > index1 && index1 >= 0) {
			if(newArr[index1] == ' ') {
				newArr[index2--] = '0';
				newArr[index2--] = '2';
				newArr[index2--] = '%';
			} else {
				newArr[index2--] = newArr[index1];
			}
			index1--;
		}
		
		System.out.println(newArr);
	}
	
	/**
	 * 获取新字符数组的长度
	 * @param str
	 * @return
	 */
	private static int getNewStrLength(String str) {
		char[] arr = str.toCharArray();
		int count = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == ' ') {
				count++;
			}
		}
		return count*2+str.length();
	}
	
}
