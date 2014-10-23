package com.sa.offer;

/**
 * 从1打印到最大的n位数
 * 
 * 递归方法打印n位数组的排列组合
 * @author qjf
 */
public class Twelve {

	public static void main(String[] args) {
		test(2);
	}

	private static void test(int n) {
		char[] arr = new char[n];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = '0';
		}
		
		for(int j = 0; j < 10; j++) {
			arr[0] = (char) (j+'0');
			test1(arr,n,0);
		}
	}

	private static void test1(char[] arr, int length, int index) {
		if(index == length - 1) {
			printNum(arr);
			return;
		}
		
		for(int i = 0; i < 10; i++) {
//			arr[index + 1] = (char) i;//设置内容不是i所代表的int值
			arr[index + 1] = (char) (i+'0');//正确的将int转换为char
			test1(arr,length,index+1);
		}
	}
	
	private static void printNum(char[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == '0' && i < arr.length - 1) {
				continue;
			}
			System.out.print(arr[i]);
		}
		System.out.println();
	}
}
