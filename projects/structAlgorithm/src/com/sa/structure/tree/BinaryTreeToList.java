package com.sa.structure.tree;

import java.util.Stack;

import com.sa.structure.tree.TraverseTree.Node;

/**
 * 将二叉查找树转换为排序的双向链表
 * 
 * 用中序遍历
 * @author QJF
 */
public class BinaryTreeToList {

	public static void main(String[] args) {
		int[] arr = {10,6,14,4,8,12,16};
		TraverseTree bTree = new TraverseTree();

		// 构建一棵二叉树
		for (int i = 0; i < arr.length; i++) {
			bTree.buildTree(bTree.getRoot(), arr[i]);
        }
	}
	
	private static void convert(Node node) {
		Stack<Node> stack = new Stack<Node>();
		stack.push(node);
		Node preNode = null;
		while(!stack.isEmpty()) {
			if(node != null) {
				stack.push(node);
				node = node.getLeft();
			} else {
				node = stack.pop();
				if(preNode != null) {
					preNode.setRight(node);
				}
				node.setLeft(preNode);
				
				preNode = node;
				
				System.out.print(node.getVal()+" ");
				node = node.getRight();
			}
		}
	}
}
