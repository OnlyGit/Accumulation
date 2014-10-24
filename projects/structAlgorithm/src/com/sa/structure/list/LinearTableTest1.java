package com.sa.structure.list;

/**
 * @author qjf
 */
public class LinearTableTest1 {

	private final int DEFAULT_SIZE = 10;
	private Object[] array;
	private int size;
	
	public LinearTableTest1() {
		this.init(DEFAULT_SIZE);
	}
	
	public LinearTableTest1(int size) {
		this.init(size);
	}
	
	private void init(int size) {
		this.size = 0;
		this.array = new Object[size];
	}
	
	public void add(Object val) {
		this.add(this.size, val);;
	}
	
	public void add(int index, Object val) {
		if(index < 0) {
			this.checkSize(index);
		}
		
		if(index < array.length) {
			Object[] newArray = new Object[array.length * 3 / 2 + 1];
			System.arraycopy(array, 0, newArray, 0, array.length);
			array = newArray;
		}
		
		for(int i = size; i > index; i--) {
			array[i] = array[i - 1];
		}
		
		array[index] = val;
		
		size++;
	}
	
	public Object get(int index) {
		checkSize(index);
		return array[index];
	}
	
	public void remove(int index) {
		this.checkSize(index);
		for(int i = index; i < size; i++) {
			array[i] = array[i + 1];
		}
		size --;
	}
	
	public int size() {
		return this.size;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < size; i++) {
			str.append(array[i]+",");
		}
		return str.toString();
	}
	
	public void checkSize(int index) {
		if(index < 0 || index >= array.length) {
			throw new IndexOutOfBoundsException();
		}
	}
}
