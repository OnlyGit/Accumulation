package com.sa.structure.tree;

public class Tree2 {

	private static final int DEFAULT_DEEP = 5;
	private Object[] array;
	private int size;
	private int deep;

	public Tree2() {
		this(DEFAULT_DEEP);
	}
	
	public Tree2(int deep) {
		this.size = (int)Math.pow(2, deep - 1);
		this.deep = deep;
		array = new Object[size];
		array[0] = "Root";
	}
	
	public void add(int index,Object val,boolean left) {
		if(null == array[index]) {
			throw new RuntimeException("选择的节点不存在！");
		}
		if(2 * index + 1 >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(left) {
			array[index * 2 + 1] = val;
		} else {
			array[index * 2 + 2] = val;
		}
	}
	
	public Object getLeft(int index) {
		if(index * 2 + 1 < size) {
			return array[2 * index + 1];
		} else {
			return null;
		}
	}
	
	public Object getRight(int index) {
		if(index * 2 + 1 < size) {
			return array[2 * index + 2];
		} else {
			return null;
		}
	}
	
	public Object getRoot() {
		return array[0];
	}
	
	public boolean isEmpty() {
		return array[0] == null;
	}
	
	public int deep() {
		return this.deep;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < array.length; i++) {
			if(null == array[i]) {
				continue;
			}
			String s = array[i].toString();
			str.append(s+",");
		}
		return str.toString();
	}
	
}
