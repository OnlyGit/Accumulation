package com.basic.struct.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * ����������
 * ������
 * @author qjf
 */
public class TraverseTree {

	private Node root;
	
	public Node getRoot() {
		return root;
	}

	//��ʼ��
	public void buildTree(Node node,int val) {
		Node newNode = new Node(val);
		if(root == null) {
			root = newNode;
		} else {
			if(val < node.val) {
				if(node.left == null) {
					node.setLeft(newNode);
				} else {
					buildTree(node.getLeft(), val);
				}
			} else {
				if(node.right == null) {
					node.setRight(newNode);
				} else {
					buildTree(node.getRight(), val);
				}
			}
		}
	}
	
	/**
	 * ǰ�����---�ݹ�
	 */
	public void preOrderTraverse(Node node) {
		if(null != node) {
			System.out.print(node.getVal());
			preOrderTraverse(node.getLeft());
			preOrderTraverse(node.getRight());
		}
	}
	/**
	 * �������---�ݹ�
	 */
	public void inOrderTraverse(Node node) {
		if(null != node) {
			inOrderTraverse(node.getLeft());
			System.out.print(node.getVal());
			inOrderTraverse(node.getRight());
		}
	}
	/**
	 * �������---�ݹ�
	 */
	public void postOrderTraverse(Node node) {
		if(null != node) {
			postOrderTraverse(node.getLeft());
			postOrderTraverse(node.getRight());
			System.out.print(node.getVal());
		}
	}
	/**
	 * ǰ�����---�ǵݹ�
	 */
	public void preOrderTraverse1(Node node) {
		Stack<Node> stack = new Stack<Node>();
		if(node != null) {
			stack.push(node);
			while(!stack.isEmpty()) {
				node = stack.pop();
				System.out.print(node.getVal());
				if(node.getRight() != null) {
					stack.push(node.getRight());
				}
				if(node.getLeft() != null) {
					stack.push(node.getLeft());
				}
			}
		}
	}
	
	/**
	 * �������---�ǵݹ�
	 */
	public void inOrderTraversel(Node node) {
		Stack<Node> stack = new Stack<Node>();
		while ((node != null) || (!stack.isEmpty())) {  
            if (node != null) {  
                stack.push(node);  
                node = node.getLeft();  
            } else {  
                node = stack.pop();  
                System.out.print(node.getVal());  
                node = node.getRight();  
            }  
        } 
	}
	
	/**
	 * �������---�ǵݹ�
	 */
	public void postOrderTraversel(Node node) {
		Stack<Node> stack = new Stack<Node>();  
        Node preNode = null;  
        if (node != null) {  
            stack.push(node);  
            while (!stack.isEmpty()) {  
                node = stack.peek();  
                if ((node.getLeft() == null && node.getRight() == null)   
                        || (preNode != null && (preNode == node.getLeft()|| preNode == node.getRight()))) {  
                    System.out.print(" " + node.val + " ");  
                    stack.pop();  
                    preNode = node;  
                } else {  
                    if (node.getRight() != null)  
                        stack.push(node.getRight());  
                    if (node.getLeft() != null)  
                        stack.push(node.getLeft());  
                }  
            }  
        }  
	}
	/**
	 * ��α���
	 */
	public void levelOrderTraverse(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		if(node != null) {
			queue.offer(node);
			while(!queue.isEmpty()) {
				node = queue.poll();
				System.out.print(node.getVal());
				if(node.getLeft() != null) {
					queue.offer(node.getLeft());
				}
				if(node.getRight() != null) {
					queue.offer(node.getRight());
				}
			}
		}
	}
	
	class Node {
		private Node left;
		private Node right;
		private int val;
		
		public Node(int val) {
			this.val = val;
		}
		
		public Node getLeft() {
			return left;
		}
		public void setLeft(Node left) {
			this.left = left;
		}
		public Node getRight() {
			return right;
		}
		public void setRight(Node right) {
			this.right = right;
		}
		public int getVal() {
			return val;
		}
		public void setVal(int val) {
			this.val = val;
		}
	}
	/**
	 * 			4
	 * 		2		5
	 * 1	3			6
	 */
}
