package com.sa.offer;

import java.io.ObjectInputStream.GetField;

/**
 * 不用加减乘除做加法
 * @author qjf
 * 
 * 17 + 7
 * 17 00010001
 * 7  00000111
 * 22 00010110 17^7
 * 1  00000001 17&7
 * 2  00000010 1 << 1
 * 
 * 22 + 2
 * 22 00010110
 * 2  00000010
 * 20 00010100 22^2
 * 2  00000010 22&2
 * 4  00000100 2 << 1
 * 
 * 20 + 4
 * 20 00010100
 * 4  00000100
 * 16 00010000 20^4
 * 4  00000100 20&4
 * 8  00001000 4 << 1
 * 
 * 16 + 8
 * 16 00010000
 * 8  00001000
 * 24 00011000 16^8
 */
public class FortySeven {

	public static void main(String[] args) {
		System.out.println(getResult(17,5));
	}
	
	private static int getResult(int a, int b) {
		int yh = 0;
		int y = 0;
		
		do {
			yh = a ^ b;
			y = a & b;
			y = y << 1;
			
			a = yh;
			b = y;
		} while((y) != 0);
		
		return yh;
	}
}
