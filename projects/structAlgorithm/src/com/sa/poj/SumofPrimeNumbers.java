package com.sa.poj;

/**
 * 连续素数的和
 * @author QJF
 * 2739
 */
public class SumofPrimeNumbers {

	public static void main(String[] args) {
		int[] arr = getPrimeArr();
		int target = 41;
		int begin = 0;
		int end = begin + 1;
		int sum = 0;
		
		
		while(begin < end) {
			for(int i = begin; i <= end; i++) {
				sum += arr[i];
			}
			if(sum < target) {
				end++;
			} else if(sum > target) {
				begin++;
			} else {
				System.out.println(begin+"-"+end);
				begin++;
			}
			sum = 0;
		}
	}

	/**
	 * 获取n以内的所有素数
	 * 素数筛法：
	 * 筛法是：先把n个自然数按次序排列起来。1不是质数，也不是合数，要划去。第二个数2是质数留下来，
	 * 而把2后面所有能被2整除的数都划去。2后面第一个没划去的数是3，把3留下，再把3后面所有能被3整除的数都划去。
	 * 3后面第一个没划去的数是5，把5留下，再把5后面所有能被5整除的数都划去。这样一直做下去，就会把不超过N的全部合数都筛掉，
	 * 留下的就是不超过N的全部质数。因为希腊人是把数写在涂腊的板上，每要划去一个数，就在上面记以小点，寻求质数的工作完毕后，
	 * 这许多小点就像一个筛子，所以就把埃拉托斯特尼的方法叫做“埃拉托斯特尼筛法”，简称“筛法”。
	 * @return
	 */
	private static int[] getPrimeArr() {
		int[] count = new int[100];
		for(int i = 0; i < count.length; i++) {
			count[i] = i;
		}
		
		int zeroCount = 0;
		
		for(int i = 0; i < count.length; i++) {
			if(count[i] != 0 && count[i] != 1) {
				for(int j = 2*i; j < count.length; j += i) {
					count[j] = 0;
				}
			}
		}
		
		for(int a : count) {
			if(a > 1) {
				zeroCount++;
			}
		}
		int[] newArr = new int[zeroCount];
		int index = 0;
		for(int a : count) {
			if(a > 1) {
				newArr[index++] = a;
			}
		}
		
		for(int a : newArr) {
			if(a > 1) {
				System.out.print(a+" ");
			}
		}
		return newArr;
	}
}
