package com.poj;

public class RecoverTree {

	/**
	 * ��ǰ���л�ȡ������
	 * @param preOrder
	 * @param length ���������ȣ���getLeftTreeFromIn��ȡ
	 * @return
	 */
	private static String[] getLeftTreeFromPre(String[] preOrder, int length) {
		String[] arr = new String[length];
		for(int i = 0; i < length; i++) {
			arr[i] = preOrder[i+1];
			System.out.println(preOrder[i+1]);
		}
		System.out.println("------");
		return arr;
	}
	
	/**
	 * �������еõ�������
	 * @param root
	 * @param inOrder
	 * @return
	 */
	private static String[] getLeftTreeFromIn(String root, String[] inOrder) {
		int index = 0;
		for(String val : inOrder) {
			if(!val.equals(root)) {
				index++;
			} else {
				break;
			}
		}
		String[] arr = new String[index];
		for(int i = 0; i < index; i++) {
			arr[i] = inOrder[i];
			System.out.println(inOrder[i]);
		}
		System.out.println("------");
		return arr;
	}
	
	/**
	 * ��ǰ���л�ȡ������
	 * @param preOrder
	 * @param length ���������ȣ���getRightTreeFromIn�л�ȡ
	 * @return
	 */
	private static String[] getRightTreeFromPre(String[] preOrder, int length) {
		String[] arr = new String[length];
		for(int i = 0; i < length; i++) {
			arr[i] = preOrder[preOrder.length - length + i];
			System.out.println(preOrder[preOrder.length - length + i]);
		}
		System.out.println("------");
		return arr;
	}
	/**
	 * �������л�ȡ������
	 * @param root
	 * @param inOrder
	 * @return
	 */
	private static String[] getRightTreeFromIn(String root, String[] inOrder) {
		int index = 0;
		int length = inOrder.length;
		for(String val : inOrder) {
			index++;
			if(val.equals(root)) {
				break;
			}
		}
		String[] arr = new String[length - index];
		for(int i = 0; i < length - index; i++) {
			arr[i] = inOrder[index + i];
			System.out.println(inOrder[index+i]);
		}
		System.out.println("-------");
		return arr;
	}
	
	private static Node getRoot(String[] pre, String[] in) {
		String val = pre.length == 0 ? null : pre[0];
		if(val == null) {
			return null;
		}
		Node node = new Node(val);
		
		String[] inLeft = getLeftTreeFromIn(node.val,in);
		String[] preLeft = getLeftTreeFromPre(pre,inLeft.length);
		Node leftNode = getRoot(preLeft, inLeft);
		
		String[] inRight = getRightTreeFromIn(node.val, in);
		String[] preRight = getRightTreeFromPre(pre, inRight.length);
		Node rightNode = getRoot(preRight,inRight);
		
		node.setLeft(leftNode);
		node.setRight(rightNode);
		return node;
	}
	
	public static void main(String[] args) {
//		String[] pre = {"D","B","A","C","E","G","F"};
//		String[] in = {"A","B","C","D","E","F","G"};
		
		String[] pre = {"B","C","A","D"};
		String[] in = {"C","B","A","D"};
		Node root = getRoot(pre, in);
		
		BinaryTree tree = new BinaryTree();
		tree.setRoot(root);
		
		tree.postOrderTraversel(root);
	}
	
}
