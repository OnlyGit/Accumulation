package com.sa.structure.list;


//线性表---链式存储
public class LinearTable2<T> {

	private Node<T> first;
	private int size;
	
	public void add( T val) {
		Node<T> newNode = new Node<T>(val);
		if(isEmpty()) {
			this.first = newNode;
		} else {
			Node<T> tail = this.get(size);
			tail.setNext(newNode);
		}
		this.size++;
	}
	
	public void insert(int index, T val) {
		if(index > 0 && (index <= size + 1)) {
			Node<T> newNode = new Node<T>(val);
			if(isEmpty() || index == 1) {
				newNode.setNext(first);
				first = newNode;
			} else {
				Node<T> node = this.get(index - 1);
				newNode.setNext(node.getNext());
				node.setNext(newNode);
			}
			size ++;
		}
	}
	
	public T remove(int index) {
		T result = null;
		if(isEmpty()) {
			return null;
		}
		if(index > 0 && index <= size) {
			if(index == 1) {
				Node<T> node = first;
				first = node.getNext();
				result = node.getVal();
				node.next = null;
			} else {
				Node<T> preNode = get(index - 1);
				Node<T> node = get(index);
				result = node.getVal();
				preNode.setNext(node.getNext());
				node.setNext(null);
			}
			size--;
		}
		return result;
	}
	
	public Node<T> get(int index) {
		Node<T> node = null;
		if(!isEmpty() && index > 0 && index <= size) {
			node = first;
			for(int i = 1; i < index; i++) {
				node = node.getNext();
			}
		}
		return node;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder(first.getVal().toString()+",");
		Node<T> node = first;
		for(int i = 1; i < this.size; i++) {
			result.append(node.getNext().getVal());
			node = node.getNext();
		}
		result.deleteCharAt(result.lastIndexOf(","));
		return result.toString();
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
