package com.sa.offer;

/**
 * 将数组中的奇数放在偶数前面
 * @author qjf
 */
public class FourTheen {

	public static void main(String[] args) {
		int[] arr = {2,5,3,6,8,4,7,9};
		
		sort(arr);
		for(int a : arr) {
			System.out.println(a);
		}
	}

	private static void sort(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		int begin = 0;
		int end = arr.length - 1;
		int tmp = 0;
		while(begin < end) {
			if(arr[begin] % 2 == 0 && arr[end] % 2 == 1) {
				tmp = arr[begin];
				arr[begin] = arr[end];
				arr[end] = tmp;
			}
			if(arr[begin] % 2 == 1) {
				begin++;
			}
			
			if(arr[end] % 2 == 0) {
				end--;
			}
		}
	}
}
