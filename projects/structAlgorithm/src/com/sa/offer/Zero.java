package com.sa.offer;

/**
 * 1,2,5组成10的组合
 * @author qjf
 */
public class Zero {

	public static void main(String[] args) {
		for(int i = 0; i <= 10; i++) {
			for(int j = 0; j <= 10; j++) {
				for(int x = 0; x <= 10; x++) {
					if(1*i+2*j+5*x==10) {
						System.out.println("("+i+","+j+","+x+")");
					}
				}
			}
		}
	}
}
