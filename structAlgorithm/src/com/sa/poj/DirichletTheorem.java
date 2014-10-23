package com.sa.poj;

/**
 * 基数为a,增幅为b，
 * 得到的序列为a,a+b,a+2b,a+3b,a+4b....
 * 找出序列第n个素数
 * @author QJF
 * 
 * 素数：prime number
 * 
 */
public class DirichletTheorem {

	public static void main(String[] args) {
		test(269,58,102);
	}

	private static void test(int a, int b, int n) {
		int pos = 0;
		int num = 0;
		int index = 0;
		while(pos < n) {
			num = a + index * b;
			if(!checkPrimeNum(num)) {
				pos++;
			}
			index++;
		}
		System.out.println(num);
	}
	
	private static boolean checkPrimeNum(int n) {
		if(n <= 3) {
			return false;
		}
		for(int i = 2; i < n/2; i++) {
			if(n % i == 0) {
				return true;
			}
		}
		return false;
	}
}
