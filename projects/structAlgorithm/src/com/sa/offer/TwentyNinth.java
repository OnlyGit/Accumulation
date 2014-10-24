package com.sa.offer;

/**
 * 数组中出现次数超过一半的数字
 * 
 * 算法：该数字出现的次数大于等于其他所有数组出现的次数之和
 * @author qjf
 */
public class TwentyNinth {

	public static void main(String[] args) {
		int[] arr = {2,1,5,2,4,6,5,5,5,5};
		
		int curr = arr[0];
		int times = 1;
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] == curr) {
				times++;
				continue;
			} else {
				curr = arr[i];
				if(times == 0) {
					times = 1;
				} else {
					times--;
				}
			}
		}
		System.out.println(curr);
	}
}
