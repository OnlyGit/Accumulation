package com.sa.structure.tree;

import com.sa.structure.tree.TraverseTree.Node;

/**
 * 求一个二叉树的镜像
 * @author QJF
 */
public class MirrorOfBinaryTree {

	public static void main(String[] args) {
		int[] arr = {8,6,10,5,7,9,11};
		TraverseTree bTree = new TraverseTree();

		// 构建一棵二叉树
		for (int i = 0; i < arr.length; i++) {
			bTree.buildTree(bTree.getRoot(), arr[i]);
        }
		
		bTree.preOrderTraverse(bTree.getRoot());
		
		exchange(bTree.getRoot());
		System.out.println();
		bTree.preOrderTraverse(bTree.getRoot());
		System.out.println();
		bTree.inOrderTraverse(bTree.getRoot());
		System.out.println();
		bTree.postOrderTraverse(bTree.getRoot());
	}
	
	private static void exchange(Node node) {
		
		if(node == null) {
			return;
		}
		
		if(node.getLeft() != null) {
			exchange(node.getLeft());
		}
		
		if(node.getRight() != null) {
			exchange(node.getRight());
		}
		
		Node tem = node.getLeft();
		node.setLeft(node.getRight());
		node.setRight(tem);
		
	}
}
