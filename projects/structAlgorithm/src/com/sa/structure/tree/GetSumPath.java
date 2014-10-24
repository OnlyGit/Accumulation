package com.sa.structure.tree;

import java.util.Stack;

import com.sa.structure.tree.TraverseTree.Node;

/**
 * 二叉树中和为某一值的路径
 * @author QJF
 */
public class GetSumPath {
	private static int sum = 0;

	public static void main(String[] args) {
		int[] arr = { 10, 5, 12, 4, 7,11};
		TraverseTree bTree = new TraverseTree();

		// 构建一棵二叉树
		for (int i = 0; i < arr.length; i++) {
			bTree.buildTree(bTree.getRoot(), arr[i]);
        }  
		Stack<Integer> stack = new Stack<Integer>();
		getPath(bTree.getRoot(), stack, 22);
	}
	
	private static void getPath(Node node, Stack<Integer> stack, int target) {
		
		int val = node.getVal();
		stack.push(val);
		sum += val;
		
		if(sum == target) {
			for(int value : stack) {
				System.out.print(value+" ");
			}
			System.out.println();
		}
		
		if(node.getLeft() != null) {
			getPath(node.getLeft(), stack, target);
		}
		
		if(node.getRight() != null) {
			getPath(node.getRight(), stack, target);
		}
		stack.pop();
		sum -= val;
	}
}
