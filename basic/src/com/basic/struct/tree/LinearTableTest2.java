package com.basic.struct.tree;

public class LinearTableTest2<T> {

	private Node<T> head;
	private int size;
	
	public void add(T val) {
		Node<T> newNode = new Node<T>(val);
		if(isEmpty()) {
			this.head = newNode;
		} else {
			Node<T> tail = get(size);
			tail.setNext(newNode);
		}
		
		size++;
	}
	
	public void insert(int index, T val) {
		Node<T> newNode = new Node<T>(val);
		if(index == 0) {
			newNode.setNext(head);
			head = newNode;
		} else {
			Node<T> preNode = get(index - 1);
			newNode.setNext(preNode.getNext());
			preNode.setNext(newNode);
		}
		size++;
	}
	
	public void remove(int index) {
		if(index > size) {
			return;
		}
		if(index == 0) {
			Node<T> node = head;
			head = node.getNext();
			node = null;
		} else {
			Node<T> preNode = get(index - 1);
			Node<T> node = get(index);
			preNode.setNext(node.getNext());
			System.out.println("remove---"+node.getVal());
			node = null;
		}
		size--;
	}

	public Node<T> get(int index) {
		Node<T> node = null;
		if(index > 0 && index < (size + 1)) {
			node = head;
			for(int i = 1; i < index; i++) {
				node = node.getNext();
			}
		}
		return node;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder(head.getVal().toString()+",");
		Node<T> node = head;
		for(int i = 1; i < this.size; i++) {
			result.append(node.getNext().getVal());
			node = node.getNext();
		}
		result.deleteCharAt(result.lastIndexOf(","));
		return result.toString();
	}

	public boolean isEmpty() {
		return this.size == 0;
	}
	
	private class Node<E> {
		private Node<E> next;
		private E val;
		
		public Node(E val) {
			this.val = val;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public E getVal() {
			return val;
		}

		public void setVal(E val) {
			this.val = val;
		}
	}
}
