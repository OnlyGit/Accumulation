package com.basic.struct.tree;

//线性表----双向链表
public class LinearTable4<E> {
	private Node<E> head;
	private int size;
	
	public Node<E> get(int index) {
		Node<E> node = null;
		if(index > 0 && index <= size ) {
			node = head;
			for(int i = 1; i < index; i++) {
				node = node.getNext();
			}
		}
		return node;
	}
	
	public void add(E val) {
		Node<E> newNode = new Node<E>(val);
		if(isEmpty()) {
			head = newNode;
		} else {
			Node<E> tail = get(size);
			tail.setNext(newNode);
			newNode.setPre(tail);
		}
		size++;
	}
	
	public void insert(int index, E val) {
		if(index > 0 && index <= size + 1) {
			Node<E> newNode = new Node<E>(val);
			if(index == 1) {
				newNode.setNext(head);
				head.setPre(newNode);
				head = newNode;
			} else {
				Node<E> preNode = get(index - 1);
				Node<E> node = preNode.getNext();
				
				newNode.setPre(preNode);
				newNode.setNext(node);
				
				preNode.setNext(newNode);
				node.setPre(newNode);
			}
			size++;
		}
	}
	
	public void remove(int index) {
		if(index > 0 && index <= size) {
			if(index == 1) {
				head = null;
				head = head.getNext();
			} else if(index == size) {
				Node<E> preNode = get(index - 1);
				preNode.setNext(null);
			} else {
				Node<E> node = get(index);
				Node<E> preNode = node.getPre();
				Node<E> next = node.getNext();
				
				preNode.setNext(next);
				next.setPre(preNode);
				
				node = null;
			}
			size--;
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}

	public String toString() {
		StringBuilder result = new StringBuilder(head.getVal().toString()+",");
		Node<E> node = head;
		for(int i = 1; i < this.size; i++) {
			result.append(node.getNext().getVal());
			node = node.getNext();
		}
		result.deleteCharAt(result.lastIndexOf(","));
		return result.toString();
	}

	private class Node<T> {
		private Node<T> pre;
		private Node<T> next;
		private T val;
		
		public Node() {
			
		}
		
		public Node(T val) {
			this.val = val;
		}
		
		public Node(Node<T> pre, Node<T> next, T val) {
			this.pre = pre;
			this.next = next;
			this.val = val;
		}

		public Node<T> getPre() {
			return pre;
		}

		public void setPre(Node<T> pre) {
			this.pre = pre;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public T getVal() {
			return val;
		}

		public void setVal(T val) {
			this.val = val;
		}
	}
}