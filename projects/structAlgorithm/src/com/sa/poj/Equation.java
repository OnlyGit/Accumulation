package com.sa.poj;

/**
 * poj1840
 * 等式a1*x1*x1*x1+ a2*x2*x2*x2+ a3*x3*x3*x3+ a4*x4*x4*x4+ a5*x5*x5*x5=0
 * 的解的个数
 * 其中x1∈[-50,50].  
 * 利用hash表求等式
 * 
 * @author QJF
 */
public class Equation {

	public static void main(String[] args) {
		int max = 31250005*2;
		short[] hash = new short[max];
		long sum = 0;
		int a1 = 37,a2 = 29,a3 = 41,a4 = 43,a5 = 47;
		int count = 0;
		for(int i = -50; i <= 50; i++) {
			if(i == 0) {
				continue;
			}
			for(int j = -50; j <= 50; j++) {
				if(j == 0) {
					continue;
				}
				for(int n = -50; n <= 50; n ++) {
					if(n == 0) {
						continue;
					}
					sum = a1*i*i*i+a2*j*j*j+a3*n*n*n;
					if(sum < 0) {
						sum += max;
					}
					hash[(int)sum]++;
				}
			}
		}
		
		sum = 0;
		for(int i = -50; i <= 50; i++) {
			if(i == 0) {
				continue;
			}
			for(int j = -50; j <= 50; j++) {
				if(j == 0) {
					continue;
				}
				sum = a4*i*i*i+a5*j*j*j;
				if(sum < 0) {
					sum += max;
				}
				count += hash[(int)sum];
			}
		}
		
		System.out.println(count);
	}
}
