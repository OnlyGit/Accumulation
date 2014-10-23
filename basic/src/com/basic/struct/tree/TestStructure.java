package com.basic.struct.tree;

import java.util.Iterator;
import java.util.LinkedList;

import com.basic.struct.tree.TraverseTree.Node;

public class TestStructure {

	public static void main(String[] args) {
//		testTree1();
		getList();
	}
	
	
	private static void testLinearTable1() {
		LinearTable1 lt1 = new LinearTable1();
		
		lt1.add(0,"1");
		lt1.add(1,"2");
		lt1.add(2,"3");
		lt1.add(3,"4");
		lt1.add(4,"5");
		lt1.add("6");
		lt1.add("7");
		lt1.add("8");
		lt1.add("9");
		lt1.add("10");
		lt1.add("11");
		lt1.add("12");
		
		System.out.println(lt1.size());
		System.out.println(lt1.isEmpty());
		System.out.println(lt1);
		System.out.println(lt1.get(4));
		System.out.println(lt1.remove(14));
		System.out.println(lt1);
	}
	
	private static void testLinearTable2() {
		LinearTable2<String> lt2 = new LinearTable2<String>();
		lt2.add("1");
		lt2.add("2");
		lt2.add("3");
		lt2.add("4");
		lt2.add("5");
		lt2.add("6");
		lt2.add("7");
		
		System.out.println(lt2.toString());
		lt2.remove(3);
		System.out.println(lt2.toString());
		lt2.insert(4, "8");
		System.out.println(lt2.toString());
	}
	
	private static void testLinearTable3() {
		LinearTable3<String> lt3 = new LinearTable3<String>();
		lt3.addBeforeHead("5");
		lt3.addBeforeHead("4");
		lt3.addBeforeHead("3");
		lt3.addBeforeHead("2");
		lt3.addBeforeHead("1");
		
//		lt3.addAfterHead("1.5");
		
		lt3.insert(4, "8");
		
		lt3.remove(5);
		System.out.println(lt3);
	}
	
	private static void testLinearTable4() {
		LinearTable4<String> lt4 = new LinearTable4<String>();
		
		
		lt4.add("3");
		lt4.add("4");
		lt4.add("5");
		lt4.add("6");
		lt4.add("7");
		lt4.add("8");
		lt4.add("9");
		
		lt4.insert(1, "1");
		lt4.insert(2, "2");
		
		System.out.println(lt4);
		
		lt4.remove(9);
		
		System.out.println(lt4);
	}
	
	private static void testLinearTable5() {
		LinearTable5 lt = new LinearTable5();
		lt.add("1");
		lt.add("2");
		lt.add("3");
		lt.add("4");
		lt.add("5");
		lt.add("6");
		lt.add("7");
		lt.add("8");
		lt.add("9");
		lt.add("10");
		lt.add("6");
		System.out.println(lt.size());
		lt.remove();
		lt.remove();
		System.out.println(lt.size());
		
		System.out.println(lt);
	}
	
	private static void testTree1() {
		int[] arr = { 6, 4, 8, 1, 7, 3, 9, 2, 5 };
		TraverseTree bTree = new TraverseTree();

		// 构建一棵二叉树
		for (int i = 0; i < arr.length; i++) {
			bTree.buildTree(bTree.getRoot(), arr[i]);
        }  
		bTree.preOrderTraverse(bTree.getRoot());
		System.out.println();
		bTree.inOrderTraverse(bTree.getRoot());
		System.out.println();
		bTree.postOrderTraverse(bTree.getRoot());
		System.out.println();
		bTree.preOrderTraverse1(bTree.getRoot());
		System.out.println();
		bTree.levelOrderTraverse(bTree.getRoot());
	}
	
	private static void testTree2() {
		Tree2 tree = new Tree2();
		
		tree.add(0, "1", true);
		tree.add(0, "2", false);
		tree.add(1, "3", true);
		
		System.out.println(tree);
	}
	/**
	 * 二叉树两个节点的距离
	 * 
	 * 两个节点到最低公共祖先的距离之和
	 */
	private static void getList() {
//		int[] arr = { 6, 4, 8, 1, 7, 3, 9, 2, 5 };
		int[] arr = { 4, 2, 5, 1, 3, 6 ,8};
		TraverseTree bTree = new TraverseTree();

		// 构建一棵二叉树
		for (int i = 0; i < arr.length; i++) {
			bTree.buildTree(bTree.getRoot(), arr[i]);
        }
		
		LinkedList<Node> list1 = new LinkedList<Node>();
		Node node = bTree.new Node(8);
		getList(bTree.getRoot(), node, list1);
		for(Iterator<Node> it = list1.iterator(); it.hasNext();) {
			System.out.println(it.next().getVal());
		}
		LinkedList<Node> list2 = new LinkedList<Node>();
		Node node2 = bTree.new Node(1);
		getList(bTree.getRoot(), node2, list2);
		for(Iterator<Node> it = list2.iterator(); it.hasNext();) {
			System.out.println(it.next().getVal());
		}
		System.out.println(getCommonNode(list1, list2));
	}
	/**
	 * 获取节点到根节点的路径，不包括自己
	 * @param root
	 * @param node
	 * @param list
	 * @return
	 */
	private static boolean getList(Node root,Node node,LinkedList<Node> list) {
		if(root.getVal() == node.getVal()) {//找到节点
			return true;
		}
		boolean flag = false;
		list.add(root);
		
		if(root.getLeft() != null && !flag) {
			flag = getList(root.getLeft(), node, list);
		}
		if(root.getRight() != null && !flag) {
			flag = getList(root.getRight(), node, list);
		}
		
		if(!flag) {
			list.removeLast();
		}
		return flag;
	}
	/**
	 * 获取两个链表的公共节点
	 * @param list1
	 * @param list2
	 * @return
	 */
	private static int getCommonNode(LinkedList<Node> list1, LinkedList<Node> list2) {
		int size1 = list1.size();
		int size2 = list2.size();
		
		int min = size1 < size2 ? size1 : size2;
		
		int pre = list1.get(0).getVal();
		
		for(int i = 0; i < min; i++) {
			if(list1.get(i).getVal() == list2.get(i).getVal()) {
				pre = list1.get(i).getVal();
			}else {
				break;
			}
		}
		return pre;
	}
}