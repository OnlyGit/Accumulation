package com.sa.poj;

import java.util.Stack;

/**
 * S		    (((()()())))
 * P-sequence	    4 5 6666
 * W-sequence	    1 1 1456
 * 
 * P-sequence说明：4表示当前位置的右括号前面的左括号个数
 * W-sequence说明：1表示从当前右括号对应的左括号数起的右括号个数
 * 
 * 问：根据P-sequence求W-sequence
 * @author QJF
 */
public class Parencodings {

	public static void main(String[] args) {
		//左括号用0，有括号用1
		int[] pSeq = {4,5,6,6,6,6};
//		int[] pSeq = {4,6, 6, 6, 6, 8, 9, 9, 9};
		
		int[] sSeq = new int[pSeq.length * 2];
		
		int leftCount = 0;
		int index = 0;
		for(int i = 0; i < pSeq.length; i++) {
			
			while(leftCount < pSeq[i]) {
				sSeq[index++] = 0;
				leftCount++;
			}
			sSeq[index++] = 1;
		}
		
		for(int a : sSeq) {
			System.out.print(a);
		}
		System.out.println();
		
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i < sSeq.length; i++) {
			if(sSeq[i] == 0) {
				stack.push(i);
			}
			if(sSeq[i] == 1) {
				int leftPos = stack.peek();//对应左括号的位置
				int rightPos = i;//有括号当前位置
				System.out.print((rightPos-leftPos)/2 + 1);
				stack.pop();
			}
		}
	}
}
