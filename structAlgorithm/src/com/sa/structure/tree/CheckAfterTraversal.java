package com.sa.structure.tree;

/**
 * 检查输入序列是否是某一个二叉查找树的后续遍历
 * @author QJF
 */
public class CheckAfterTraversal {

	public static void main(String[] args) {
		int[] arr = {5,10,7,6,9,11,8};
		System.out.println(check(arr, 0, 6));;
	}
	
	private static boolean check(int[] arr, int begin, int end) {
		if(end == begin) {
			return true;
		}
		boolean flag = false;
		int root = arr[end];
		int rightBegin = begin;
		int leftEnd = 0;
		while(arr[rightBegin] < root) {
			rightBegin++;
		}
		leftEnd = rightBegin;
		
		while(arr[rightBegin] > root) {
			rightBegin++;
		}
		flag = rightBegin == end;
		if(flag) {
			flag = check(arr, begin, leftEnd - 1) && check(arr, leftEnd, rightBegin - 1);
		}
		return flag;
	}
}