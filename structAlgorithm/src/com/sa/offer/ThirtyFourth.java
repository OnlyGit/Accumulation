package com.sa.offer;

/**
 * 获取前n个丑数
 * @author qjf
 */
public class ThirtyFourth {

	public static void main(String[] args) {
		int n = 20;
		
		int index2 = 0;
		int index3 = 0;
		int index5 = 0;
		
		int[] results = new int[20];
		
		results[0] = 1;
		int index = 0;
		int min;
		while(index < n - 1) {
			min = getMin(results[index2] * 2, results[index3] * 3, results[index5] * 5);
			
			if(results[index2] * 2 == min) {
				index2++;
			}
			
			if(results[index3] * 3 == min) {
				index3++;
			}
			
			if(results[index5] * 5 == min) {
				index5++;
			}
			
			results[++index] = min;
		}
		
		for(int i : results) {
			System.out.println(i);
		}
	}
	
	private static int getMin(int first, int second, int third) {
		int min = first > second ? second : first;
		return min > third ? third : min;
	}
}
