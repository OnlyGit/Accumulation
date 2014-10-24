package com.sa.offer;

/**
 * 数值的整数次方
 * @author qjf
 */
public class Elenve {

	public static void main(String[] args) {
		System.out.println(getReult(2, -2));
	}
	
	private static double getReult(double base, int exponent) {
		if(exponent < 0) {
			return 1 / power(base, exponent*-1);
		} else {
			return power(base, exponent);
		}
	}
	
	private static double power(double base, int exponent) {
		if(exponent == 0) {
			return 1;
		}
		if(exponent == 1) {
			return base;
		}
		
		double result = getReult(base, exponent >> 1);
		result *= result;
		
		if((exponent & 1) == 1) {
			result *= base;
		}
		
		return result;
	}
}
