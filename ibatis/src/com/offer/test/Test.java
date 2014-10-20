package com.offer.test;

import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		/*ReverseLinkList list = new ReverseLinkList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		System.out.println(list);
		
		list.reverse();
		System.out.println(list);*/
		
		System.out.println(testStack(new int[]{1,2,3,4,5},new int[]{4,3,5,2,1}));
		System.out.println(testStack(new int[]{1,2,3,4,5},new int[]{4,5,3,2,1}));
	}
	
	/**
	 * outOrder是否为inOrder的一个出栈序列
	 * @param inOrder
	 * @param outOrder
	 * @return
	 */
	private static boolean testStack(int[] inOrder, int[] outOrder) {
		Stack<Integer> stack = new Stack<Integer>();
		int index = 0;
		for(int i = 0; i < outOrder.length; i++) {
			int curr = outOrder[i];
			
			if(!stack.isEmpty() && stack.peek() == curr) {
				stack.pop();
			} else {
				for(int j = index; j < inOrder.length; j++) {
					stack.push(inOrder[j]);
					index = j + 1;
					if(inOrder[j] == curr) {
						stack.pop();
						break;
					}
				}
			}
		}
		if(!stack.isEmpty()) {
			return false;
		}
		return true;
	}
}
