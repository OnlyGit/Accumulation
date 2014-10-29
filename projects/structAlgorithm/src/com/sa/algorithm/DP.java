package com.sa.algorithm;

/**
 * 动态规划
 * @author QJF
 */
public class DP {

	public static void main(String[] args) {
		int[] yb = {1,3,5};
		System.out.println(getCount(9, yb));
	}
	
	/**
	 * yb为硬币组合
	 * 求从yb中选择的最少的硬币个数，相加等于i
	 * @param i
	 * @param yb
	 * @return
	 */
	private static int getCount(int i, int[] yb) {
		if(i == 0) {
			return 0;
		}
		if(i == 1) {
			return 1;
		}

		int count = 0;
		for(int j = 0; j < yb.length; j++) {
			if(yb[j] <= i) {
				int newCount = getCount(i - yb[j], yb) + 1;
				if(count != 0) {
					count = newCount < count ? newCount : count;
				} else {
					count = newCount;
				}
			}
		}
		return count;
	}
}
