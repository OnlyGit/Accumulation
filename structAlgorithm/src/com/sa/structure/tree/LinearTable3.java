package com.sa.structure.tree;


//线性表---单项循环链表
public class LinearTable3<E> {
	private Node<E> head;
	private int size;
	
	public void addBeforeHead(E val) {
		Node<E> newNode = new Node<E>(val);
		if(isEmpty()) {
			head = newNode;
			head.setNext(newNode);
		} else {
			Node<E> tail = this.get(size);
			newNode.setNext(tail.getNext());
			tail.setNext(newNode);
			head = newNode;
		}
		size++;
	}
	
	public void addAfterHead(E val) {
		Node<E> newNode = new Node<E>(val);
		if(isEmpty()) {
			head = newNode;
			head.setNext(newNode);
		} else {
			Node<E> node = head.getNext();
			head.setNext(newNode);
			newNode.setNext(node);
		}
		size++;
	}
	
	public void insert(int index, E val) {
		Node<E> newNode = new Node<E>(val);
		if(index > 0 && index <= size + 1) {
			if(index == 1) {
				this.addBeforeHead(val);
			} /*else if(index == size) {
				Node<E> tail = get(size);
				Node<E> node = get(size - 1);
				node.setNext(newNode);
				newNode.setNext(tail);
			} */else {
				Node<E> preNode = get(index - 1);
				newNode.setNext(preNode.getNext());
				preNode.setNext(newNode);
			}
			size++;
		}
	}
	
	public void remove(int index) {
		if(index > 0 && index <= size) {
			if(index == 1) {
				Node<E> node = head.getNext();
				Node<E> tail = get(size);
				tail.setNext(node);
				head = node;
			} else {
				Node<E> preNode = get(index - 1);
				Node<E> node = get(index);
				preNode.setNext(node.getNext());
				node = null;
			}
			size--;
		}
	}
	
	public Node<E> get(int index) {
		Node<E> node = null;
		if(index > 0 && index <= size) {
			node = head;
			for(int i = 1;i < index; i++) {
				node = node.getNext();
			}
		}
		return node;
	}
	
	private boolean isEmpty() {
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
		private Node<T> next;
		private T val;
		
		public Node(T val) {
			this.val = val;
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