package com.sa.offer;

/**
 * 数组中连续数字的最大和
 * @author qjf
 */
public class ThirtyOneth {

	public static void main(String[] args) {
		int[] arr = {1,-3,-1,10,-4,7,2,-3};
		
		int max = arr[0];
		int index = 0;
		int sum = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			sum += arr[i];
			if(sum < arr[i]) {
				sum = max = arr[i];
				index = i;
			} else {
				if(sum > max) {
					max = sum;
				}
			}
			
		}
		System.out.println(index);
		System.out.println(max);
	}
}
