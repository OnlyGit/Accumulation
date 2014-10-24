package com.sa.structure.tree;

import com.sa.structure.tree.TraverseTree.Node;

/**
 * 判断一棵树是否是另一棵树的子树
 * @author QJF
 */
public class HasSubTree {

	public static void main(String[] args) {
		int[] arr = { 6, 4, 8, 1, 7, 3, 9, 2, 5 };
		TraverseTree bTree = new TraverseTree();

		// 构建一棵二叉树
		for (int i = 0; i < arr.length; i++) {
			bTree.buildTree(bTree.getRoot(), arr[i]);
        }  
		
		int[] arr1 = { 6, 4, 1, 7, 3, 9, 2, 5 };
		TraverseTree bTree1 = new TraverseTree();

		// 构建一棵二叉树
		for (int i = 0; i < arr1.length; i++) {
			bTree1.buildTree(bTree1.getRoot(), arr1[i]);
        }  
		
		System.out.println(check(bTree.getRoot(), bTree1.getRoot()));
	}

	private static boolean check(Node parentRoot, Node childRoot) {
		boolean flag = false;
		if(parentRoot != null && childRoot != null) {
			if(parentRoot.getVal() == childRoot.getVal()) {
				flag = hasSubTree(parentRoot, childRoot);
			}
			if(!flag) {
				check(parentRoot.getLeft(), childRoot);
			}
			if(!flag) {
				check(parentRoot.getRight(), childRoot);
			}
		}
		return flag;
	}
	
	private static boolean hasSubTree(Node parentNode, Node childNode) {
		if(childNode == null) {
			return true;
		}
		if(parentNode == null) {
			return false;
		}
		if(parentNode.getVal() != childNode.getVal()) {
			return false;
		}
		return hasSubTree(parentNode.getLeft(), childNode.getLeft()) &&
				hasSubTree(parentNode.getRight(), childNode.getRight());
	}
}
