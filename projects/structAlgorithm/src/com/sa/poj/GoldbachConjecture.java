package com.sa.poj;

/**
 * 将偶数拆分为两个素数的和
 * @author QJF
 * 偶数：even numbers
 */
public class GoldbachConjecture {

	public static void main(String[] args) {
		int a = 20;
		for(int i = 3; i < a/2; i++) {
			if(!checkPrimeNum(i) && !checkPrimeNum(a - i)) {
				System.out.println(i+"+"+(a-i));
			}
		}
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
