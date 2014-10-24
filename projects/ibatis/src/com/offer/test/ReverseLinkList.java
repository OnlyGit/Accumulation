package com.offer.test;

public class ReverseLinkList {
	private Node head;
	private int length;
	
	public ReverseLinkList() {
		
	}
	
	public ReverseLinkList(Node head) {
		this.head = head;
	}
	
	public void add(int value) {
		Node newNode = new Node(value);
		if(this.head == null) {
			this.head = newNode;
			length++;
			return;
		}
		
		Node node = head;
		
		while(node.getNext() != null) {
			node = node.getNext();
		}
		
		node.setNext(newNode);
		length++;
	}
	
	/**
	 * 反转链表
	 */
	public void reverse() {
		if(length <= 1) {
			return;
		}
		
		Node pre = null;
		Node next = null;
		Node node = head;
		
		while(node != null) {
			next = node.next;
			if(next == null) {
				head = node;
			}
			node.next = pre;
			pre = node;
			node = next;
		}
	}
	

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if(head == null) {
			return "";
		}
		sb.append(head.value);
		Node node = head.next;
		while(node != null) {
			sb.append(node.value);
			node = node.next;
		}
		return sb.toString();
	}

	private class Node {
		private int value;
		private Node next;
		
		public Node(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
	}
}
